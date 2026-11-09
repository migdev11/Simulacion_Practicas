# Practica 5 — Generador Congruencial Lineal (LCG)

Implementación y análisis del **Generador Congruencial Lineal (LCG)** aplicando el patrón de diseño **Modelo-Vista-Controlador (MVC)** en dos lenguajes: **Python** y **Java**.

El proyecto genera una secuencia de números pseudoaleatorios mediante la fórmula recursiva del LCG, normaliza los valores y permite demostrar el efecto del cambio de semilla sobre la secuencia generada.

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
**Practica 5 — Generador Congruencial Lineal (LCG)**

### Propósito principal
Implementar el algoritmo del Generador Congruencial Lineal (LCG) para producir una secuencia de números pseudoaleatorios, organizando el código bajo el patrón de diseño **Modelo-Vista-Controlador (MVC)** para garantizar la separación de responsabilidades y la inyección de dependencias.

### Problema que busca resolver
Demostrar cómo un LCG genera una secuencia determinista a partir de una semilla inicial, cómo se normalizan los valores al intervalo [0, 1) y cómo el cambio de semilla produce una secuencia completamente distinta, lo cual es fundamental en simulaciones de sistemas y modelado estocástico.

### Funcionalidades más importantes

- Generación de **10,000 valores** mediante la fórmula recursiva del LCG.
- Cálculo de valores normalizados `U(n) = X(n) / m`.
- Impresión en terminal de las primeras **20 observaciones** (`n`, `X(n)`, `U(n)`).
- Cambio de semilla en tiempo de ejecución para generar una nueva muestra y comparar secuencias.
- Renderizado y guardado de una **gráfica de la secuencia** (primeros 1000 valores) en formato PNG.
- Arquitectura MVC con capas claramente separadas: modelo (lógica matemática y estado), vista (salida y gráfica) y controlador (orquestación).

---

## 2. Tecnologías utilizadas

| Categoría | Tecnología | Versión / Detalle |
|-----------|------------|-------------------|
| Lenguaje de programación | Python | 3.x |
| Lenguaje de programación | Java | 17+ |
| Biblioteca (Python) | matplotlib | Generación y guardado de gráficas |
| Biblioteca (Java) | Java AWT / `java.awt` | Renderizado de gráficas con `BufferedImage` (estándar JDK, sin dependencias externas) |
| Patrón de diseño | MVC | Modelo-Vista-Controlador implementado en ambos lenguajes |

> Nota: No se utilizan frameworks de frontend, bases de datos ni servicios externos. El proyecto es un programa de consola de propósito educativo.

---

## 3. Instalación

### Requisitos previos

- **Python**: versión 3.6 o superior.
- **Java**: JDK 17 o superior (incluye `javac` y `java`).
- **Python**: biblioteca `matplotlib` instalada.

### Clonación del repositorio

El proyecto se encuentra en el directorio `practica5/` del repositorio. No es necesario clonar un repositorio remoto adicional.

### Entorno virtual (Python)

Se recomienda crear y activar un entorno virtual antes de instalar dependencias:

**Linux / macOS:**
```bash
python3 -m venv .venv
source .venv/bin/activate
```

**Windows:**
```powershell
python -m venv .venv
.venv\Scripts\activate
```

### Instalación de dependencias (Python)

Instalar la única dependencia externa requerida:

```bash
pip install matplotlib
```

### Instalación de Java

Verificar que el JDK esté disponible:

```bash
javac -version
java -version
```

> Si se ejecuta desde un IDE como PyCharm o IntelliJ, el entorno se configura automáticamente al abrir el proyecto.

---

## 4. Configuración del proyecto

El proyecto no requiere variables de entorno ni archivos de configuración externos. Todos los parámetros están definidos como constantes dentro del código fuente.

### Parámetros obligatorios del LCG

Estos valores están hardcodeados en `LCGModel` (ambos lenguajes):

| Parámetro | Símbolo | Valor | Descripción |
|-----------|---------|-------|-------------|
| Multiplicador | `a` | `1664525` | Constante multiplicadora del LCG |
| Incremento | `c` | `1013904223` | Constante aditiva del LCG |
| Módulo | `m` | `2^32` = `4294967296` | Módulo de la operación |
| Semilla inicial | `X0` | `12345` | Valor inicial del generador |
| Semilla de cambio | — | `987654321` | Semilla alternativa para demostrar variación de secuencia |
| Cantidad de valores | `n` | `10000` | Total de valores generados por simulación |
| Muestra impresa | `limit` | `20` | Primeras observaciones mostradas en terminal |
| Muestra gráfica | `sample_size` | `1000` | Valores representados en la gráfica |

### Fórmula implementada

- Recursiva: `X(n+1) = (a * X(n) + c) mod m`
- Normalizada: `U(n) = X(n) / m`

---

## 5. Uso del proyecto

### Ejecución en Python

Desde la carpeta `practica5/python/`:

```bash
python main.py
```

