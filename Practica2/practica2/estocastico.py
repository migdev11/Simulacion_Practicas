import numpy as np
import matplotlib.pyplot as plt


def ejecutar_estocastico(P0, r, sigma, tiempo):
    t_vector = list(range(tiempo + 1))
    poblacion = [P0]

    for _ in range(1, tiempo + 1):
        # Generar ruido con distribución normal
        ruido = np.random.normal(0, sigma)

        poblacion_actual = poblacion[-1]
        # Calcular nueva población sumando el incremento y el ruido
        nueva_poblacion = poblacion_actual + (r * poblacion_actual) + ruido

        # Evitar que la población sea negativa
        if nueva_poblacion < 0:
            nueva_poblacion = 0

        poblacion.append(nueva_poblacion)

    return t_vector, poblacion


if __name__ == "__main__":
    # Parámetros
    P0 = 1000
    r = 0.1236
    r = - 0.1236
    sigma = 30
    tiempo = 6

    # Ejecutar modelo
    t, p = ejecutar_estocastico(P0, r, sigma, tiempo)

    # Graficar
    plt.plot(t, p, marker='x', linestyle='-', color='orange', label='Estocástico')
    plt.title('Modelo Estocástico de Crecimiento Poblacional')
    plt.xlabel('Tiempo')
    plt.ylabel('Población')
    plt.grid(True)
    plt.legend()
    plt.show()