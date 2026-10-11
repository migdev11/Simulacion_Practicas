#Práctica 2 - Simulación de Crecimiento Poblacional1. Descripción del proyectoEste proyecto implementa y compara cuatro modelos matemáticos de simulación para analizar el comportamiento del crecimiento poblacional a lo largo del tiempo.
Propósito y Problema Resuelto: Permite visualizar cómo evoluciona una población partiendo de un conjunto de datos reales ($P_0 = 1000$), evaluando su comportamiento ideal, sus fluctuaciones reales y aproximaciones numéricas. Ayuda a comprender la dinámica de sistemas y la influencia directa de la tasa de crecimiento ($r$).2. Tecnologías utilizadasLenguaje: Python 3.xLibrerías principales:NumPy: Para cálculos matemáticos, generación de vectores de tiempo y ruido estadístico (distribución normal).Matplotlib: Para la visualización y renderizado de las gráficas comparativas.SciPy / Pandas: Como dependencias adicionales para el manejo analítico de datos.3. InstalaciónPara preparar el entorno y ejecutar este proyecto localmente, sigue estos pasos:Clona el repositorio (o descarga la carpeta):git clone https://github.com/migdev11/Simulacion_Practicas.git
cd Simulacion_Practicas/Practica2
(Opcional) Crea y activa un entorno virtual:python -m venv .venv
source .venv/bin/activate  # En Linux/Mac
Instala las dependencias necesarias:pip install pynetlogo numpy pandas matplotlib scipy
4. UsoPara ejecutar la simulación, puedes correr cada modelo por separado o utilizar el simulador principal. Desde la terminal, ejecuta:# Para correr un modelo específico:
python deterministico.py

# Para correr el menú interactivo con todos los modelos:
python simulador_interactivo.py
Al ejecutar el simulador interactivo, la consola mostrará un menú numerado (1-5) donde podrás elegir qué modelo graficar comparado con los datos reales.5. Configuración (Parámetros)Si deseas alterar el comportamiento de la simulación, puedes modificar las siguientes variables al inicio de los scripts:P0: Población inicial.r: Tasa de crecimiento.sigma: Nivel de aleatoriedad (solo para el modelo estocástico).dt: Tamaño del paso (para el modelo continuo por Euler).6. Estructura del proyectoPractica2/
├── deterministico.py       # Implementa la fórmula exacta P = P0 * e^(rt)
├── discreto.py             # Calcula el crecimiento en intervalos de tiempo definidos
├── estocastico.py          # Añade una variable aleatoria (ruido) al sistema
├── modelo_continuo.py      # Usa aproximación numérica por el método de Euler
├── simulador_interactivo.py # Script principal con menú para graficar todos los modelos
└── README.md               # Documentación del proyecto
7. Información adicional y ResultadosInfluencia de $r$ (Tasa de crecimiento): El parámetro $r$ define la trayectoria del sistema. Si se configura un valor de $r > 0$ (ej. $0.1236$), se obtienen gráficas de crecimiento exponencial. Si se modifica en el código por un valor negativo (ej. $r = -0.1236$), el modelo arrojará gráficas de decrecimiento, simulando una población que tiende a la extinción.Interpretación matemática: El modelo estocástico es el que mejor representa la realidad al incluir fluctuaciones naturales (clima, eventos externos), mientras que el método de Euler demuestra cómo los pasos más pequeños ($dt$) acercan el modelo continuo a la exactitud del modelo determinístico.
