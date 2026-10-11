import numpy as np
import matplotlib.pyplot as plt


def ejecutar_continuo(P0, r, tiempo, dt):

    t_vector = np.arange(0, tiempo + dt, dt)
    poblacion = [P0]

    for _ in range(1, len(t_vector)):
        poblacion_actual = poblacion[-1]

        cambio = r * poblacion_actual
        nueva_poblacion = poblacion_actual + (dt * cambio)

        poblacion.append(nueva_poblacion)

    return t_vector, poblacion


if __name__ == "__main__":

    P0 = 1000
    r = 0.1236
    r = - 0.1236
    tiempo = 6
    dt = 0.1

    t, p = ejecutar_continuo(P0, r, tiempo, dt)

    plt.plot(t, p, color='red', label='Continuo (Euler)')
    plt.title('Modelo Continuo (Euler) de Crecimiento')
    plt.xlabel('Tiempo')
    plt.ylabel('Población')
    plt.grid(True)
    plt.legend()
    plt.show()