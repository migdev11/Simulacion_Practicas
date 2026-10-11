import matplotlib.pyplot as plt

class VistaClima:
    def mostrar_cabecera(self, titulo):
        print(f"\n================ {titulo} ================")
        print("Hora   | H    | N    | Tf   | Indice | Estado")
        print("-" * 55)

    def mostrar_fila(self, hora, h, n, tf, i, estado):
        print(f"{hora}  | {h:.2f} | {n:.2f} | {tf:.2f} | {i:.3f}  | {estado}")

    def mostrar_grafica_doble(self, horas, indices_orig, indices_ajust):
        plt.figure(figsize=(10, 5))

        plt.plot(horas, indices_orig, marker='o', label="Original (H=0.5, N=0.3)")
        plt.plot(horas, indices_ajust, marker='s', linestyle='-.', label="Ajustado (H=0.3, N=0.5)")

        plt.axhline(y=0.75, linestyle="--", color="red", label="Límite Lluvia (0.75)")

        plt.xlabel("Tiempo")
        plt.ylabel("Índice de posibilidad de lluvia")
        plt.title("Comparación del Modelo Original vs Ajustado")
        plt.grid(True)
        plt.legend()
        plt.show()