**Flujo de ejecución:**
1. Instancia `LCGModel` con semilla `12345`.
2. Genera 10,000 valores mediante `LCGController`.
3. Imprime las primeras 20 observaciones (`n`, `X(n)`, `U(n)`).
4. Cambia la semilla a `987654321` y genera una nueva muestra de 20 valores.
5. Renderiza y guarda la gráfica en `practica5/python/graficas/lcg_sequence.png`.

### Ejecución en Java

Desde la carpeta `practica5/java/src/`:

```bash
java Main.java
```

**Flujo de ejecución:** idéntico al de Python (10,000 valores, primeras 20 observaciones, cambio de semilla, gráfica guardada en `practica5/java/graficas/lcg_sequence.png`).

### Salida esperada (ejemplo)

```
=== Simulacion LCG con semilla X0 = 12345 ===
n      | X(n)         | U(n)
----------------------------------
0      | 87628868     | 0.0204026857
1      | 71072467     | 0.0165478482
...
19     | 4092090893   | 0.9527641565

=== Cambio de semilla a X0 = 987654321 ===
n      | X(n)         | U(n)
----------------------------------
0      | 280611420    | 0.0653349375
...
19     | 1384348677   | 0.3223187935

=== Generando grafica ===
Grafica guardada en: .../graficas/lcg_sequence.png
```

---

## 6. Estructura del proyecto

```text
practica5/
├── java/
│   ├── graficas/
│   │   └── lcg_sequence.png
│   └── src/
│       ├── controller/
│       │   └── LCGController.java
│       ├── model/
│       │   └── LCGModel.java
│       ├── view/
│       │   └── LCGView.java
│       └── Main.java
├── python/
│   ├── controller/
│   │   ├── LCGController.py
│   │   └── __pycache__/
│   ├── graficas/
│   │   └── lcg_sequence.png
│   ├── main.py
│   ├── model/
│   │   ├── LCGModel.py
│   │   └── __pycache__/
│   └── view/
│       ├── LCGView.py
│       └── __pycache__/
└── README.md
```

### Responsabilidad de cada carpeta y archivo principal

| Carpeta / Archivo | Responsabilidad |
|-------------------|-----------------|
| `java/src/model/LCGModel.java` | Lógica matemática pura del LCG en Java: constantes `a`, `c`, `m`, estado de la semilla, métodos `nextValue`, `generate`, `normalize` y `reset`. |
| `java/src/view/LCGView.java` | Salida por consola y generación de gráfica PNG con `java.awt` en Java. |
| `java/src/controller/LCGController.java` | Orquestación entre modelo y vista en Java: ejecuta la simulación y gestiona el cambio de semilla. |
| `java/src/Main.java` | Punto de entrada de Java: ensambla MVC y ejecuta el flujo completo. |
| `python/model/LCGModel.py` | Lógica matemática pura del LCG en Python: constantes, estado de la semilla, métodos `next_value`, `generate`, `normalize` y `reset`. |
| `python/view/LCGView.py` | Salida por consola y generación de gráfica con `matplotlib` en Python. |
| `python/controller/LCGController.py` | Orquestación entre modelo y vista en Python: ejecuta la simulación y gestiona el cambio de semilla. |
| `python/main.py` | Punto de entrada de Python: ensambla MVC y ejecuta el flujo completo. |
| `graficas/` | Almacena la imagen PNG generada (`lcg_sequence.png`) por cada implementación. |
| `__pycache__/` | Caché de bytecode de Python (generado automáticamente, no se edita manualmente). |

---

## 7. Información adicional

### Arquitectura aplicada

El proyecto implementa estrictamente el patrón **MVC**:

- **Modelo (`LCGModel`)**: única fuente de verdad sobre los datos y la lógica del negocio. No conoce ni depende de la vista ni del controlador.
- **Vista (`LCGView`)**: solo se encarga de presentar información al usuario (impresión en consola y renderizado de gráfica). No contiene lógica de negocio.
- **Controlador (`LCGController`): actúa como intermediario. Recibe solicitudes, actualiza el modelo y solicita a la vista que muestre los resultados.
- **Punto de entrada (`Main`)**: instancia los tres componentes y los conecta mediante inyección de dependencias.

### Validaciones realizadas

- Ambas implementaciones (Python y Java) generan la **misma secuencia de valores** para la semilla `12345`, ya que comparten los mismos parámetros `a`, `c` y `m`.
- El cambio de semilla a `987654321` produce una secuencia distinta, demostrando la sensibilidad del LCG al valor inicial.
- Se generan exactamente **10,000 valores** por simulación, con las **primeras 20 impresas** en terminal.

### Archivos generados

- `practica5/python/graficas/lcg_sequence.png`
- `practica5/java/graficas/lcg_sequence.png`

### Notas importantes

- Los archivos `.class` de Java no deben subirse al control de versiones; se generan al compilar y se descartan.
- Las carpetas `__pycache__` de Python son caché de bytecode y no deben subirse al control de versiones.
- No se requieren credenciales, claves API ni configuración de red para ejecutar el proyecto.

---

**Autor:** Practica 5 — Simulación de Sistemas  
**Materia:** Simulación de Sistemas  
**Patrón de diseño:** Modelo-Vista-Controlador (MVC)
