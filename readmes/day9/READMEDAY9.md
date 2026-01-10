# Advent of Code – Día9
## 🧩 Descripción del problema
reto A)
El reto consiste en analizar un conjunto de baldosas rojas ubicadas en una cuadrícula y elegir dos de ellas como esquinas opuestas de un rectángulo. El rectángulo puede abarcar cualquier tipo de baldosa, sin restricciones adicionales, y su área se calcula a partir de la distancia entre las coordenadas elegidas. Se deben evaluar todas las combinaciones posibles de pares de baldosas rojas para encontrar el rectángulo de mayor área. El objetivo final es determinar el área máxima que puede formarse.

reto B)
En la segunda parte se introduce una restricción adicional: el rectángulo solo puede incluir baldosas rojas o verdes. Las baldosas verdes forman un contorno y relleno conectando las rojas en un bucle cerrado. Esto limita las regiones válidas donde puede existir un rectángulo completo. El reto consiste en encontrar, entre todos los pares de baldosas rojas, el rectángulo de mayor área que esté contenido íntegramente dentro de la zona formada por baldosas rojas y verdes.

---

## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Main** invoca a `CinemaSolver`.
2.  **Parsing:** `CinemaSolver` lee el archivo y convierte las coordenadas "x,y" en una lista de registros `Point`.  

**reto A**  
* **Inicialización del Tablero (`Board`):**
* Buscamos el rectangulo con mayor area con la combinacion de todas las baldosas  


**reto B** 

* **Inicialización del Tablero (`Board`):** Al instanciar `Board`, ocurre un pre-procesamiento intensivo:
    * **Compresión de Coordenadas:** Se mapean las coordenadas reales (que pueden ser millones) a un espacio discreto pequeño basado en los valores únicos de X e Y.
    * **Construcción de Grilla:** Se dibuja el polígono en una matriz comprimida.
    * **Flood Fill:** Se inunda el exterior del polígono para marcar qué celdas son "fuera" (inválidas) y cuáles son "dentro" (válidas).
    * **Prefix Sum:** Se construye una tabla de sumas acumuladas para consultas rápidas.
* **Cálculo (`getBigerRectangle`):**
    * Se iteran pares de puntos del borde (`redTiles`).
    * Se asume que estos pares forman la diagonal de un rectángulo candidato.
    * Se verifica en $O(1)$ si el rectángulo es válido (está totalmente dentro) usando la matriz de sumas prefijas.
    * Se devuelve el área máxima encontrada.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Point` (Record):** Datos puros inmutables (x, y).
* **`CinemaSolver`**: Responsabilidad de I/O y conversión de datos. Actúa como factoría para el `Board`.
* **`Board`**: Responsabilidad de cálculo geométrico. Maneja la representación interna del espacio y la lógica de validación de rectángulos.

### O - Open/Closed Principle (OCP)
* `CinemaSolver` depende de la interfaz `Map`. Si en el futuro se requiere buscar el rectángulo más grande usando un algoritmo diferente, se puede crear una clase `HistogramBoard` que implemente `Map` sin modificar el `CinemaSolver` por ejemplo.

### D - Dependency Inversion Principle (DIP)
* `CinemaSolver` depende de la abstracción `FileReader` para obtener los datos, facilitando el testing sin archivos físicos.

---

## 3. Patrones de Diseño y Algoritmos

* **Coordinate Compression (Compresión de Coordenadas):**
    * Mapeo de valores dispersos grandes a índices consecutivos pequeños usando `TreeSet` y `binarySearch`.
    * **Propósito:** Permite usar matrices (`int[][]`) para representar espacios de coordenadas gigantes (ej. x=1,000,000) sin desbordar la memoria RAM.
* **Prefix Sum 2D (Suma de Prefijos):**
    * Pre-cálculo de la suma acumulada de la grilla.
    * **Propósito:** Permite calcular la suma de cualquier sub-rectángulo en tiempo constante $O(1)$. Aquí se usa para saber si un rectángulo contiene celdas "exteriores" (valor 1). Si la suma es 0, el rectángulo es válido (totalmente interior).
* **Flood Fill (Algoritmo de Relleno):**
    * Uso de BFS (Cola) para recorrer la matriz desde el borde (0,0) y marcar todo lo que sea "exterior".
* **Immutable Data Carrier:**
    * Uso de `record Point` para garantizar que las coordenadas de las tejas no cambien accidentalmente durante el proceso.

---

## 4. Clean Code y Estilo

* **Nombres Expresivos:** `compressCoordinates`, `countExitTitles` (aunque hay un typo, debería ser `Tiles`), `isValidRect`.
* **Encapsulamiento de Complejidad:** El método `buildTempGrid` y `floodFill` ocultan la complejidad de la manipulación de matrices crudas. El método público `getBigerRectangle` es limpio y legible.
* **Manejo de Colecciones:** Uso eficiente de `TreeSet` para ordenar y eliminar duplicados automáticamente durante la compresión.

---

## 5. Justificación de Decisiones Técnicas

### ¿Por qué Compresión de Coordenadas?
El problema implica coordenadas que pueden ser muy grandes. Crear una matriz `new int[1000000][1000000]` lanzaría un `OutOfMemoryError` inmediatamente.
* **Solución:** La compresión reduce el espacio a solo las filas y columnas "interesantes" (donde hay puntos). Un mapa de 1M x 1M con 100 puntos se convierte en una grilla de aprox 200x200, que es trivial de manejar en memoria.

### Verificación de Rectángulos en $O(1)$
Para encontrar el rectángulo máximo, el algoritmo prueba combinaciones de puntos. Verificar si un rectángulo es válido recorriendo todas sus celdas (fuerza bruta) sería lentísimo
* **Solución (Prefix Sum):** Al pre-calcular las sumas, la función `countExitTitles` puede determinar instantáneamente si un área rectangular contiene alguna celda inválida (exterior) usando la fórmula de inclusión-exclusión:
    ```
    sum = total - arriba - izquierda + esquina_diagonal
    ``` 

### Definición de "Interior" (Flood Fill)
Saber si un punto arbitrario está dentro o fuera de un polígono irregular es difícil.
* **Solución:** Es más fácil definir qué es "fuera". Se crea un borde extra alrededor de los puntos comprimidos y se lanza un `FloodFill` desde (0,0). Todo lo que toque el agua es "exterior". Lo que quede seco es "interior" o "borde".