# Advent of Code – Día7

## 🧩 Descripción del problema
reto A)
El reto consiste en simular el recorrido de un haz de taquiones dentro de un diagrama en forma de cuadrícula. El haz comienza en una posición inicial y se mueve siempre hacia abajo, atravesando espacios vacíos hasta encontrar divisores que lo detienen y generan nuevos haces hacia la izquierda y la derecha. Cada divisor cuenta como una división del haz, incluso si varios haces confluyen en el mismo punto. El objetivo es contar cuántas veces ocurre una división total durante todo el proceso.

reto B)
En la segunda parte, el comportamiento se interpreta de forma cuántica: un único taquión recorre simultáneamente todas las rutas posibles del diagrama. Cada divisor genera una bifurcación temporal, creando múltiples líneas de tiempo independientes. El objetivo ya no es contar divisiones, sino calcular cuántas líneas de tiempo distintas existen al finalizar todos los recorridos posibles. El reto se centra en el conteo combinatorio de caminos dentro de la estructura del diagrama.
---
## 1. Metodología: Test Driven Development (TDD)

El diseño modular facilita un enfoque TDD, aislando la lógica computacionalmente intensiva:

* **Testabilidad de la Lógica Cuántica:** La clase `QuantumSpace` recibe la grilla (`List<String>`) directamente en su constructor. Esto permite probar el algoritmo de búsqueda de rutas con escenarios controlados (ej. callejones sin salida, espacios abiertos, obstáculos) sin depender de archivos externos.
* **Separación de Preocupaciones:** La validación de límites y la lógica de movimiento (`getResult`) están separadas, permitiendo tests granulares sobre cómo se comporta una "partícula" o "línea de tiempo" en una coordenada específica.

---

## Flujo de Ejecución

1.  **Main** inicia el proceso llamando a `TachyonManifold`.
2.  **Carga de Datos:** `TachyonManifold` utiliza `FileReader` para cargar las "capas" del colector (líneas del archivo) en una lista de Strings.
3.  **Inicialización del Espacio:** Se crea una instancia de `QuantumSpace` con la grilla cargada.
4.  **Búsqueda del Origen:** Se identifica la columna inicial buscando el caracter 'S' en la primera fila.
5. **reto A**  
**Recorrido iterativo (`getTimelines`):**
    * En cada nivel itera sobre las posiciones actuales
    * Si encuentra un espacio vacío (`.`), baja a la siguiente fila.
    * Si encuentra un obstáculo, suma a la cuenta y borra la posiscion actual para añadir dos posisicones mas, (actual -1) y (actual +1)
    * itera siguinte nivel
6. **Reto b**  
**Recorrido Recursivo (`getTimelines`):**
    * El algoritmo intenta descender desde la posición actual.
    * Si encuentra un espacio vacío (`.`), baja a la siguiente fila.
    * Si encuentra un obstáculo, se bifurca horizontalmente (izquierda y derecha) en la misma fila.  
**Optimización (Memoización):** Antes de calcular cualquier ruta, verifica si el resultado para esa coordenada `(row, col)` ya existe en el mapa `memo`. Si existe, lo devuelve inmediatamente, podando el árbol de recursión.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`TachyonManifold`:** Responsabilidad de configuración y I/O. Prepara los datos crudos para el simulador.
* **`QuantumSpace`:** Responsabilidad algorítmica. Gestiona el estado de la recursión, la caché de resultados y las reglas de movimiento dentro de la grilla.
* **`Main`:** Punto de entrada.

### O - Open/Closed Principle (OCP)
* El sistema define la interfaz `QuantumSimulator`. Si en el futuro se requiere un simulador que use un algoritmo iterativo (basado en pilas) o un algoritmo de búsqueda diferente (A*), se puede implementar una nueva clase que cumpla el contrato sin modificar `TachyonManifold` (salvo la inyección).

### D - Dependency Inversion Principle (DIP)
* `TachyonManifold` depende de `FileReader` (abstracción) para obtener los datos.
* `QuantumSpace` no depende de ninguna clase concreta externa, solo de estructuras de datos estándar de Java (`List`, `Map`), lo que lo hace altamente portable y reutilizable.

---

## 3. Patrones de Diseño

* **Memoization (Dynamic Programming):**
    * Uso de `Map<String, Long> memo` para almacenar resultados de sub-problemas ya resueltos.
    * **Impacto:** Transforma una complejidad exponencial $O(2^N)$ (debido a la bifurcación izquierda/derecha) en una complejidad casi lineal proporcional al tamaño de la grilla $O(Rows \times Cols)$.
* **Static Factory Method:**
    * `TachyonManifold.create()` encapsula la creación del objeto y sus dependencias internas (`ResourceFileReader`).
* **Strategy Pattern (Implícito):**
    * La interfaz `QuantumSimulator` permite intercambiar la estrategia de simulación.

---

## 4. Clean Code y Estilo

* **Short-Circuit Evaluation:**
    * Las condiciones de guarda al inicio de `getTimelines` (límites de la grilla, caché) limpian el flujo principal del método, evitando anidamiento excesivo (`if/else` profundos).
* **Naming Claro:**
    * `countTimelines`, `loadLayers`. Los nombres reflejan el dominio del problema (física cuántica/temporal).
* **Separación de Lógica de Decisión:**
    * El método `getResult` encapsula exclusivamente la regla de negocio del movimiento (bajar si es punto, bifurcar si no), separándola de la gestión de estado y caché.

---

## 5. Justificación de Decisiones Técnicas

### Uso de Recursividad con Memoización
* **Decisión:** Implementar `getTimelines` como una función recursiva que se llama a sí misma para explorar caminos.
* **Justificación:** El problema es inherentemente una exploración de árbol/grafo (DFS - Depth First Search). La recursividad es la forma más natural de expresar "la suma de caminos desde aquí es la suma de caminos de mis vecinos". Sin memoización, sería inviable; con ella, es eficiente y legible.

### Clave de Caché (`String key`)
* **Implementación:** `String key = row + "," + col;`.
* **Justificación:** Aunque generar Strings genera basura en el Heap (GC pressure), es una forma rápida y libre de colisiones para crear una clave compuesta única para un par de coordenadas `(x, y)` en Java. Una alternativa optimizada sería usar un `long` con bits desplazados (ej. `(row << 32) | col`), pero el String es suficientemente rápido para el tamaño esperado de los inputs de este reto y gana en legibilidad.

### Estructura de Datos (`List<String>`)
* **Decisión:** Mantener la grilla como `List<String>`.
* **Justificación:** Permite acceso eficiente (`get(row).charAt(col)`) sin la sobrecarga de memoria de convertir todo a una matriz de objetos o `char[][]` explícita, aprovechando que los Strings en Java son inmutables y compactos.