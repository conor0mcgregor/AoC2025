# 3. Fundamentos de diseño (el "qué queremos conseguir")

Los fundamentos son las cualidades deseables de cualquier sistema bien diseñado.

## 3.1 Alta cohesión

Un módulo/clase debe hacer una sola cosa y hacerla bien.

📌 **Ejemplo:**
* ❌ Clase `Usuario` que gestiona datos, imprime informes y accede a la BD
* ✅ Clase `Usuario` solo gestiona datos del usuario

👉 **Beneficio:** código más claro y fácil de modificar.

## 3.2 Bajo acoplamiento

Los módulos deben depender lo menos posible unos de otros.

📌 **Ejemplo:**
* ❌ Clase A conoce todos los detalles internos de la clase B
* ✅ Clase A solo usa una interfaz de B

👉 **Beneficio:** cambiar una parte no rompe el resto.

## 3.3 Modularidad

Dividir el sistema en partes independientes.

📌 **Ejemplo:**
* Módulo de usuarios
* Módulo de pagos
* Módulo de notificaciones

👉 **Beneficio:** se puede trabajar, probar y mantener cada módulo por separado.

## 3.4 Código expresivo

El código debe leerse como un texto claro, no como un acertijo.

📌 **Ejemplo:**
* ❌ `calc(x, y)`
* ✅ `calcularPrecioFinal(precioBase, impuestos)`

👉 **Beneficio:** menos errores y menos tiempo entendiendo código antiguo.

## 3.5 Abstracción

Ocultar la complejidad detrás de una interfaz simple.

📌 **Ejemplo:**
* Usas `guardarArchivo()` sin saber si guarda en disco, nube o base de datos.

👉 **Beneficio:** puedes cambiar la implementación sin afectar al resto.
---
# 4. Principios de diseño (las reglas para lograr los fundamentos)

Los principios son normas prácticas que ayudan a cumplir los fundamentos.

## 4.1 SRP – Principio de Responsabilidad Única

Una clase debe tener una sola razón para cambiar.

📌 **Ejemplo:**
* ❌ Clase que calcula facturas y las imprime
* ✅ Una clase calcula, otra imprime

🔗 **Relacionado con:** alta cohesión

## 4.2 OCP – Abierto/Cerrado

Abierto para extender, cerrado para modificar.

📌 **Ejemplo:**
* Añadir nuevos tipos de pago sin tocar el código existente.

🔗 Evita romper código ya probado.

## 4.3 LSP – Sustitución de Liskov

Un objeto hijo debe poder usarse como su padre sin errores.

📌 **Ejemplo:**
* Si `Perro` hereda de `Animal`, no debe romper comportamientos esperados.

👉 Garantiza herencias correctas.

## 4.4 ISP – Segregación de Interfaces

No obligar a implementar métodos que no se usan.

📌 **Ejemplo:**
* ❌ Interfaz gigante con 20 métodos
* ✅ Interfaces pequeñas y específicas

🔗 Favorece bajo acoplamiento.

## 4.5 DIP – Inversión de Dependencias

Los módulos importantes dependen de abstracciones, no de detalles.

📌 **Ejemplo:**
* Usar interfaces en lugar de clases concretas.

👉 Clave para testear y escalar.

## 4.6 Composición sobre herencia

Es mejor tener un objeto que ser un objeto.

📌 **Ejemplo:**
* ❌ `CocheDeportivo extends Coche`
* ✅ `Coche` tiene un `Motor`

👉 Evita jerarquías rígidas.

## 4.7 Ley de Demeter

"No hables con extraños".

📌 **Ejemplo:**
* ❌ `a.getB().getC().doSomething()`
* ✅ `a.doSomething()`

👉 Reduce dependencias ocultas.

## 4.8 DRY – Don't Repeat Yourself

No repetir conocimiento.

📌 **Ejemplo:**
* ❌ Misma fórmula copiada en 5 sitios
* ✅ Una función reutilizable

## 4.9 CoC – Convención sobre configuración

Menos configuración, más convenciones.

📌 **Ejemplo:**
* Frameworks que funcionan "por defecto".

## 4.10 YAGNI

No implementes algo "por si acaso".

📌 **Ejemplo:**
* No crear funcionalidades que nadie necesita aún.

👉 Evita sobreingeniería.

---

# 5. Patrones de diseño (soluciones reutilizables)

Los patrones son formas probadas de resolver problemas habituales.

## 5.1 Singleton

Una sola instancia global.

📌 **Usos:**
* Configuración
* Logs
* Conexión a BD

⚠️ Debe usarse con cuidado.

