# Advent of Code – Día8

## 🧩 Descripción del problema
reto A)
El reto consiste en conectar cajas de conexiones ubicadas en un espacio tridimensional, uniendo siempre primero los pares más cercanos según la distancia euclídea. Cada conexión une los circuitos de ambas cajas, formando conjuntos cada vez más grandes, y las conexiones entre cajas ya pertenecientes al mismo circuito no tienen efecto. Tras realizar un número fijo de conexiones, se deben analizar los tamaños de los circuitos resultantes. El objetivo es multiplicar los tamaños de los tres circuitos más grandes obtenidos tras las 1000 conexiones más cortas.

reto B)
En la segunda parte, el proceso de conexión continúa hasta que todas las cajas de conexiones forman un único circuito. El momento clave es identificar la última conexión necesaria para lograr esta unión total. A partir de esa conexión final, se toman las coordenadas X de las dos cajas implicadas y se calcula su producto. El reto se centra en el seguimiento de la conectividad y en determinar cuándo se alcanza la conexión completa del sistema.
---

## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Parsing:** `JunctionBoxes` lee las coordenadas y crea una lista de objetos `Node`.
2.  **Generación de Aristas (Malla Completa):**
    * Se generan todas las posibles conexiones (pares de nodos) entre los nodos existentes ($N*(N-1)/2$ combinaciones).
    * Se calcula la distancia para cada par.
3.  **Ordenamiento:** La lista de aristas (`edges`) se ordena de menor a mayor distancia.
4.  **Algoritmo de Unión (Kruskal):**
    * Se iteran las aristas ordenadas.
    * Si los nodos de la arista pertenecen a grafos diferentes, se fusionan los grafos (`addNewConnection`).
    * **Estrategia A (Producto X):** Se detiene cuando todos los nodos pertenecen a un único grafo (`lenght() == 1`).
    * **Estrategia B (Biggers Connection):** Se añaden solo las $N$ conexiones más cortas y se analizan los tamaños de los grupos resultantes.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`Node`**: Datos puros. Representa un punto en el espacio y su identidad.
* **`Edge`**: Relación. Calcula la "costosa" distancia entre dos nodos y define su orden natural.
* **`GraphSet`**: Estructura de Datos. Gestiona la colección de clústeres (bosque de grafos) y la lógica de fusión (Union-Find).
* **`JunctionBoxes`**: Solucionador. Orquesta el algoritmo específico del reto utilizando las estructuras anteriores.

### O - Open/Closed Principle (OCP)
* **Interfaz `GraphCollection`**: `JunctionBoxes` depende de esta interfaz. Esto permite cambiar la implementación subyacente de gestión de grafos (por ejemplo, optimizar `GraphSet` con un algoritmo de *Union-Find* con compresión de caminos) sin tocar la lógica del solucionador.

### I - Interface Segregation Principle (ISP)
* La interfaz `GraphCollection` define métodos específicos necesarios para el algoritmo (`addNewConnection`, `graphsSizes`, `lenght`), sin obligar a implementar métodos innecesarios de recorrido de grafos.

---

## 3. Patrones de Diseño

* **Composite Pattern (Variación):**
    * `GraphSet` actúa como un contenedor que gestiona múltiples objetos `Graph`. A su vez, `Graph` contiene múltiples `Node`.
* **Value Object:**
    * Uso de `record Point` dentro de `Node`. Inmutable y definido por sus atributos coordenadas.
* **Comparable Interface:**
    * `Edge` implementa `Comparable<Edge>`. Esto permite usar `Collections.sort()` o `stream().sorted()` de forma natural, delegando la lógica de comparación a la propia clase (patrón experto).
* **Static Factory Method:**
    * `JunctionBoxes.create()` encapsula la creación del objeto y sus dependencias internas.

---

## 4. Clean Code y Estilo

* **Streams y Funcionalidad:**
    * Uso intensivo de Streams para la combinatoria: `flatMap` para generar pares de nodos y crear aristas es una solución elegante y declarativa para un problema de bucles anidados.
* **Uso de Records:**
    * La clase interna `record Point(int x, int y, int z)` simplifica la clase `Node`, eliminando *boilerplate*.
* **Nombres Descriptivos:**
    * Métodos como `joinGraphs` y `getBiggersConnection` comunican claramente la intención, aunque hay pequeños errores tipográficos (`calculeDistace`, `lenght`).

---

## 5. Justificación de Decisiones Técnicas

### Algoritmo de Kruskal (Implícito)
El código implementa la lógica de Kruskal para encontrar el Árbol de Expansión Mínima (MST):
1.  Ordenar aristas por peso (distancia).
2.  Añadir aristas si no forman un ciclo (es decir, si unen dos componentes desconectados).
* **Justificación:** Es el algoritmo óptimo para conectar todos los puntos con el coste mínimo total o para analizar la estructura de conectividad basada en proximidad.

### Distancia Euclidiana al Cuadrado
* **Implementación:** `pow((point1.x() - point2.x()), 2) + ...` sin raíz cuadrada.
* **Justificación:** Para comparar distancias, no es necesario calcular la raíz cuadrada (que es costosa computacionalmente y pierde precisión con flotantes). Si $A^2 < B^2$, entonces $A < B$. Trabajar con `long` mantiene la precisión perfecta.

### Estructura de "Sets" (GraphSet)
* **Implementación:** Una lista de objetos `Graph`, donde cada `Graph` contiene una lista de `Node`. Al unir, se mueven todos los nodos de un grafo a otro y se elimina el grafo vacío.
* **Justificación:** Aunque existen estructuras de datos más eficientes, la implementación actual con Listas es más intuitiva, fácil de depurar y suficientemente rápida para el tamaño de entrada típico de estos retos ($N < 2000$).

### Generación de Aristas (`flatMap`)
* **Implementación:**
```java
IntStream.range(0, nodes.size()).boxed().flatMap(...)