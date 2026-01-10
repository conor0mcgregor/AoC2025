# Advent of Code – Día 2

## 🧩 Descripción del problema
reto A)
El reto plantea analizar varios rangos de identificadores numéricos para detectar IDs inválidos introducidos por error. Un ID es inválido si está formado por una secuencia de dígitos repetida exactamente dos veces, como 11, 6464 o 123123. Se deben recorrer todos los rangos dados, identificar los números que cumplen ese patrón y sumar sus valores. El problema requiere trabajar con números grandes, evitar ceros a la izquierda y aplicar comprobaciones de patrones sobre la representación decimal de cada ID.

reto B)
En la segunda parte se amplía la definición de ID inválido: ahora basta con que una secuencia de dígitos se repita dos o más veces, sin límite superior. Esto incluye repeticiones largas como 1111111 o 1212121212. El reto exige detectar patrones de repetición más generales dentro de cada número y volver a recorrer todos los rangos para sumar los nuevos IDs inválidos. La complejidad aumenta al tener que considerar distintas longitudes de patrón y múltiples repeticiones posibles.

---

## 1. Metodología: Test Driven Development (TDD)

El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de ejecución

Nombre del archivo se pasa a **`IDValidator`** para leer la línea de rangos  
↓  
La cadena de rangos se divide (split) y cada segmento se transforma en un objeto **`Range`** ↓  
Se genera un **`LongStream`** con todos los números entre el inicio y fin del rango  
↓  
Cada número se evalúa en **`IDValidator`** para detectar patrones repetitivos

---

## 2. Principios SOLID

El diseño respeta los principios SOLID para garantizar un software mantenible y robusto:

### S - Single Responsibility Principle (SRP)
Cada componente tiene una responsabilidad clara:
* **`Range` (Record)**: Responsabilidad de transporte y parsing. Solo se encarga de interpretar una cadena de texto ("min-max") y convertirla en un par de valores numéricos utilizables.
* **`IDValidator`**: Responsabilidad de lógica de negocio. Orquesta la lectura, genera los flujos de números y determina si un ID es inválido según el patrón.
* **`Main`**: Responsabilidad de punto de entrada (Entry Point).

### O - Open/Closed Principle (OCP)
El sistema está diseñado para permitir cambios sin modificar el código base excesivamente:
* El algoritmo de detección `findPatron` es genérico (iterativo). Si el problema cambiara para detectar patrones que se repiten 3 veces en lugar de 2, o cualquier cantidad `N` veces (como pide el reto B), la lógica actual ya lo soporta sin necesidad de reescribir la estructura, simplemente iterando sobre los divisores de la longitud.

### D - Dependency Inversion Principle (DIP)
* `IDValidator` depende de la abstracción `FileReader`, recibiéndola en su constructor privado. Esto desacopla la validación de la fuente de datos (disco, red, memoria).

---

## 3. Patrones de Diseño

Se han aplicado patrones tácticos para mejorar la legibilidad y la seguridad de tipos:

* **Static Factory Method:**
    * Uso de `IDValidator.create()` y `Range.with()`.
    * **Beneficio:** Aporta semántica (`Range.with("1-10")` es más legible que un constructor) y encapsula la lógica de creación (como la instanciación del `ResourceFileReader` por defecto).
* **Value Object:**
    * Implementación de `Range` como un Java Record.
    * **Beneficio:** Garantiza inmutabilidad, igualdad semántica automática (dos rangos con los mismos valores son iguales) y reduce el *boilerplate*.
* **Strategy (Implícito):**
    * La inyección de `FileReader` actúa como una estrategia de lectura, permitiendo cambiar el origen de los datos en tiempo de test.

---

## 4. Clean Code y Estilo

* **Naming Semántico:** Los métodos cuentan una historia: `sumAllInvalidIds` -> `sumInvalidIdInStrRange` -> `isInvalidId`.
* **Small Methods:** El algoritmo de detección de patrones, que es complejo, se ha desglosado en pasos atómicos:
    * `conteinPatron`: Prepara los datos.
    * `findPatron`: Itera sobre posibles longitudes de patrón.
    * `existPatronIn`: Verifica si un patrón específico se repite.
* **Uso de Java Records:** El uso de `record Range` limpia el código de *getters*, *setters* y constructores verbosos.

---

## 5. Justificación de Decisiones Técnicas

### Gestión de Flujos de Datos (LongStream)
Se utiliza `LongStream.rangeClosed(start, end)` para generar los números a verificar.
* **Justificación:** Dado que los rangos pueden ser enormes (millones de IDs), usar una `List<Long>` llenaría la memoria (Heap). El Stream evalúa los números de forma perezosa (*lazy evaluation*), procesando uno a uno sin almacenarlos todos, lo cual es crítico para el rendimiento y la estabilidad de memoria.

### Algoritmo de Detección (String vs Math)
Para verificar si un número tiene un patrón repetido (ej. 123123), se convierte el número a `String` y se usa manipulación de cadenas (`substring`, `repeat`).
* **Justificación:** Aunque operar matemáticamente (división y módulo) es más rápido en CPU puro, la lógica para detectar repeticiones de *longitud variable* (Reto B) es mucho más compleja de implementar matemáticamente. La conversión a String simplifica drásticamente la lectura y mantenimiento del código (`pattern.repeat(repetitions).equals(strId)`), con un costo de rendimiento aceptable para este contexto.

### Estructuras de Datos
* **`long`**: Se usa `long` en lugar de `int` para los IDs y sumas.
* **Justificación:** El problema menciona "números grandes" y la suma de estos IDs inválidos probablemente exceda el límite de `Integer.MAX_VALUE` (2 mil millones), por lo que `long` es necesario para evitar *overflow*.