## 5.2 Factory Method

Delegar la creación de objetos.

📌 **Beneficio:**
* No acoplas el código a clases concretas.

## 5.3 Iterator

Recorrer colecciones sin conocer su estructura.

📌 **Ejemplo:**
* `for each` en listas, árboles, etc.

## 5.4 Adapter

Hace compatibles interfaces incompatibles.

📌 **Ejemplo:**
* Usar una librería antigua con una interfaz nueva.

## 5.5 Decorator

Añadir comportamiento dinámicamente.

📌 **Ejemplo:**
* Añadir permisos, logs o validaciones sin modificar la clase original.

## 5.6 Observer

Uno cambia → muchos se actualizan.

📌 **Ejemplo:**
* Interfaces gráficas
* Eventos

👉 Muy desacoplado.

## 5.7 Command

Encapsular acciones como objetos.

📌 **Usos:**
* Deshacer/rehacer
* Colas de tareas

# PATRONES Y TÉCNICAS NUEVAS (explicadas una sola vez)

## 1. Fluent Interface (Method Chaining)

### Qué es
Un estilo de API donde los métodos devuelven el propio objeto (this), permitiendo encadenar llamadas.

### Problema que resuelve
- APIs verbosas
- Código imperativo poco legible
- Configuraciones paso a paso poco claras

### Cómo lo usaste
En DialManeger, los métodos retornan this:

```
manager.ordersOfSpins(...)
       .getPosition();
```

### Por qué está bien
- El código se lee como una frase
- Reduce variables temporales
- Muy expresivo para configuraciones

👉 Es diseño orientado a legibilidad y expresividad.

---

## 2. Object Mother / Result Object (clase interna)

### Qué es
Un patrón para devolver múltiples valores relacionados sin usar:
- arrays confusos
- variables globales
- parámetros de salida

### Problema que resuelve
Java solo devuelve un valor por método.

### Cómo lo usaste
Clase interna:

```java
private static class MaxDigitResult {
    long value;
    int index;
}
```

Para devolver:
- el dígito máximo
- su posición

### Por qué está bien
- Semántica clara
- Tipado fuerte
- Evita "posición mágica" en arrays (result[0], result[1])

👉 Es mejor diseño que devolver arrays.

---

## 3. Simulation / State Loop

### Qué es
Un patrón algorítmico donde:
- el sistema se ejecuta en ciclos
- cada iteración modifica el estado
- se detiene cuando no hay cambios

### Problema que resuelve
Casos donde:
- una sola pasada no basta
- las reglas tienen efectos en cascada

### Cómo lo usaste
En parse:
- no es un algoritmo directo
- se repite hasta que el sistema se estabiliza

### Por qué está bien
- Refleja fielmente el modelo del problema
- Evita lógica frágil "todo en una pasada"

👉 Muy usado en simulaciones físicas, económicas, grafos.

---

## 4. Composite / Manager

### Qué es
Una variación del Composite donde:
- un objeto gestiona una colección
- expone operaciones de alto nivel
- oculta la estructura interna

### Problema que resuelve
Evitar que el cliente:
- gestione listas
- sepa cómo se almacenan los elementos

### Cómo lo usaste
RangesManager:
- contiene `List<Range>`
- expone `addRange`, `getSizeRanges`

### Por qué está bien
- Encapsulación total
- El cliente no sabe ni le importa cómo se gestionan los rangos

👉 Muy buen diseño orientado a abstracción.

---

## 5. Value Object (más allá de lo básico)

### Qué es
Un objeto:
- inmutable
- definido por sus valores
- sin identidad propia

### Cómo lo usaste
```
record Range(long a, long b)
```

Y también:
```
record Point(int x, int y)
```

### Por qué está perfecto
- Inmutabilidad → seguridad
- Ideal para matemáticas, coordenadas, intervalos
- Facilita pruebas y razonamiento

👉 Esto es diseño funcional moderno en Java.

---

## 6. Recursive Optimization (Efecto Cascada)

### Qué es
Una técnica donde una operación:
- puede habilitar nuevas operaciones
- y se llama recursivamente hasta cerrar el sistema

### Problema que resuelve
Unificar rangos:
- una fusión puede crear otra fusión posible

### Cómo lo usaste
```
unify → updateRanges → unify
```

### Por qué está bien
- Garantiza estado final correcto
- Evita bucles complejos y flags

👉 Es una recursión semánticamente justificada, no accidental.

---

## 7. Command Pattern (variación funcional)

### Qué es
Encapsular:
- los datos
- la acción

en un solo objeto.

