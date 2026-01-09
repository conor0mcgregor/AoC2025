# Advent of Code – Día4 

## 🧩 Descripción del problema
reto A)
El reto consiste en analizar una cuadrícula que representa la ubicación de rollos de papel y espacios vacíos. Un rollo es accesible por un montacargas si en sus ocho posiciones adyacentes hay menos de cuatro rollos de papel. Se debe revisar toda la cuadrícula y contar cuántos rollos cumplen esta condición inicial. El problema requiere evaluar correctamente los vecinos de cada celda y manejar los bordes del mapa.

reto B)
En la segunda parte, el proceso se vuelve iterativo: cada rollo accesible puede retirarse del mapa, lo que puede permitir que nuevos rollos se vuelvan accesibles. Se repite el proceso de detección y eliminación hasta que no queden rollos que cumplan la condición. El objetivo es contar el total acumulado de rollos retirados durante todo el proceso. El reto combina simulación paso a paso y actualización dinámica de la cuadrícula.

---

## 1. Metodología: Test Driven Development (TDD)

El enfoque TDD es evidente en la separación de responsabilidades:

* **Testabilidad de la Simulación:** La clase `CounterAccessRolls` no depende del sistema de archivos. Recibe un `List<String>` en su método de fábrica `create`. Esto permite escribir tests unitarios con grillas pequeñas (ej. 3x3) definidas en código para verificar casos específicos:
    * Rolls aislados (0 vecinos).
    * Rolls bloqueados (4+ vecinos).
    * Reacción en cadena (al eliminar uno, se desbloquea otro).
* **Aislamiento de I/O:** `PrintingDepartment` maneja la lectura del archivo y la transformación a lista, delegando la lógica compleja al parser.

---

## Flujo de Ejecución

1.  **Main** invoca a `PrintingDepartment` con la ruta del archivo.
2.  **PrintingDepartment** lee el archivo y lo convierte en una `List<String>` (la grilla inicial).
3.  Se instancia **`CounterAccessRolls`**, haciendo una copia defensiva de la grilla.
4.  **Bucle de Simulación (`parse`):**
    * Recorre cada celda de la grilla.
    * Si encuentra un `@` y tiene menos de 4 vecinos (`isAccessible`), lo elimina y marca `change = true`.
5.  El bucle se repite indefinidamente mientras haya cambios (`while(change)`), simulando el efecto cascada.
6.  Retorna el total de elementos eliminados.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`PrintingDepartment`**: Orquestador. Su única responsabilidad es cargar los datos y coordinar el proceso.
* **`CounterAccessRolls`**: Motor de simulación. Contiene la lógica de vecindad (8 direcciones), modificación de la grilla y conteo.
* **`DepartmentParser`**: Define el contrato de procesamiento.

### O - Open/Closed Principle (OCP)
* `PrintingDepartment` depende de la interfaz `DepartmentParser`. Si mañana la regla cambia (ej. "contar solo los que tienen 8 vecinos"), se puede implementar una nueva clase `StrictAccessParser` sin modificar la clase orquestadora.

### D - Dependency Inversion Principle (DIP)
* **Acierto:** `PrintingDepartment` usa la abstracción `FileReader` como atributo.
---

## 3. Patrones de Diseño

* **Factory Method:**
    * Uso de `create()` en `PrintingDepartment` y `CounterAccessRolls` para encapsular la instanciación.
* **Strategy Pattern:**
    * La interfaz `DepartmentParser` actúa como una estrategia. `PrintingDepartment` ejecuta una estrategia de parsing sin conocer los detalles algorítmicos.
* **Simulation / State Loop:**
    * El método `parse` implementa un bucle de estado estable. No es un algoritmo de una sola pasada, sino una simulación iterativa que se detiene cuando el sistema alcanza el equilibrio (ningún cambio en una iteración completa).

---

## 4. Clean Code y Estilo

* **Nombres Descriptivos:** `isAccessible`, `countNeighbors`, `removeRoll`. El código se lee como una descripción del problema.
* **Descomposición Funcional:**
    * El problema complejo (recorrer, verificar, borrar, repetir) se rompe en métodos privados pequeños.
    * `countNeighbors` abstrae la complejidad de verificar los 8 vecinos (vecindad de Moore) usando arrays de offsets (`rowOffsets`, `colOffsets`), evitando 8 sentencias `if` anidadas.
* **Defensive Copy:**
    * `this.grid = new ArrayList<>(grid);`. El parser crea su propia copia de la lista para no mutar los datos originales del llamante, previniendo efectos secundarios indeseados.

---

## 5. Justificación de Decisiones Técnicas

### Algoritmo de Cascada (`while(change)`)
El código usa un bucle `while (change)` que envuelve los bucles `for`.
* **Justificación:** El problema implica que al eliminar un elemento ("roll"), sus vecinos pierden un vecino. Esto puede hacer que un elemento que antes estaba "bloqueado" (>= 4 vecinos) ahora se vuelva "accesible" (< 4 vecinos). Se requiere reevaluar la grilla repetidamente hasta que no se puedan eliminar más elementos.

### Estructura de Datos (`List<String>` vs `char[][]`)
Se manipula una lista de Strings. Para "borrar" un elemento, se crea un nuevo String: `substring(0, col) + '.' + substring(...)`.
* **Justificación:** Aunque `char[][]` sería más eficiente en CPU (mutable in-place), el uso de `List<String>` simplifica la integración con la lectura de archivos (que devuelve líneas) y mantiene la inmutabilidad de las filas individuales hasta que es estrictamente necesario cambiarlas, reduciendo errores de punteros.

### Vecindad de Moore (8 Direcciones)
Uso de arrays de desplazamiento `{-1, 0, 1}`.
* **Justificación:** Es la forma estándar y más limpia de iterar sobre celdas adyacentes en una matriz, evitando código duplicado y propenso a errores tipográficos. Verifica diagonales, verticales y horizontales en un solo bucle.