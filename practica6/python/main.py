import time
from pathlib import Path

from controller.pruebas_estadisticas import prueba_chi_cuadrado, prueba_ks
from model.generador_lcg import GeneradorLCG
from view.consola import Consola, graficar_ks


VALORES_N = [1000, 10000, 100000, 1000000]
N_DETALLE = 10000
ALPHA = 0.05


def main():
    consola = Consola()
    tiempos = []

    for n in VALORES_N:
        generador = GeneradorLCG()

        inicio = time.perf_counter()
        datos = generador.generar(n)
        resultado_chi = prueba_chi_cuadrado(datos, k=10, alpha=ALPHA)
        resultado_ks = prueba_ks(datos, alpha=ALPHA)
        fin = time.perf_counter()

        tiempo_ms = (fin - inicio) * 1000.0
        tiempos.append((n, tiempo_ms))

        if n == N_DETALLE:
            consola.imprimir_resultados(n, datos, resultado_chi, resultado_ks)
            ruta_grafica = (
                Path(__file__).resolve().parent / "graficas" / f"ks_N{n}.png"
            )
            graficar_ks(resultado_ks, n, ruta_grafica)
            consola.imprimir_ruta_grafica(ruta_grafica)

    consola.imprimir_tabla_tiempos(tiempos)


if __name__ == "__main__":
    main()
