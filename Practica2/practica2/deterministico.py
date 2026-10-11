import numpy as np
import matplotlib.pyplot as plt


def ejecutar_deterministico(P0, r, tiempo):
    t_vector = np.linspace(0, tiempo, 100)

    poblacion = P0 * np.exp(r * t_vector)

    return t_vector, poblacion


if __name__ == "__main__":
    P0 = 1000
    r = 0.1236
    r = - 0.1236
    tiempo = 6


    t, p = ejecutar_deterministico(P0, r, tiempo)

    plt.plot(t, p, label='Determinístico', color='blue', linewidth=2)
    plt.title('Modelo Determinístico de Crecimiento Poblacional')
    plt.xlabel('Tiempo')
    plt.ylabel('Población')
    plt.grid(True)
    plt.legend()
    plt.show()