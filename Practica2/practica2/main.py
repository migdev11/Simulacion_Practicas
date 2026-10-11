import numpy as np
import matplotlib.pyplot as plt

# Parámetros y datos generales
datos = [1000, 1100, 1250, 1400, 1600, 1850, 2100]
P0, t_tot, r, sig, dt = datos[0], len(datos) - 1, 0.1236, 30, 0.1
nombres = ['Determinístico', 'Discreto', 'Estocástico', 'Continuo (Euler)']
estilos = ['b-', 'g--', 'orange', 'r:']


def simular(opc):
    p = [P0]
    if opc == 0:  # Determinístico
        t = np.linspace(0, t_tot, 100)
        return t, P0 * np.exp(r * t)
    elif opc == 1:  # Discreto
        for _ in range(t_tot): p.append(p[-1] * (1 + r))
        return range(t_tot + 1), p
    elif opc == 2:  # Estocástico
        for _ in range(t_tot): p.append(max(0, p[-1] * (1 + r) + np.random.normal(0, sig)))
        return range(t_tot + 1), p
    elif opc == 3:  # Continuo
        t = np.arange(0, t_tot + dt, dt)
        for _ in range(len(t) - 1): p.append(p[-1] * (1 + r * dt))
        return t, p

while True:
    print("\n1.Determinístico | 2.Discreto | 3.Estocástico | 4.Continuo | 5.Salir")
    try:
        op = int(input("Elige (1-5): ")) - 1

        if op == 4:
            print("Saliendo...")
            break

        if 0 <= op <= 3:
            t, p = simular(op)
            plt.plot(range(len(datos)), datos, 'ko', label='Reales')
            plt.plot(t, p, estilos[op], marker='.', label=nombres[op])
            plt.title(f"Simulación: {nombres[op]}")
            plt.grid();
            plt.legend();
            plt.show()
        else:
            print("Opción fuera de rango.")

    except ValueError:
        print("\nPor favor, ingresa un número válido.")
    except KeyboardInterrupt:
        print("\n\nSimulador interrumpido manualmente. Saliendo limipamente...")
        break