# Advent of Code – Día6

## 🧩 Descripción del problema
reto A)
El reto consiste en interpretar una hoja de ejercicios con varios problemas matemáticos dispuestos verticalmente en columnas. Cada problema contiene varios números alineados verticalmente y un operador al final que indica si deben sumarse o multiplicarse. Los problemas están separados por columnas vacías y deben leerse de forma normal para calcular cada resultado. Finalmente, se suman los resultados de todos los problemas para obtener el total general.

reto B)
En la segunda parte cambia la forma de lectura: los problemas deben interpretarse de derecha a izquierda por columnas, y cada número se forma leyendo sus dígitos de arriba hacia abajo. Esto transforma completamente los valores de cada problema y, por tanto, sus resultados. El reto exige reinterpretar correctamente la estructura visual del input antes de aplicar las operaciones y sumar los resultados finales.

---
## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Main** inicia `TrashCompactor`.
2.  **Lectura:** `TrashCompactor` lee el archivo y lo convierte en líneas (`List<String>`).
3.  **Análisis de Operadores:**
    * `Calculator` examina la **última línea** del archivo para encontrar los operadores (`+` o `*`).
4.  **Parsing Vertical:**
    * Por cada operador encontrado, se invoca `extractNumbersFromColumn`.
    * Reto A) el sistema recorre las columnas cogiendo los numeros de cada columna respecto a su operador
    * Reto B) El sistema recorre las líneas anteriores verticalmente (`getCharAt`) para construir los números asociados a ese operador.
5.  **Creación del Modelo:** Se instancian objetos `Problem` (record) que contienen los números y su operador.
6.  **Resolución:**
    * Cada `Problem` se resuelve a sí mismo (`solve`).
    * `Calculator` suma los resultados de todos los problemas y devuelve el total.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`TrashCompactor`:** Orquestación pura. Coordina la entrada (archivo) con el proceso (calculadora).
* **`Calculator`:** Responsabilidad de parsing. Traduce una representación visual (texto en grilla) a un modelo de objetos (`List<Problem>`).
* **`Problem` (Record):** Responsabilidad de dominio. Sabe exclusivamente cómo aplicar una operación matemática a una lista de números.

### O - Open/Closed Principle (OCP)
* **Interfaces:** `TrashCompactor` utiliza `Calculator`, pero este implementa la interfaz `ProblemSolver`. Esto permite cambiar la implementación del solucionador (ej. una versión que soporte división o restas) sin cambiar el código cliente, siempre que se respete el contrato.

### D - Dependency Inversion Principle (DIP)
* `TrashCompactor` depende de la abstracción `FileReader` y de la de `ProblemSolver` (campo de clase), facilitando el testeo con dobles de prueba

---

## 3. Patrones de Diseño

* **Command Pattern (Variación):**
    * El record `Problem` actúa como un objeto de comando: encapsula todos los datos necesarios (números) y la acción a realizar (el operador). El método `solve()` ejecuta el comando.
* **Data Transfer Object (DTO) / Value Object:**
    * El uso de `record Problem` implementa un objeto de valor inmutable. Dos problemas con los mismos números y operador son semánticamente idénticos.
* **Static Factory Method:**
    * `TrashCompactor.create()` oculta la complejidad de creación y cableado de dependencias internas (`ResourceFileReader`, `Calculator`).

---

## 4. Clean Code y Estilo

* **Programación Funcional:**
    * Uso extensivo de **Streams** para transformar datos. Ejemplo: `lines.stream()...collect(Collectors.joining())` para leer verticalmente.
    * Uso de `reduce` para la multiplicación: `numbers.stream()...reduce(1, (a, b) -> a * b)`.
* **Records:**
    * El uso de `record` reduce drásticamente el "ruido" visual (getters, constructores, toString) en la clase `Problem`, dejando solo la lógica de negocio visible.
* **Nombres Expresivos:**
    * `extractNumberEndingAt`, `extractNumbersFromColumn`. Los nombres de los métodos privados en `Calculator` narran paso a paso el algoritmo de parsing visual.

---

## 5. Justificación de Decisiones Técnicas

### Parsing Vertical (Simulación de Matriz)
El problema requiere leer números que están escritos verticalmente o alineados en columnas.
* **Estrategia:** En lugar de convertir la `List<String>` en un `char[][]` (matriz bidimensional), se utilizan Streams sobre la lista de strings original.
* **Código:** `map(line -> getCharAt(line, col))`.
* **Justificación:** Esto ahorra memoria y código de inicialización de matrices. Se trata la lista de líneas como una matriz virtual, accediendo a ella mediante coordenadas (índice de línea, índice de columna) bajo demanda.

### Uso de `Problem` Record
* **Decisión:** Encapsular los datos y la operación en un registro inmutable.
* **Justificación:** Elimina el riesgo de estado compartido mutable. Una vez que el parser define un problema, sus números y operador no pueden cambiar, lo que hace que el paso de cálculo sea totalmente seguro y libre de efectos secundarios.

### Estrategia de `reduce` para Multiplicación
* **Código:** `reduce(1, (a, b) -> a * b)`.
* **Justificación:** Es la forma idiomática y funcional de acumular productos. Se usa `1` como identidad (neutro multiplicativo), manejando correctamente listas vacías o de un solo elemento sin necesidad de `if/else`.