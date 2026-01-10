# Advent of Code – Día12

## 🧩 Descripción del problema
El problema consiste en comprobar si un conjunto de regalos con formas irregulares puede colocarse dentro de regiones rectangulares bajo los árboles de Navidad.  
Primero se te da una lista de formas estándar de regalos, descritas como patrones en una cuadrícula donde # representa parte sólida del regalo y . espacio vacío. Cada forma tiene un índice y puede rotarse y reflejarse, pero siempre debe alinearse con la cuadrícula.  
Después, se listan varias regiones rectangulares, cada una con:  
Su tamaño (ancho × alto).
La cantidad requerida de regalos de cada forma.  
Las reglas son:  
* Los regalos no pueden solaparse en sus partes #.  
* Los espacios. no bloquean otros regalos.  
* No se pueden apilar regalos.  
* Todos deben colocarse completamente dentro de la región.  
* Para cada región, el objetivo es determinar si existe al menos una disposición válida que permita colocar exactamente todos los regalos requeridos cumpliendo las reglas.
---
## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Parsing (`PuzzleInput`):**
    * Se lee el archivo completo.
    * Se separan los bloques de texto: los primeros definen los tipos de regalos (`Present`) y el último define los árboles/regiones (`Tree`).
2.  **Expansión de Instancias (`Tree`):**
    * Para cada árbol, se convierte la lista de "cantidad requerida" (`presentCounts`) en una lista plana de objetos `Present` repetidos, listos para ser colocados uno a uno.
3.  **Filtrado Rápido (Heurísticas):**
    * Antes de intentar colocar nada, `PresentPacker` verifica si es matemáticamente posible:
        * ¿Hay suficientes píxeles totales? (`hasEnoughPixels`).
        * ¿Caben los regalos en una estimación optimista de slots? (`fitsWithOptimisticEstimate`).
4.  **Backtracking (`tryPlaceAllPresents`):**
    * El algoritmo intenta colocar el primer regalo en la primera posición disponible.
    * Si encaja, clona el tablero y llama recursivamente para el siguiente regalo.
    * Si llega a un callejón sin salida, retrocede (backtrack) y prueba la siguiente posición o rotación.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Shape`**: Responsabilidad geométrica pura. Solo sabe representar una matriz booleana y transformarla (rotar/voltear).
* **`Present`**: Gestión de variantes. Agrupa todas las permutaciones válidas de una forma.
* **`PresentPacker`**: Motor de resolución. No conoce el formato del archivo ni la definición del árbol, solo sabe encajar formas en una matriz `boolean[][]`.

### O - Open/Closed Principle (OCP)
* **Inmutabilidad:** El uso de `record` para `Shape`, `Present` y `Tree` hace que las estructuras de datos sean cerradas a la modificación pero abiertas a ser usadas por diferentes algoritmos de empaquetado.

### L - Liskov Substitution Principle (LSP)
* Aunque no hay herencia compleja, el diseño respeta la consistencia de tipos. `Shape` se comporta de manera predecible en todos los contextos de rotación.

---

## 3. Patrones de Diseño y Algoritmos

* **Backtracking (Vuelta Atrás):**
    * Implementado en `tryPlaceAllPresents`. Es el patrón estándar para resolver rompecabezas de "tiling" o "packing", explorando el árbol de decisiones en profundidad (DFS).
* **Flyweight / Caching:**
    * `Present` calcula `addRotatedShapes` y `addFlipped` una sola vez en el constructor y almacena la lista `shapeRotations`. El empaquetador reutiliza estas instancias pre-calculadas miles de veces durante la recursión, ahorrando CPU masivamente.
* **Prototype (Clonación):**
    * Para mantener la inmutabilidad del estado en la recursión, se utiliza clonación manual de la matriz: `boolean[][] newArea = cloneArea(area)`. Esto permite que cada rama de la recursión tenga su propio "universo" sin afectar a las otras ramas.

---

## 4. Clean Code y Estilo

* **Guard Clauses & Fail Fast:**
    * El método `canFitAllPresents` descarta inmediatamente casos imposibles (área insuficiente) antes de iniciar el costoso proceso recursivo.
* **Primitive Obsession (Evitada):**
    * En lugar de pasar `List<String>` o `char[][]` por todo el programa, se parsean inmediatamente a objetos de dominio (`Shape`, `boolean[][]`).
* **Streams:**
    * Uso elegante de Streams para la expansión de regalos: `IntStream.range(...).mapToObj(...).flatMap(...)`.

---

## 5. Justificación de Decisiones Técnicas

### Uso de `boolean[][]` para el Tablero
* **Decisión:** Representar el área de empaquetado como una matriz de primitivos booleanos (`boolean[][]`).
* **Justificación:** Es la estructura más eficiente en memoria y velocidad para detección de colisiones en Java. Verificar si un espacio está ocupado es una operación `O(1)` de acceso directo al array, mucho más rápido que buscar en un `Set<Point>` o usar objetos complejos.

### Pre-cálculo de Rotaciones (`Present`)
* **Decisión:** Generar las 8 posibles orientaciones (4 rotaciones x 2 reflexiones) al inicio.
* **Justificación:** En el algoritmo de backtracking, se intentará colocar la misma pieza millones de veces. Recalcular la rotación de la matriz en cada intento haría la solución inviable por tiempo. Cachear las variantes reduce la complejidad interna del bucle principal.

### Clonación de Matriz en Recursión
* **Decisión:** `cloneArea` en cada paso recursivo exitoso.
* **Justificación:** Aunque consume más memoria que "marcar/desmarcar", simplifica drásticamente la lógica y evita errores de estado compartido difíciles de depurar. Dado que el tamaño de los "árboles" (regiones) suele ser pequeño en estos retos, el overhead de memoria es aceptable a cambio de la seguridad del código.