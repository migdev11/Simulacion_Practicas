import math


def _gamma_p_serie(a, x):
    ap = a
    suma = 1.0 / a
    termino = suma

    for _ in range(10000):
        ap += 1.0
        termino *= x / ap
        suma += termino
        if abs(termino) < abs(suma) * 1e-15:
            break

    return suma * math.exp(-x + a * math.log(x) - math.lgamma(a))


def _gamma_q_fraccion(a, x):
    fpmax = 1e300
    fpmin = 1e-300
    eps = 1e-15

    b = x + 1.0 - a
    c = fpmax
    d = 1.0 / b
    h = d

    for i in range(1, 10000):
        an = -i * (i - a)
        b += 2.0
        d = an * d + b
        if abs(d) < fpmin:
            d = fpmin
        c = b + an / c
        if abs(c) < fpmin:
            c = fpmin
        d = 1.0 / d
        delta = d * c
        h *= delta
        if abs(delta - 1.0) < eps:
            break

    return math.exp(-x + a * math.log(x) - math.lgamma(a)) * h


def _gamma_q(a, x):
    if x <= 0.0:
        return 1.0
    if x < a + 1.0:
        return 1.0 - _gamma_p_serie(a, x)
    return _gamma_q_fraccion(a, x)


def _chi_cuadrado_valor_p(estadistico, grados_libertad):
    if estadistico <= 0.0:
        return 1.0
    return _gamma_q(grados_libertad / 2.0, estadistico / 2.0)


def _ks_valor_p(estadistico_d, n):
    if n <= 0 or estadistico_d <= 0.0:
        return 1.0

    raiz_n = math.sqrt(n)
    lam = (raiz_n + 0.12 + 0.11 / raiz_n) * estadistico_d

    if lam <= 0.0:
        return 1.0

    if lam < 0.5:
        suma = 0.0
        for j in range(1, 1000):
            impar = 2 * j - 1
            termino = math.exp(-(impar * impar * math.pi * math.pi) / (8.0 * lam * lam))
            suma += termino
            if termino < 1e-16:
                break
        cdf = math.sqrt(2.0 * math.pi) / lam * suma
        valor_p = 1.0 - cdf
    else:
        suma = 0.0
        signo = 1.0
        for j in range(1, 1000):
            termino = math.exp(-2.0 * j * j * lam * lam)
            suma += signo * termino
            if termino < 1e-16:
                break
            signo = -signo
        valor_p = 2.0 * suma

    return min(1.0, max(0.0, valor_p))


def prueba_chi_cuadrado(datos, k=10, alpha=0.05):
    n = len(datos)
    if n == 0:
        raise ValueError("La muestra no puede estar vacía.")

    frecuencias_observadas = [0] * k

    for valor in datos:
        indice = int(valor * k)
        if indice < 0:
            indice = 0
        elif indice >= k:
            indice = k - 1
        frecuencias_observadas[indice] += 1

    frecuencia_esperada = n / k
    estadistico = 0.0

    for observada in frecuencias_observadas:
        diferencia = observada - frecuencia_esperada
        estadistico += (diferencia * diferencia) / frecuencia_esperada

    grados_libertad = k - 1
    valor_p = _chi_cuadrado_valor_p(estadistico, grados_libertad)
    rechazar_h0 = valor_p < alpha

    return {
        "frecuencias_observadas": frecuencias_observadas,
        "frecuencia_esperada": frecuencia_esperada,
        "estadistico": estadistico,
        "grados_libertad": grados_libertad,
        "valor_p": valor_p,
        "alpha": alpha,
        "rechazar_h0": rechazar_h0,
    }


def prueba_ks(datos, alpha=0.05):
    n = len(datos)
    if n == 0:
        raise ValueError("La muestra no puede estar vacía.")

    x_ordenado = sorted(datos)
    cdf_empirica = [0.0] * n
    cdf_teorica = [0.0] * n

    d_mas = 0.0
    d_menos = 0.0

    for i in range(1, n + 1):
        empirica = i / n
        teorica = x_ordenado[i - 1]

        cdf_empirica[i - 1] = empirica
        cdf_teorica[i - 1] = teorica

        diferencia_mas = empirica - teorica
        diferencia_menos = teorica - (i - 1) / n

        if diferencia_mas > d_mas:
            d_mas = diferencia_mas
        if diferencia_menos > d_menos:
            d_menos = diferencia_menos

    estadistico_d = max(d_mas, d_menos)
    valor_p = _ks_valor_p(estadistico_d, n)
    rechazar_h0 = valor_p < alpha

    return {
        "estadistico_d": estadistico_d,
        "valor_p": valor_p,
        "alpha": alpha,
        "rechazar_h0": rechazar_h0,
        "x_ordenado": x_ordenado,
        "cdf_empirica": cdf_empirica,
        "cdf_teorica": cdf_teorica,
    }
