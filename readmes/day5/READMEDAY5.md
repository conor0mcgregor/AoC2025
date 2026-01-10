# Advent of Code – Día4 

## 🧩 Descripción del problema
reto A)
El reto consiste en procesar una lista de rangos de IDs de ingredientes frescos y una lista separada de IDs disponibles. Un ID se considera fresco si pertenece al menos a uno de los rangos, los cuales pueden solaparse. Se debe comprobar cada ID disponible y contar cuántos son frescos según esos rangos. El problema requiere manejar rangos inclusivos y evaluar correctamente la pertenencia de cada valor.

reto B)
En la segunda parte se ignora la lista de IDs disponibles y se trabaja únicamente con los rangos de frescura. El objetivo es calcular cuántos IDs distintos están cubiertos por al menos uno de los rangos, teniendo en cuenta los solapamientos entre ellos. Para resolverlo es necesario unir o combinar rangos superpuestos y contar la cantidad total de valores únicos resultantes. El reto se centra en el manejo eficiente de intervalos grandes.

---

## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Main** invoca a `CounterFreshId` para obtener IDs disponibles.
2.  **Lectura y Parsing (`saveRanges`):**
    * Se lee el archivo línea a línea.
    * Se utiliza `takeWhile` para leer solo la primera sección del archivo (rangos "A-B") hasta encontrar una línea vacía.
3.  **Gestión de Rangos (`RangesManager`):**
    * Cada nuevo rango se añade a una lista.
    * **Auto-optimización:** Al añadir, se llama a `upDateRanges`, que ordena la lista y fusiona recursivamente cualquier solapamiento. Esto mantiene la lista siempre en su estado más compacto posible.
4.  **Cálculo Final:**
    * reto A) verifica todos los Ids comprobando si estan dentro de los rangos
    * reto B) `getSizeRanges()` suma el tamaño (`b - a + 1`) de los rangos ya fusionados y disjuntos.
    
---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Range` (Record):** Estructura de datos pura. Solo sabe si un número está dentro de él y cuál es su tamaño.
* **`RangesManager`:** "Cerebro" matemático. Su única responsabilidad es mantener la integridad de la colección de rangos (ordenar y fusionar). No sabe de archivos ni de parsing.
* **`CounterFreshId`:** Orquestador de I/O. Sabe cómo leer el formato específico del archivo de entrada y delegar los datos al Manager.

### O - Open/Closed Principle (OCP)
* El sistema utiliza la interfaz `RangesIdParser`. Si se necesitara una estrategia diferente para contar IDs (ej. "contar solo pares" o "leer desde base de datos"), se podría implementar una nueva clase sin tocar `Main` ni `RangesManager`.

### D - Dependency Inversion Principle (DIP)
* `CounterFreshId` depende de `FileReader`, permitiendo *mockear* la lectura del disco en los tests de integración.

---

## 3. Patrones de Diseño
* **Factory Method:**
    * Uso de `create()` en `CounterFreshId` para encapsular la instanciación.
* **Composite / Manager:**
    * `RangesManager` encapsula una lista de objetos `Range` y expone operaciones de alto nivel (`addRange`, `getSizeRanges`) que ocultan la complejidad de la gestión interna de la lista.
* **Value Object:**
    * Uso de `record Range(long a, long b)`. Es inmutable y su identidad se basa en sus valores, perfecto para representar intervalos matemáticos.
* **Recursive Optimization:**
    * El método `unify` llama recursivamente a `upDateRanges` después de una fusión. Esto asegura que si una fusión crea una nueva oportunidad de fusión (efecto cascada), se resuelva inmediatamente.

---

## 4. Clean Code y Estilo

* **Nombres Expresivos:** `isUnifiable`, `examine`, `isInside`. El código revela su intención.
* **Java Streams Modernos:**
    * Uso de `takeWhile` en el parser para manejar secciones de archivos delimitadas por líneas vacías.
    * Uso de `mapToLong(Range::getSize).sum()` para cálculos agregados limpios.
---

## 5. Justificación de Decisiones Técnicas

### Manejo de Rangos vs Sets
El problema trata con IDs que pueden ser números muy grandes (tipo `long`).
* **Decisión:** Almacenar objetos `Range` (inicio, fin) en lugar de todos los números individuales en un `HashSet`.
* **Justificación:** Un rango como `0-1,000,000,000` ocuparía gigabytes de RAM si guardáramos cada número. Como `Range`, ocupa solo 16 bytes (dos `longs`). Esta es la única solución viable para grandes volúmenes de datos continuos.

### Algoritmo de Fusión (Merge Intervals)
* **Lógica:** `range1.b() >= range2.a()`.
* **Justificación:** Para fusionar intervalos eficientemente, primero deben estar **ordenados** por su inicio. `RangesManager` impone este orden (`ranges.sort`). Una vez ordenados, solo es necesario comparar el final del actual con el inicio del siguiente para detectar solapamientos, reduciendo la complejidad de comparación cuadrática a lineal (amortizada).

### Uso de `long`
* Se utiliza `long` en todo el dominio (`Range`, `CounterFreshId`).
* **Justificación:** Los identificadores y las sumas totales probablemente excedan `Integer.MAX_VALUE` (2^31 - 1). El uso de `int` provocaría desbordamiento numérico (resultados negativos incorrectos).