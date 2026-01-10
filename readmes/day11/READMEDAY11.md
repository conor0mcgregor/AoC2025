# Advent of Code – Día11

## 🧩 Descripción del problema
reto A)
El reto consiste en analizar un conjunto de dispositivos conectados de forma dirigida, donde los datos fluyen solo en el sentido de las conexiones indicadas. A partir de un dispositivo inicial llamado **you**, se deben encontrar todos los caminos posibles que llevan hasta el dispositivo final **out**. Cada camino es una secuencia válida de dispositivos siguiendo las conexiones definidas. El objetivo es contar cuántas rutas distintas existen desde el origen hasta la salida, explorando todas las posibilidades sin retrocesos.

reto B)
En la segunda parte, el problema se amplía a buscar rutas desde un nuevo dispositivo inicial **svr** hasta **out**, pero con una restricción adicional. Solo se consideran válidos los caminos que pasan por dos dispositivos específicos, **dac** y **fft**, en cualquier orden. El reto requiere recorrer todas las rutas posibles y filtrar aquellas que cumplen esta condición obligatoria. El objetivo final es contar cuántos caminos cumplen simultáneamente con llegar a **out** y visitar ambos dispositivos clave.

---
## 1. Metodología: Test Driven Development (TDD)

El diseño del sistema muestra una clara separación que facilita TDD:

* **Aislamiento de la Estructura de Datos:** La clase `Digraph` puede ser instanciada y poblada manualmente mediante `addDestNodeTo` sin depender de un archivo de entrada. Esto permite crear tests unitarios con grafos pequeños y controlados para verificar la lógica de conexión.
* **Verificación de Lógica de Negocio:** El método `getNumPaths` contiene la lógica compleja (pasar por "fft" y "dac"). Al estar desacoplado del parser, se pueden escribir tests que afirmen: "Dada una ruta A->fft->B->dac->C, el resultado debe ser 1".
* **Parsing Independiente:** `Reactor` puede aceptar una `List<String>`, permitiendo probar la lógica de interpretación de texto sin I/O real.

---

## Flujo de Ejecución

1.  **Main** invoca a `Reactor` con la ruta del archivo.
2.  **Construcción del Grafo (`parserPaths`):**
    * `Reactor` lee el archivo línea por línea.
    * Cada línea `origen: dest1 dest2` se parsea y se alimenta al `Digraph`.
    * **Gestión de Nodos:** Si un nodo no existe, el `Digraph` lo crea bajo demanda (`getNode`).
3. **Optimización (Memoización):**
    * Antes de calcular los caminos desde un nodo dada una combinación de estados, se consulta el mapa `memo`.
    * Si el cálculo ya se hizo para ese estado específico, se retorna el valor cacheado, evitando la explosión combinatoria.
4. **Cálculo de Rutas (`getNumPaths`):**  
   **reto A)**
    * Se inicia una búsqueda recursiva (DFS) desde "you" hacia "out".
    **reto B)**
    * Se inicia una búsqueda recursiva (DFS) desde "svr" hacia "out".
    * **Estado:** Se rastrea en cada paso si ya se ha visitado "fft" y "dac" (`passedFFT`, `passedDAC`).
    * Cuando llega al nodo final verifica si en el camino se pasó por "ffy" y "dac" para que ese camino cuente
---
## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Node`**: Estructura de datos básica. Mantiene su identidad y la lista de sus vecinos inmediatos.
* **`Digraph`**: Lógica de grafo. Gestiona la colección global de nodos y el algoritmo de recorrido (Pathfinding).
* **`Reactor`**: Builder/Parser. Su responsabilidad es orquestar la conversión de texto plano a la estructura de objetos `Digraph` y lanzar el cálculo.

### O - Open/Closed Principle (OCP)
* **Uso de Interfaces:** `Reactor` define su campo como `private PathGraph digraph;`. El uso de la interfaz `PathGraph` en las clases permite que, con una refactorización menor (inyección de dependencias), el sistema soporte diferentes implementaciones de grafos (ej. uno basado en matrices).

### D - Dependency Inversion Principle (DIP)
* `Reactor` utiliza la abstracción `FileReader` para la lectura.
* `Reactor` utiliza la abstracción `PathGraph` para su logica.

---

## 3. Patrones de Diseño

* **Builder Pattern (Simplificado):**
    * Los métodos `parserPaths`, `buildGraph` y `parserNodes` en `Reactor` actúan conjuntamente como un Director/Builder que construye paso a paso el objeto complejo `Digraph` a partir de una representación en string.
* **Memoization (Dynamic Programming):**
    * Se utiliza un `Map<String, Long> memo` en `Digraph`. La clave compuesta (`nodeOrigin + "|" + passedFFT + "|" + passedDAC`) permite almacenar resultados parciales basándose no solo en la posición, sino en el estado del recorrido (requisitos cumplidos).
* **Adjacency List:**
    * Cada `Node` contiene una `List<Node> destNodes`. Es el patrón estándar para representar grafos dispersos eficientemente.

---

## 4. Clean Code y Estilo

* **Streams:**
    * `Reactor` utiliza `Stream<String>` en `buildGraph`, lo que permite procesar las líneas de manera funcional y eficiente.
    * `Digraph` usa `mapToLong(...).sum()` para agregar resultados recursivos.
* **Inmutabilidad de Identidad:**
    * El `id` del `Node` es `final` y fundamental para `equals` y `hashCode`, garantizando la integridad en colecciones.
* **Observaciones de Naming:**
    * El código es legible, aunque hay pequeñas inconsistencias gramaticales en inglés: `dates` (debería ser `data` o `lines`), `parserPaths` (verbo `parsePaths`), y `calculedNumPaths` (`calculated`).
* **Static Factory Method:**
    * `Reactor.create()` encapsula la creación del objeto y sus dependencias internas (`ResourceFileReader`).

---

## 5. Justificación de Decisiones Técnicas

### Separación Reactor vs Digraph
* **Decisión:** Tener una clase dedicada (`Reactor`) para la lectura y construcción, separada del grafo (`Digraph`).
* **Justificación:** Separa la lógica de infraestructura (I/O, Parsing de Strings) de la lógica algorítmica y matemática. Esto permite que `Digraph` sea reutilizable en otros contextos donde el grafo no provenga de un archivo de texto, y simplifica las pruebas unitarias de los algoritmos de búsqueda.

### Gestión de Estado en la Recursión (`passedFFT`, `passedDAC`)
* **Decisión:** Pasar banderas booleanas a través de la recursión en lugar de guardar el camino completo.
* **Justificación:** Optimiza drásticamente el uso de memoria. El problema solo requiere saber *si* se pasó por ciertos nodos, no el orden exacto. Esto permite usar Programación Dinámica; si guardáramos la ruta completa, el espacio de estados sería infinito e imposible de memoizar efectivamente.