### Cómo lo usaste
```
record Problem(...) {
    long solve() { ... }
}
```

### Por qué está bien
- El objeto representa una acción
- Puede almacenarse, ejecutarse, reutilizarse

👉 Es Command sin sobreingeniería.

---

## 8. DTO / Value Object combinado

### Qué es
Un objeto que:
- transporta datos
- es inmutable
- tiene significado semántico

### Cómo lo usaste
Problem como:
- DTO (datos)
- Value Object (identidad por valor)

### Por qué está bien
- No hay setters
- No hay estado intermedio
- Totalmente seguro en concurrencia

---

## 9. Memoization (Dynamic Programming)

### Qué es
Guardar resultados de subproblemas ya calculados.

### Problema que resuelve
Complejidad exponencial por:
- bifurcaciones
- recursión

### Cómo lo usaste
```
Map<String, Long> memo
```

Clave compuesta por:
- posición
- estado lógico

### Impacto
De exponencial → casi lineal

Brutal mejora de rendimiento

👉 Esto es ingeniería algorítmica de alto nivel.

---

## 10. Composite Pattern (grafos)

### Qué es
Un patrón estructural donde:
- objetos contienen otros objetos
- se tratan de forma uniforme

### Cómo lo usaste
- GraphSet → contiene Graph
- Graph → contiene Node

### Por qué está bien
- Jerarquía clara
- Operaciones recursivas naturales

---

## 11. Comparable (Patrón Experto)

### Qué es
Delegar la lógica de comparación al objeto que sabe comparar.

### Cómo lo usaste
```
class Edge implements Comparable<Edge>
```

### Por qué está bien
- `Collections.sort()` sin lógica externa
- Cohesión alta

👉 Es aplicar responsabilidad donde corresponde.

---

## 12. Coordinate Compression

### Qué es
Técnica algorítmica para:
- mapear valores grandes y dispersos
- a índices pequeños consecutivos

### Problema que resuelve
Memoria imposible:
- coordenadas tipo 1.000.000

### Por qué está bien
- Permite usar arrays
- Mantiene relaciones espaciales

👉 Patrón típico de programación competitiva y sistemas eficientes.

---

## 13. Prefix Sum 2D

### Qué es
Precalcular sumas acumuladas para consultas O(1).

### Cómo lo usaste
Para verificar:
- si un rectángulo tiene celdas exteriores

### Por qué está bien
- Evita recorrer submatrices
- Escala muy bien

---

## 14. Flood Fill (BFS)

### Qué es
Algoritmo para:
- recorrer áreas conectadas
- marcar regiones

### Cómo lo usaste
Desde (0,0):
- marcas todo lo exterior

### Por qué está perfecto
- BFS evita desbordamiento de pila
- Claridad total del modelo

---

## 15. Immutable Data Carrier

### Qué es
Objetos simples, inmutables, solo datos.

### Cómo lo usaste
```
record Point
```

### Por qué está bien
- Seguridad
- Ningún efecto colateral

---

## 16. Adapter Pattern

### Qué es
Convertir una interfaz en otra esperada por el sistema.

### Cómo lo usaste
StateCalculator:
- entrada textual
- salida en grafos o sistemas lineales

### Por qué está perfecto
- Desacopla parsing de lógica
- Reutilizable

---

## 17. Builder Pattern (simplificado)

### Qué es
Construcción paso a paso de un objeto complejo.

### Cómo lo usaste
Métodos secuenciales en Reactor:
- parse
- build
- assemble

### Por qué está bien
- No necesitas una clase Builder formal
- El flujo es claro

---

## 18. Adjacency List

### Qué es
Representación estándar de grafos dispersos.

### Cómo lo usaste
```
List<Node> destNodes
```

### Por qué está bien
- Memoria eficiente
- Recorridos rápidos

---

## 19. Backtracking

### Qué es
Explorar decisiones:
- probar
- deshacer
- probar otra opción

### Cómo lo usaste
```
tryPlaceAllPresents
```

### Por qué está bien
- DFS natural
- Código limpio

---

## 20. Flyweight / Caching

### Qué es
Precalcular y reutilizar objetos pesados.

### Cómo lo usaste
Rotaciones calculadas una vez.

### Impacto
- Ahorro masivo de CPU
- Ideal para recursión profunda

---

## 21. Prototype (Clonación)

### Qué es
Copiar estado para aislar ramas de ejecución.

### Cómo lo usaste
Clonado manual de matrices en backtracking.

### Por qué está bien
- Inmutabilidad lógica
- Cada rama tiene su propio "universo"