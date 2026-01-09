# Advent of Code – Día 1

## 🧩 Descripción del problema
RETO A)
El reto consiste en simular un dial circular numerado del 0 al 99 que comienza en la posición 50 y se mueve según una secuencia de rotaciones a izquierda (L) o derecha (R). Cada rotación indica cuántos pasos avanzar, teniendo en cuenta que el dial es circular. El objetivo no es obtener la posición final, sino contar cuántas veces el dial termina exactamente en 0 después de completar una rotación. Ese conteo total es la contraseña necesaria para abrir la puerta. El problema requiere manejar correctamente el comportamiento circular del dial y procesar toda la secuencia de instrucciones.

RETO B)
En la segunda parte se modifica la regla de conteo: ahora se deben contar todas las veces que el dial pasa por la posición 0, tanto al final de una rotación como durante el movimiento paso a paso. Esto implica que una sola rotación larga puede hacer que el dial pase varias veces por el 0. El reto añade complejidad al obligar a calcular cuántos cruces por 0 ocurren en cada rotación, no solo el resultado final. El objetivo sigue siendo sumar todos esos cruces para obtener la nueva contraseña.

---

## 1. Metodología: Test Driven Development (TDD)

El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante:

* **Testabilidad Aislada:** La lógica de negocio crítica (el cálculo de rotaciones y contraseñas) se ha encapsulado totalmente en la clase `Dial`. Al no tener dependencias externas (como I/O o red), esta clase permite pruebas unitarias rápidas y exhaustivas para cubrir casos borde (como el cruce por el cero).
* **Diseño Evolutivo:** La separación entre `Dial` y `DialManeger` sugiere un ciclo *Red-Green-Refactor* donde primero se resolvió la lógica matemática y posteriormente se añadió la capa de gestión de archivos.
* **Métodos Públicos vs Privados:** La exposición de métodos como `getPosition()` o `getPassword()` facilita las aserciones en los tests, mientras que la complejidad interna se mantiene privada.

---
## Flujo de ejecucion
Nombre del archivo se pasa a **`FileReader`** para trasformar a List<String  
↓  
Cada linea es un split que se le pasa a **`Dial`** y lo procesa
---

## 2. Principios SOLID

El diseño respeta los principios SOLID para garantizar un software mantenible:

### S - Single Responsibility Principle (SRP)
Cada clase tiene una única razón para cambiar:
* **`Dial`**: Responsabilidad puramente de dominio. Gestiona el estado (posición), la aritmética modular de la rotación y el cálculo acumulativo del password.
* **`DialManeger`** (Manager): Responsabilidad de orquestación. Parsea las entradas (Strings/Archivos) y coordina las acciones sobre el Dial.
* **`Main`**: Responsabilidad de punto de entrada (Entry Point) e inicio de la aplicación.

### O - Open/Closed Principle (OCP)
El sistema está abierto a la extensión pero cerrado a la modificación:
* El uso de la interfaz `FileReader` (importada) permite que `DialManeger` pueda operar con diferentes fuentes de datos en el futuro (ej. leer de una URL o base de datos) sin cambiar su lógica interna de procesamiento de giros.

### D - Dependency Inversion Principle (DIP)
* `DialManeger` depende de la abstracción `FileReader`, no de una implementación concreta en su definición de atributos.

---

## 3. Patrones de Diseño

Se han aplicado patrones tácticos para mejorar la legibilidad y el uso de la API:

* **Static Factory Method:**
    * Uso de `Dial.create()` y `DialManeger.create()`.
    * **Beneficio:** Oculta los constructores, controla la instanciación y aporta semántica al código cliente.
* **Fluent Interface (Method Chaining):**
    * Los métodos en `DialManeger` retornan `this` (`return this;`).
    * **Beneficio:** Permite encadenar configuraciones y acciones de forma declarativa (ej. `manager.ordersOfSpins(...).getPosition()`).
* **Facade (Fachada):**
    * `DialManeger` actúa como una fachada simple para el `Main`, ocultando la complejidad matemática del `Dial` y la gestión de flujos de lectura.

---

## 4. Clean Code y Estilo

* **Naming Semántico:** Variables y métodos autoexplicativos (`rotate`, `isPlusRotation`, `countClicksInZero`).
* **Small Methods:** Descomposición de problemas grandes en funciones pequeñas y reutilizables.
    * *Ejemplo:* `countClicksInZero` delega en `countRightCliks` y `countLeftCliks`, haciendo la lectura del algoritmo casi textual.
* **Evitar Números Mágicos (Mejora Potencial):** Aunque el código es limpio, el valor `100` (tamaño del dial) se repite, siendo candidato a convertirse en una constante (`DIAL_SIZE`).

---

## 5. Justificación de Decisiones Técnicas

### Gestión de Flujos de Datos (Java Streams)
Se optó por el uso de **Streams** en lugar de bucles tradicionales `for/while` para procesar las órdenes:
```java
br.lines().forEach(this::spin);