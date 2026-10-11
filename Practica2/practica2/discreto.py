import matplotlib.pyplot as plt


def ejecutar_discreto(P0, r, tiempo):
    t_vector = list(range(tiempo + 1))
    poblacion = [P0]

    for _ in range(1, tiempo + 1):
        poblacion_actual = poblacion[-1]
        incremento = r * poblacion_actual
        nueva_poblacion = poblacion_actual + incremento
        poblacion.append(nueva_poblacion)

    return t_vector, poblacion


if __name__ == "__main__":
    # Parámetros
    P0 = 1000
    r = 0.1236
    r = - 0.1236
    tiempo = 6

    # Ejecutar modelo
    t, p = ejecutar_discreto(P0, r, tiempo)

    # Graficar (usamos puntos/líneas con marcadores porque es discreto)
    plt.plot(t, p, marker='o', linestyle='--', color='green', label='Discreto')
    plt.title('Modelo Discreto de Crecimiento Poblacional')
    plt.xlabel('Período')
    plt.ylabel('Población')
    plt.grid(True)
    plt.legend()
    plt.show()