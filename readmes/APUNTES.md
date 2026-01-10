# Principios SOLID

Los principios **SOLID** son un conjunto de cinco principios de diseño orientado a objetos cuyo objetivo es mejorar la **calidad del software**, favoreciendo un código más **mantenible, extensible, reutilizable y testable**.

Estos principios no son reglas estrictas, sino **guías de diseño** que ayudan a tomar mejores decisiones arquitectónicas.

---

## 🧱 ¿Qué significa SOLID?

SOLID es un acrónimo que representa cinco principios fundamentales:

- **S**: Single Responsibility Principle
- **O**: Open/Closed Principle
- **L**: Liskov Substitution Principle
- **I**: Interface Segregation Principle
- **D**: Dependency Inversion Principle

---

## 1️⃣ Single Responsibility Principle (SRP)

**Una clase debe tener una única razón para cambiar.**

### Explicación
Cada clase o módulo debe encargarse de **una sola responsabilidad** dentro del sistema.  
Si una clase tiene más de un motivo para cambiar, es señal de que está asumiendo demasiadas funciones.

### Beneficios
- Mayor cohesión
- Menor acoplamiento
- Código más fácil de entender y mantener

### Ejemplo conceptual
Separar:
- Lógica de negocio
- Gestión de entrada
- Presentación de resultados

en lugar de concentrarlo todo en una sola clase.

---

## 2️⃣ Open/Closed Principle (OCP)

**Las entidades de software deben estar abiertas para extensión, pero cerradas para modificación.**

### Explicación
El comportamiento del sistema debe poder ampliarse **sin modificar el código existente**, evitando introducir errores en funcionalidades ya probadas.

Esto se logra mediante:
- Abstracciones
- Interfaces
- Polimorfismo

### Beneficios
- Mayor estabilidad del código
- Facilita la evolución del sistema
- Reduce el riesgo de regresiones

---

## 3️⃣ Liskov Substitution Principle (LSP)

**Las clases derivadas deben poder sustituir a sus clases base sin alterar el comportamiento esperado del sistema.**

### Explicación
Si una clase implementa una interfaz o hereda de otra, debe **respetar el contrato** definido.  
El código que usa la abstracción no debería notar la diferencia al cambiar la implementación.

### Beneficios
- Uso seguro del polimorfismo
- Comportamiento predecible
- Código más robusto

---

## 4️⃣ Interface Segregation Principle (ISP)

**Es mejor tener muchas interfaces pequeñas y específicas que una interfaz grande y genérica.**

### Explicación
Las clases no deberían verse obligadas a implementar métodos que no necesitan.  
Las interfaces deben estar enfocadas a un propósito concreto.

### Beneficios
- Menor acoplamiento
- Mayor claridad
- Implementaciones más simples

---

## 5️⃣ Dependency Inversion Principle (DIP)

**Los módulos de alto nivel no deben depender de módulos de bajo nivel, sino de abstracciones.**

### Explicación
El código debe depender de **interfaces o abstracciones**, no de implementaciones concretas.  
Esto permite cambiar detalles técnicos sin afectar a la lógica principal.

### Beneficios
- Mayor flexibilidad
- Facilita las pruebas unitarias
- Reduce dependencias rígidas

---
Aquí está el documento simplificado:

---
# Patrones de diseño

## 🏭 Static Factory Method

**Descripción:** Patrón creacional que utiliza un método estático para crear instancias de una clase en lugar de exponer directamente sus constructores, permitiendo nombres significativos que describan la intención de la creación.  
**Ejemplo:** `DialManager.create();`

---

## 🔗 Fluent Interface (Method Chaining)

**Descripción:** Patrón que permite encadenar llamadas a métodos devolviendo el propio objeto (`this`), creando una API más expresiva y declarativa cercana al lenguaje natural.  
**Ejemplo:** `order.addItem().setPrice().confirm();`

---

## 🪟 Facade (Fachada)

**Descripción:** Patrón que proporciona una interfaz simple y unificada para acceder a un conjunto de subsistemas más complejos, permitiendo que el cliente interactúe únicamente con la fachada sin conocer la complejidad interna.  
**Ejemplo:** `DialManager`

---

## 💎 Value Object

**Descripción:** Patrón que representa un concepto del dominio mediante un objeto **inmutable**, cuya identidad se define por sus valores y no por una referencia.  
**Ejemplo:** `record Money(amount, currency)`

## 🧙‍♂️ Object Mother / Result Object (Inner Class)

**Descripción:** Patrón que ayuda a **crear objetos de prueba complejos** de forma sencilla (Object Mother) o encapsula resultados y errores dentro de una clase interna (Result Object).  
**Ejemplo:** `UserMother.createAdminUser();` / `OperationResult.success(data)`

---

## 🎯 Strategy Pattern

**Descripción:** Patrón de comportamiento que define una **familia de algoritmos**, encapsula cada uno y los hace intercambiables, permitiendo que el algoritmo varíe independientemente del cliente.  
**Ejemplo:** `payment.setStrategy(new CreditCardPayment()).pay();`

## 🔄 Simulation / State Loop

**Descripción:** Patrón que organiza la ejecución de una simulación o sistema en **estados discretos**, iterando en un bucle principal que actualiza el estado y la lógica del sistema de manera controlada.  
**Ejemplo:**
```
while(simulation.isRunning()) {
    simulation.updateState();
    simulation.render();
}
```
## 🛠️ Command Pattern (Variación)

**Descripción:** Patrón de comportamiento que encapsula una **acción como objeto**, permitiendo parametrizar clientes con diferentes operaciones, almacenar historial o deshacer/rehacer acciones.  
**Ejemplo:**
```
Command save = new SaveCommand(document);
editor.executeCommand(save);
```
## 🧠 Memoization (Dynamic Programming)

**Descripción:** Técnica que almacena los resultados de funciones costosas para **reutilizarlos en llamadas futuras**, evitando cálculos repetidos y mejorando el rendimiento.  
**Ejemplo:**
```java
Map<Integer, Integer> cache = new HashMap<>();

int fib(int n) {
    if (cache.containsKey(n)) return cache.get(n);
    int result = (n <= 1) ? n : fib(n-1) + fib(n-2);
    cache.put(n, result);
    return result;
}
```


