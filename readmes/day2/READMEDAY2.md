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

1. **Main** delega la ejecución a **`GiftShop`**, pasando el nombre del archivo.
2. **`GiftShop`** utiliza `FileReader` para obtener la cadena de rangos cruda.
3. La cadena se divide (split) y cada segmento se pasa al componente **`IDVerifier`** (`IDValidator`).
4. **`IDValidator`** transforma el string en un objeto **`Range`**.
5. Se genera un **`LongStream`** con los números del rango y se evalúan buscando patrones repetitivos.

---

## 2. Principios SOLID

El diseño ha mejorado significativamente su adherencia a SOLID con la última refactorización:

### S - Single Responsibility Principle (SRP)
La separación de responsabilidades es ahora estricta:
* **`GiftShop`**: Responsabilidad de **Orquestación e Infraestructura**. Maneja la lectura del archivo (`FileReader`) y coordina el flujo de datos hacia el validador.
* **`IDValidator`**: Responsabilidad de **Lógica de Dominio**. Contiene exclusivamente los algoritmos matemáticos y de cadenas para detectar patrones. No sabe nada de archivos.
* **`Range`**: Responsabilidad de **Datos**. Transporte y parsing de "min-max".

### O - Open/Closed Principle (OCP)
* El sistema depende de la interfaz `IDVerifier`. Si mañana se requiere una nueva regla de validación (ej. "IDs palíndromos"), se puede crear una clase `PalindromeVerifier` que implemente la interfaz, sin necesidad de modificar la lógica de lectura en `GiftShop`.

### D - Dependency Inversion Principle (DIP)
* `GiftShop` (alto nivel) depende de la abstracción `FileReader` para I/O y de la interfaz `IDVerifier` para la lógica, invirtiendo el control y aislando el dominio.

### I - Interface Segregation Principle (ISP)
* Se ha introducido `IDVerifier`, que expone un único método necesario (`sumInvalidIdInStrRange`), evitando que los clientes dependan de métodos internos del validador.

---

## 3. Patrones de Diseño

* **Facade (Fachada):**
    * `GiftShop` actúa como una fachada que simplifica el sistema para el `Main`. El cliente solo llama a `sumInvalidIdsFromFile` sin preocuparse por rangos, streams o validadores internos.
* **Static Factory Method:**
    * Uso de `GiftShop.create()` e `IDValidator.create()`.
    * **Beneficio:** Oculta los constructores y permite en el futuro inyectar dependencias o devolver subclases sin cambiar el código cliente.
* **Strategy Pattern:**
    * `GiftShop` delega la validación en un objeto `IDVerifier`. `IDValidator` es la estrategia concreta actual para validar IDs mediante patrones repetitivos.

---

## 4. Clean Code y Estilo

* **Naming Semántico:** `conteinPatron`, `existPatronIn`. Los nombres reflejan el "qué" hace el código.
* **Small Methods:** `IDValidator` descompone el problema complejo en pasos pequeños:
    1.  `isInvalidId` -> Llama a detección.
    2.  `findPatron` -> Itera longitudes posibles.
    3.  `existPatronIn` -> Verifica una longitud específica con `String.repeat()`.
* **Inmutabilidad:** `Range` sigue siendo un `record`, garantizando seguridad en el paso de mensajes.

---

## 5. Justificación de Decisiones Técnicas

### Separación de `GiftShop` vs `IDValidator`
* **Decisión:** Mover la lectura de archivos fuera de `IDValidator`.
* **Justificación:** Mezclar lógica de negocio (matemáticas/patrones) con infraestructura (I/O) hace que el código sea rígido y difícil de probar. Ahora `IDValidator` es una función pura (misma entrada -> misma salida) sin efectos secundarios externos.

### Algoritmo de Detección (String Manipulation)
* **Código:** `pattern.repeat(repetitions).equals(strId)`.
* **Justificación:** Para el Reto B (repeticiones arbitrarias), la manipulación de Strings es superior a la aritmética modular. Permite verificar visualmente si un bloque de texto se repite N veces exactas para cubrir el ID completo, cubriendo casos como "121212" (patrón 12, rep 3) o "123123" (patrón 123, rep 2) con un solo algoritmo genérico.

### Streams para Rangos Grandes
* **Uso:** `LongStream.rangeClosed(...)`.
* **Justificación:** Esencial para eficiencia de memoria. Evita crear colecciones intermedias para rangos de millones de números, procesando cada ID bajo demanda.