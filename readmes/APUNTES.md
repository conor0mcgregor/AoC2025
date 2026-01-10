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

