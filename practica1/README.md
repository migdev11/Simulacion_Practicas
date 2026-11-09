# Práctica 1 - Simulación de Posibilidad de Lluvia

## 1. Descripción del proyecto
Este proyecto implementa y simula un modelo matemático computacional para analizar el comportamiento de la atmósfera durante un periodo de 24 horas y predecir la posibilidad de lluvia[cite: 1, 2]. 

**Propósito y Problema Resuelto:**
El programa calcula el índice de lluvia mediante la ecuación $I=0.5H+0.3N+0.2Tf$, basándose en datos de humedad, nubosidad y un factor de temperatura escalonado. Además, permite ajustar los pesos del modelo para observar variaciones en el comportamiento y grafica comparativamente los resultados originales frente a los ajustados.

## 2. Tecnologías utilizadas
- **Lenguaje:** Python 3.x[cite: 1]
- **Librerías principales:**
  * `NumPy`: Para el manejo de operaciones matemáticas[cite: 1].
  * `Matplotlib`: Para la visualización y renderizado de la gráfica de resultados del modelo[cite: 1].

## 3. Instalación
Para preparar el entorno y ejecutar este proyecto localmente, sigue estos pasos:

1. **Clona el repositorio** (o descarga la carpeta localmente a tu entorno `Simulacion_Practica1`):
   ```bash
   git clone [URL_DE_TU_REPOSITORIO]
   cd Simulacion_Practica1
2. **Crea y activa un entorno virtual**:   
python3 -m venv venv
source venv/bin/activate  # En Linux/Mac
3. **Instala las dependencias necesarias**:
   ```bash
   pip install numpy matplotlib
4. **Uso**:
Para ejecutar la simulación completa, utiliza el script principal.

Desde la terminal, ejecuta:
python3 main.py 
5. **Estructura de Proyecto**:

```text
El proyecto está construido bajo el patrón de diseño Modelo-Vista-Controlador (MVC), como se evidencia en la estructura de tus archivos:
Simulacion_Practica1/
 ├── modelo.py        # Define la lógica matemática y reglas del clima (I = 0.5H + 0.3N + 0.2Tf)
 ├── vista.py         # Maneja la impresión de tablas en consola y la gráfica con Matplotlib
 ├── controlador.py   # Conecta el modelo y la vista, iterando los datos y gestionando el flujo
 ├── main.py          # Script principal con los datos de entrada que arranca la simulación
 ├── README.md        # Documentación del proyecto
 └── venv/            # Entorno virtual de Python
```
6. **Librerías principales:**
  * `NumPy`: Para el manejo de operaciones matemáticas[cite: 1].
  * `Matplotlib`: Para la visualización y renderizado de la gráfica de resultados del modelo[cite: 1].
7. **Información adicional y ResultadosComportamiento de la curva**:
El índice evaluado clasifica el clima en diferentes estados. Si $I < 0.40$, no hay lluvia; entre $0.40$ y $0.60$, baja posibilidad; entre $0.60$ y $0.75$, lluvia probable; y $I \ge 0.75$ determina un estado de Lluvia definitiva[cite: 2].
8. **Ajuste del modelo**:
Al modificar los parámetros (pesos) que multiplican la humedad y nubosidad, la curva de la gráfica se desplaza, demostrando la sensibilidad del sistema frente a qué variable se le otorga mayor importancia en la simulación[