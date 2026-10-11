# Práctica 2 - Simulación de Crecimiento Poblacional

## 1. Descripción del proyecto
Este proyecto implementa y compara cuatro modelos matemáticos de simulación para analizar el comportamiento del crecimiento poblacional a lo largo del tiempo.

**Propósito y Problema Resuelto:**
Permite visualizar cómo evoluciona una población partiendo de un conjunto de datos reales ($P_0 = 1000$), evaluando su comportamiento ideal, sus fluctuaciones reales y aproximaciones numéricas. Ayuda a comprender la dinámica de sistemas y la influencia directa de la tasa de crecimiento ($r$).

## 2. Tecnologías utilizadas
* **Lenguaje:** Python 3.x
* **Librerías principales:**
  * `NumPy`: Para cálculos matemáticos, generación de vectores de tiempo y ruido estadístico.
  * `Matplotlib`: Para la visualización y renderizado de las gráficas comparativas.
* **Dependencias adicionales:** `SciPy`, `Pandas` (para el manejo analítico de datos).

## 3. Instalación
Para preparar el entorno y ejecutar este proyecto localmente, sigue estos pasos:

1. **Clona el repositorio** (o descarga la carpeta):
   ```bash
   git clone [https://github.com/migdev11/Simulacion_Practicas.git](https://github.com/migdev11/Simulacion_Practicas.git)
   cd Simulacion_Practicas/practica2
   
   2. **Crea y activa un entorno virtual** (Opcional):
    python -m venv .venv
   source .venv/bin/activate  # En Linux/Mac
3. **Instala las dependencias necesarias:**
   ```bash
   pip install pynetlogo numpy pandas matplotlib scipy
4. **Uso**

Para ejecutar la simulación, puedes correr cada modelo por separado o utilizar el simulador principal interactivo.

Desde la terminal, para ejecutar un modelo específico:
    python deterministico.py
Para correr el menú interactivo con todos los modelos:
    python main.py
Al ejecutar el simulador interactivo, la consola mostrará un menú numerado (1-5) donde podrás elegir qué modelo graficar comparado con los datos reales.

5. **Estructura de Proyecto**

## 5. Estructura de Proyecto

```text
practica2/
 ├── deterministico.py        # Implementa la fórmula exacta P = P0 * e^(rt)
 ├── discreto.py              # Calcula el crecimiento en intervalos definidos
 ├── estocastico.py           # Añade una variable aleatoria (ruido) al sistema
 ├── modelo_continuo.py       # Usa aproximación numérica (método de Euler)
 ├── simulador_interactivo.py # Script principal con menú de gráficas
 └── README.md                # Documentación del proyecto
```

6. **Información adicional y Resultados**

Influencia de r (Tasa de crecimiento):
El parámetro r define la trayectoria del sistema.

    Si se configura un valor de r > 0 (ej. 0.1236), se obtienen gráficas de crecimiento exponencial.

    Si se modifica en el código por un valor negativo (ej. r = -0.1236), el modelo arrojará gráficas de decrecimiento, simulando una población que tiende a la extinción.

Interpretación matemática:
El modelo estocástico es el que mejor representa la realidad al incluir fluctuaciones naturales (clima, eventos externos), mientras que el método de Euler demuestra cómo los pasos más pequeños (dt) acercan el modelo continuo a la exactitud del modelo determinístico.

