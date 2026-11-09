# Práctica 6 – Generador de Números Pseudoaleatorios (LCG)

> Implementación de un Generador Congruencial Lineal (LCG) para la generación de números pseudoaleatorios y validación de su calidad mediante pruebas estadísticas (Chi-cuadrado y Kolmogorov-Smirnov). Disponible en Python y Java con arquitectura MVC idéntica.

---

## Tabla de contenidos

1. [Descripción del proyecto](#1-descripción-del-proyecto)
2. [Tecnologías utilizadas](#2-tecnologías-utilizadas)
3. [Instalación](#3-instalación)
4. [Configuración del proyecto](#4-configuración-del-proyecto)
5. [Uso del proyecto](#5-uso-del-proyecto)
6. [Estructura del proyecto](#6-estructura-del-proyecto)
7. [Información adicional](#7-información-adicional)

---

## 1. Descripción del proyecto

### Nombre del proyecto

**Práctica 6 – Generador de Números Pseudoaleatorios (LCG)**

### Descripción general

Sistema que implementa un **Generador Congruencial Lineal (LCG)** para producir secuencias de números pseudoaleatorios en el intervalo $[0, 1)$ y evaluar su calidad estadística mediante dos pruebas de hipótesis: la prueba de Chi-cuadrado y la prueba de Kolmogorov-Smirnov (KS) contra una distribución uniforme continua $U(0, 1)$.

### Propósito principal

Verificar si los números generados por el LCG se comportan como una muestra aleatoria extraída de una distribución uniforme, midiendo el tiempo de ejecución para distintos tamaños de muestra y generando una representación gráfica de la función de distribución empírica versus la teórica.

### Problema que busca resolver

En simulación y modelado, es fundamental contar con generadores de números pseudoaleatorios cuya distribución se ajuste a la esperada. Este proyecto permite:

- Generar secuencias pseudoaleatorias con un LCG parametrizable.
- Evaluar el ajuste a la uniformidad mediante pruebas estadísticas formales.
- Comparar el rendimiento de generación y prueba entre distintos tamaños de muestra ($N$).
- Visualizar gráficamente la concordancia entre la CDF empírica y la CDF teórica.

### Objetivos principales

- Implementar un LCG con parámetros configurables (semilla, multiplicador, incremento, módulo).
- Aplicar la prueba de Chi-cuadrado con $k=10$ subintervalos y nivel de significancia $\alpha = 0.05$.
- Aplicar la prueba de Kolmogorov-Smirnov contra $U(0, 1)$ con $\alpha = 0.05$.
- Medir y reportar tiempos de ejecución para $N \in \{1000, 10000, 100000, 1000000\}$.
- Generar una gráfica comparativa (CDF empírica vs CDF teórica) para el caso detalle $N = 10000$.
- Proveer dos implementaciones funcionalmente equivalentes: una en Python y otra en Java.

### Funcionalidades más importantes

| Funcionalidad | Descripción |
|---|---|
| Generación LCG | Produce $N$ números pseudoaleatorios en $[0, 1)$ mediante la recurrencia $x_{n+1} = (a \cdot x_n + c) \bmod m$. |
| Prueba Chi-cuadrado | Divide $[0, 1)$ en 10 subintervalos, compara frecuencias observadas vs esperadas y calcula estadístico, grados de libertad, valor p y decisión sobre $H_0$. |
| Prueba Kolmogorov-Smirnov | Calcula el estadístico $D = \max(|F_n(x) - F(x)|)$, su valor p y la decisión sobre $H_0$ respecto a $U(0, 1)$. |
| Medición de tiempos | Registra el tiempo de ejecución (ms) para cada tamaño de muestra $N$. |
| Generación de gráfica | Dibuja y guarda como PNG la CDF empírica (pasos azules) y la CDF teórica $F(x) = x$ (línea roja). |
| Salida por consola | Imprime resultados detallados (secuencia, frecuencias, estadísticos, decisiones) y tabla de tiempos. |

---

## 2. Tecnologías utilizadas

El proyecto no depende de frameworks externos ni de base de datos. Se implementa con la biblioteca estándar de cada lenguaje, salvo `matplotlib` en Python para la generación de gráficos.

### Lenguajes de programación

| Lenguaje | Uso | Versión mínima |
|---|---|---|
| Python 3 | Implementación principal (entorno `python/`) | 3.x |
| Java | Implementación equivalente (entorno `java/`) | JDK 8+ |

### Bibliotecas y dependencias

| Tecnología | Categoría | Uso en el proyecto |
|---|---|---|
| `matplotlib` | Biblioteca (Python) | Generación de la gráfica KS (`view/consola.py`) |
| Java Standard Library | Biblioteca (Java) | Gráficos con `java.awt`, `javax.imageio` (`view/GraficadorKS.java`) |

### Herramientas de desarrollo

- IDE: cualquier editor compatible con Python y Java (el proyecto incluye configuración de IntelliJ IDEA en `.idea/`).
- Control de versiones: Git.

> No se detectaron archivos `requirements.txt`, `pyproject.toml`, `pom.xml` ni `build.gradle` específicos del proyecto. El `requirements.txt` en la raíz del repositorio padre corresponde a dependencias del sistema operativo y no es exclusivo de esta práctica.

---

## 3. Instalación

### Requisitos previos

#### Para Python

- Python 3 instalado.
- `pip` (gestor de paquetes de Python).
- Biblioteca `matplotlib`.

#### Para Java

- JDK 8 o superior instalado.
- Comandos `javac` y `java` disponibles en el `PATH`.

### Clonación del repositorio

```bash
# Desde la raíz del repositorio padre
git clone <url-del-repositorio>
cd Mis_Practicas_Simulacion/practica6
```

> Si ya cuentas con el repositorio clonado, dirígete directamente a la carpeta `practica6/`.

### Instalación de dependencias

#### Python

```bash
# Opción A: instalar matplotlib globalmente
pip install matplotlib

# Opción B: usar entorno virtual (recomendado)
python -m venv .venv
source .venv/bin/activate      # Linux/macOS
# .venv\Scripts\activate       # Windows
pip install matplotlib
```

#### Java

No se requieren dependencias externas. Solo se necesita el JDK instalado.

### Configuración inicial

No se requiere configuración adicional. Los parámetros del LCG y las pruebas están definidos como constantes en el archivo de entrada de cada implementación.

---

## 4. Configuración del proyecto

El proyecto no utiliza archivos de entorno (`.env`) ni variables de entorno para su funcionamiento. Todos los parámetros están hardcodeados como constantes en los archivos de entrada (`main.py` y `Main.java`).

### Parámetros configurables

| Parámetro | Valor por defecto | Archivo (Python) | Archivo (Java) | Descripción |
|---|---|---|---|---|
| Semilla | `12345` | `python/main.py` | `java/src/Main.java` | Valor inicial del LCG |
| Multiplicador `a` | `1664525` | `python/model/generador_lcg.py` | `java/src/model/GeneradorLCG.java` | Constante del LCG |
| Incremento `c` | `1013904223` | `python/model/generador_lcg.py` | `java/src/model/GeneradorLCG.java` | Constante del LCG |
| Módulo `m` | $2^{32}$ | `python/model/generador_lcg.py` | `java/src/model/GeneradorLCG.java` | Constante del LCG |
| Tamaños de muestra $N$ | `{1000, 10000, 100000, 1000000}` | `python/main.py` | `java/src/Main.java` | Cantidad de números a generar |
| Tamaño detalle $N_{detalle}$ | `10000` | `python/main.py` | `java/src/Main.java` | Muestra para salida detallada y gráfica |
| Nivel de significancia $\alpha$ | `0.05` | `python/main.py` | `java/src/Main.java` | Umbral para pruebas de hipótesis |
| Intervalos Chi-cuadrado $k$ | `10` | `python/main.py` | `java/src/Main.java` | Número de subintervalos |

> Para modificar estos valores, edita las constantes correspondientes en el archivo de entrada del lenguaje que vayas a utilizar y vuelve a ejecutar el programa.

---

## 5. Uso del proyecto

### Ejecución en Python

Desde la carpeta `python/`:

```bash
python main.py
```

Si usas un entorno virtual:

```bash
source .venv/bin/activate      # Linux/macOS
# .venv\Scripts\activate       # Windows
python main.py
```

### Ejecución en Java

Desde la carpeta `java/src/`:

```bash
# Compilar todos los archivos
javac model/GeneradorLCG.java controller/PruebasEstadisticas.java view/Consola.java view/GraficadorKS.java Main.java

# Ejecutar
java Main
```

### Salida esperada

El programa imprime en consola:

1. Para cada valor de $N$: tiempo de ejecución en milisegundos.
2. Para $N = 10000$ (caso detalle):
   - Secuencia de números pseudoaleatorios generados (primeros valores).
   - Resultado de la prueba Chi-cuadrado: frecuencias observadas vs esperadas por subintervalo, estadístico, grados de libertad, valor p y decisión sobre $H_0$.
   - Resultado de la prueba KS: estadístico $D$, valor p y decisión sobre $H_0$.
3. Ruta del archivo de la gráfica KS guardada.

Además, se genera el archivo:

```
graficas/ks_N10000.png
```

que contiene la comparativa entre la CDF empírica y la CDF teórica $F(x) = x$.

### Ejemplo de salida por consola (resumido)

```
=== Resultados detallados para N = 10000 ===

--- Secuencia de números pseudoaleatorios ---
0.0034251987 0.8754321098 ...

--- Prueba Chi-cuadrado ---
Subintervalo     Observada     Esperada
[0.0, 0.1)            998       1000.00
...
Estadístico Chi-cuadrado : 2.456789
Grados de libertad       : 9
Valor p                  : 0.981234
Decisión (alpha=0.05) : No se rechaza H0

--- Prueba Kolmogorov-Smirnov ---
Estadístico D : 0.004567
Valor p       : 0.998765
Decisión (alpha=0.05) : No se rechaza H0

--- Tiempos de ejecución ---
          N Tiempo de ejecución (ms)
       1000                      12.345
     10000                      45.678
    100000                     452.109
   1000000                    4521.345

Gráfica KS guardada en: /ruta/absoluta/graficas/ks_N10000.png
```

---

## 6. Estructura del proyecto

```text
practica6/
├── README.md
├── python/
│   ├── main.py
│   ├── model/
│   │   └── generador_lcg.py
│   ├── controller/
│   │   └── pruebas_estadisticas.py
│   ├── view/
│   │   └── consola.py
│   └── graficas/
│       └── ks_N10000.png
└── java/
    ├── src/
    │   ├── Main.java
    │   ├── model/
    │   │   └── GeneradorLCG.java
    │   ├── controller/
    │   │   └── PruebasEstadisticas.java
    │   └── view/
    │       ├── Consola.java
    │       └── GraficadorKS.java
    └── graficas/
        └── ks_N10000.png
```

### Responsabilidad de cada carpeta y archivo

#### Carpeta `python/`

| Archivo / carpeta | Responsabilidad |
|---|---|
| `main.py` | Punto de entrada. Orquesta la generación, las pruebas, la medición de tiempos y la impresión de resultados. |
| `model/generador_lcg.py` | Implementación del Generador Congruencial Lineal (LCG). |
| `controller/pruebas_estadisticas.py` | Implementación de las pruebas de Chi-cuadrado y Kolmogorov-Smirnov, incluyendo cálculo de valores p mediante funciones gamma. |
| `view/consola.py` | Impresión de resultados en consola y generación de la gráfica KS con `matplotlib`. |
| `graficas/ks_N10000.png` | Gráfica de salida generada por la ejecución (CDF empírica vs teórica). |

#### Carpeta `java/`

| Archivo / carpeta | Responsabilidad |
|---|---|
| `src/Main.java` | Punto de entrada. Orquesta la generación, las pruebas, la medición de tiempos y la impresión de resultados. |
| `src/model/GeneradorLCG.java` | Implementación del Generador Congruencial Lineal (LCG). |
| `src/controller/PruebasEstadisticas.java` | Implementación de las pruebas de Chi-cuadrado y Kolmogorov-Smirnov, incluyendo cálculo de valores p mediante aproximación de Lanczos para log-gamma. |
| `src/view/Consola.java` | Impresión de resultados formateada en consola. |
| `src/view/GraficadorKS.java` | Generación de la gráfica KS como archivo PNG utilizando `java.awt` y `javax.imageio`. |
| `graficas/ks_N10000.png` | Gráfica de salida generada por la ejecución (CDF empírica vs teórica). |

> Las carpetas `__pycache__` (Python) y los archivos `.class` (Java) generados tras la compilación no se incluyen en la estructura anterior, ya que son artefactos temporales de compilación.

---

## 7. Información adicional

### Funcionalidades principales

- Generación de números pseudoaleatorios mediante LCG con parámetros configurables.
- Validación estadística con prueba de Chi-cuadrado ($\alpha = 0.05$, $k = 10$).
- Validación estadística con prueba de Kolmogorov-Smirnov contra $U(0, 1)$ ($\alpha = 0.05$).
- Medición de tiempos de ejecución para múltiples tamaños de muestra.
- Generación automática de gráfica comparativa CDF empírica vs CDF teórica.
- Implementación dual: Python y Java con arquitectura MVC equivalente.

### Capturas de pantalla

La carpeta `graficas/` contiene el archivo `ks_N10000.png`, generado automáticamente al ejecutar el programa. Muestra:

- Línea roja: distribución teórica $F(x) = x$.
- Línea azul escalonada: distribución empírica $F_n(x)$.
- Título con $N$, estadístico $D$ y valor p.

### Pruebas y validaciones implementadas

| Prueba | Hipótesis nula $H_0$ | Nivel de significancia | Estadístico | Decisión |
|---|---|---|---|---|
| Chi-cuadrado | Los datos siguen una distribución uniforme en $[0, 1)$. | $\alpha = 0.05$ | $\chi^2 = \sum \frac{(O_i - E_i)^2}{E_i}$ | Rechazar $H_0$ si $p < \alpha$ |
| Kolmogorov-Smirnov | La CDF empírica coincide con la teórica $F(x) = x$. | $\alpha = 0.05$ | $D = \max_x |F_n(x) - F(x)|$ | Rechazar $H_0$ si $p < \alpha$ |

### Notas de implementación

- Ambas implementaciones (Python y Java) son funcionalmente equivalentes y siguen el mismo patrón de arquitectura MVC.
- El cálculo del valor p en Python usa series de potencia y fracciones continuas para la función gamma incompleta.
- El cálculo del valor p en Java usa la aproximación de Lanczos para `logGamma` junto con los mismos métodos numéricos.
- La gráfica en Python se genera con `matplotlib` (backend `Agg`, sin interfaz gráfica).
- La gráfica en Java se genera con `java.awt` y `javax.imageio`, sin dependencias externas.

### Pendiente de verificar

- No se encontró un archivo `requirements.txt` específico de la práctica; la única dependencia de Python es `matplotlib`.
- No hay tests unitarios automatizados en el repositorio.
- No se incluye archivo de licencia ni archivo de contribución (`CONTRIBUTING.md`).

---

## Licencia

No se especifica una licencia en el repositorio.

---

> Documentación generada a partir del análisis del código fuente existente en `practica6/`. Cualquier cambio en los parámetros del LCG o en las constantes de ejecución debe reflejarse editando los archivos de entrada correspondientes (`main.py` o `Main.java`).
