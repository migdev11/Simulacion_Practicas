import matplotlib

matplotlib.use("Agg")

import matplotlib.pyplot as plt


class Consola:
    def imprimir_resultados(self, n, datos, resultado_chi, resultado_ks):
        print(f"\n=== Resultados detallados para N = {n} ===")

        print("\n--- Secuencia de números pseudoaleatorios ---")
        for i, valor in enumerate(datos):
            print(f"{valor:.10f}", end=" ")
            if (i + 1) % 10 == 0:
                print()
        print()

        print("\n--- Prueba Chi-cuadrado ---")
        print(f"{'Subintervalo':<18}{'Observada':>12}{'Esperada':>12}")

        k = len(resultado_chi["frecuencias_observadas"])
        for i, observada in enumerate(resultado_chi["frecuencias_observadas"]):
            limite_inferior = i / k
            limite_superior = (i + 1) / k
            etiqueta = f"[{limite_inferior:.1f}, {limite_superior:.1f})"
            print(
                f"{etiqueta:<18}{observada:>12d}"
                f"{resultado_chi['frecuencia_esperada']:>12.2f}"
            )

        print(f"Estadístico Chi-cuadrado : {resultado_chi['estadistico']:.6f}")
        print(f"Grados de libertad       : {resultado_chi['grados_libertad']}")
        print(f"Valor p                  : {resultado_chi['valor_p']:.6f}")
        print(
            f"Decisión (alpha={resultado_chi['alpha']}) : "
            f"{self._decision(resultado_chi['rechazar_h0'])}"
        )

        print("\n--- Prueba Kolmogorov-Smirnov ---")
        print(f"Estadístico D : {resultado_ks['estadistico_d']:.6f}")
        print(f"Valor p       : {resultado_ks['valor_p']:.6f}")
        print(
            f"Decisión (alpha={resultado_ks['alpha']}) : "
            f"{self._decision(resultado_ks['rechazar_h0'])}"
        )

    def imprimir_tabla_tiempos(self, tiempos):
        print("\n--- Tiempos de ejecución ---")
        print(f"{'N':>10}{'Tiempo de ejecución (ms)':>28}")

        for n, tiempo_ms in tiempos:
            print(f"{n:>10}{tiempo_ms:>28.3f}")

    def imprimir_ruta_grafica(self, ruta):
        print(f"\nGráfica KS guardada en: {ruta}")

    @staticmethod
    def _decision(rechazar_h0):
        return "Se rechaza H0" if rechazar_h0 else "No se rechaza H0"


def graficar_ks(resultado_ks, n, ruta_salida):
    x = resultado_ks["x_ordenado"]

    figura, eje = plt.subplots(figsize=(9, 6))
    eje.step(
        x,
        resultado_ks["cdf_empirica"],
        where="post",
        color="tab:blue",
        label="Distribución empírica $F_n(x)$",
    )
    eje.plot(
        x,
        resultado_ks["cdf_teorica"],
        color="tab:red",
        label="Distribución teórica $F(x) = x$",
    )

    eje.set_title(
        f"Kolmogorov-Smirnov (N = {n}, D = {resultado_ks['estadistico_d']:.6f}, "
        f"p = {resultado_ks['valor_p']:.6f})"
    )
    eje.set_xlabel("x")
    eje.set_ylabel("F(x)")
    eje.set_xlim(0.0, 1.0)
    eje.set_ylim(0.0, 1.0)
    eje.grid(True)
    eje.legend()
    figura.tight_layout()

    ruta_salida.parent.mkdir(parents=True, exist_ok=True)
    figura.savefig(ruta_salida, dpi=150)
    plt.close(figura)
