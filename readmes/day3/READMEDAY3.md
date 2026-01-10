# Advent of Code – Día 3

## 🧩 Descripción del problema
reto A)
El reto consiste en procesar varias filas de baterías, donde cada fila representa un banco con valores de 1 a 9. En cada banco se deben seleccionar exactamente dos baterías, manteniendo su orden original, para formar el mayor número posible de dos dígitos. Para cada línea se calcula ese máximo y luego se suman todos los valores obtenidos. El problema se centra en elegir de forma óptima los dos dígitos más grandes respetando su posición.

reto B)
En la segunda parte, la regla cambia y ahora se deben seleccionar exactamente doce baterías por cada banco para formar un número de doce dígitos lo más grande posible. Esto implica descartar ciertos dígitos menos convenientes manteniendo el orden del resto. El reto aumenta en complejidad al requerir decisiones más cuidadosas sobre qué baterías excluir para maximizar el valor final. Finalmente, se suman los valores máximos obtenidos de todos los bancos para calcular el resultado total.

---

## 1. Metodología: Test Driven Development (TDD)

El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Main** llama a `BatteryBank.create()`, ensamblando las piezas.
2.  **BatteryBank** abre el archivo y lee línea por línea.
3.  Cada línea (String) se pasa a la estrategia **`BatteryParser`** (`MaxJoltageParser`).
4.  **MaxJoltageParser** ejecuta un algoritmo *greedy* (codicioso):
    * Busca el dígito más alto posible en una ventana válida.
    * Repite el proceso para los 12 dígitos requeridos.
5.  El resultado parcial se convierte a `long`, se devuelve a **BatteryBank** y se acumula en la suma total.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Main`**: Punto de entrada.
* **`BatteryBank`**: Orquestador de I/O y acumulador. No sabe cómo calcular el "max joltage", solo sabe que debe sumar lo que el parser le devuelva.
* **`MaxJoltageParser`**: Responsabilidad algorítmica. Se encarga únicamente de la lógica de selección de dígitos y conversión a número. No sabe de archivos.
* **`BatteryParser` (Interfaz)**: Define el contrato de conversión.

### O - Open/Closed Principle (OCP)
* La clase `BatteryBank` depende de la interfaz `BatteryParser`. Si el requerimiento cambia (ej: "Ahora necesitamos el número *menor* posible" o "La suma de todos los dígitos"), se puede crear una clase `MinJoltageParser` que implemente la interfaz e inyectarla sin modificar ni una línea de `BatteryBank`.

### D - Dependency Inversion Principle (DIP)
*`BatteryBank` depende de abstracciones (`FileReader`, `BatteryParser`) en su constructor y campos, no de implementaciones concretas.
### L - Liskov Substitution Principle (LSP)
* `MaxJoltageParser` cumple correctamente el contrato de `BatteryParser`. Cualquier implementación de esta interfaz podría sustituirse sin romper el funcionamiento del acumulador en `BatteryBank`.

---

## 3. Patrones de Diseño

* **Strategy Pattern:**
    * `BatteryParser` define una familia de algoritmos (parsing/cálculo). `MaxJoltageParser` es una estrategia concreta encapsulada. `BatteryBank` usa la estrategia sin conocer los detalles.
* **Static Factory Method:**
    * `BatteryBank.create()` encapsula la complejidad de creación y configuración de los objetos (configura los 12 dígitos y el lector de archivos).
* **Object Mother / Result Object (Inner Class):**
    * Uso de `private static class MaxDigitResult` para retornar múltiples valores (valor e índice) desde un método privado, evitando el uso de arrays confusos o variables globales temporales.

---

## 4. Clean Code y Estilo

* **Nombres Intencionales:** `digitsNeeded`, `searchLimit`, `extractMaxDigits`. El código explica *qué* está haciendo.
* **Small Methods:** El algoritmo complejo se divide en pasos lógicos:
    1.  `extractMaxDigits`: Bucle principal.
    2.  `maxIndexIn`: Calcula los límites de búsqueda.
    3.  `findMaxDigitInRange`: Busca el máximo local.
    4.  `convertToLong`: Reduce el array a un número.
* **Fail Fast:** El constructor de `MaxJoltageParser` valida que `targetDigits` sea positivo lanzando una excepción inmediata (`IllegalArgumentException`), protegiendo la integridad del objeto.

---

## 5. Justificación de Decisiones Técnicas

### Algoritmo Greedy (Codicioso) vs Fuerza Bruta
El problema pide encontrar el mayor número de 12 dígitos respetando el orden.
* **Decisión:** En lugar de generar todas las combinaciones posibles (lo cual sería computacionalmente inviable para strings largos), se usa un enfoque **Greedy**:
    * Para encontrar el primer dígito, busca el número más grande posible en el rango inicial que deje suficientes caracteres a la derecha para completar los 11 restantes.
    * Una vez encontrado, avanza el puntero y repite.
* **Justificación:** Ya que para que un numero sea grande priorisamos los primeros digitos. Reduce la complejidad de Exponencial a Lineal (proporcional a la longitud del string * longitud del objetivo).

### Estructuras de Datos (`int[]` vs `String`)
* Se usa un `int[]` (`result`) para ir almacenando los dígitos encontrados.
* **Justificación:** Es más eficiente en memoria y velocidad que la concatenación repetitiva de Strings (`String += val`). Al final, se reduce matemáticamente a un `long` usando `Arrays.stream().reduce()`.

### Tipos de Datos (`long`)
* El método devuelve `long`.
* **Justificación:** Un número de 12 dígitos (el `JOLTAGE_DIGITS` por defecto) puede superar el valor máximo de un entero de 32 bits (`Integer.MAX_VALUE` es aprox 2 x 10^9, es decir, 10 dígitos). `long` es obligatorio para evitar desbordamiento numérico (overflow).

### Manejo de Archivos
* `reader.lines()
                .mapToLong(batteryParser::parse)
                .sum();`.
* Usamos Streams para fasilitar la legibilidad del codigo
