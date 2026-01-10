# Advent of Code – Día10
## 🧩 Descripción del problema
reto A)
El reto consiste en inicializar varias máquinas configurando correctamente sus luces indicadoras. Cada máquina tiene un patrón objetivo de luces encendidas y apagadas, y una serie de botones que alternan el estado de ciertos indicadores al ser pulsados. Las luces comienzan apagadas y los botones pueden pulsarse cualquier número entero de veces. El objetivo es encontrar, para cada máquina, el número mínimo de pulsaciones necesarias para alcanzar el patrón deseado y sumar ese mínimo para todas las máquinas.

reto B)
En la segunda parte, se ignoran las luces y los botones pasan a controlar contadores de voltaje asociados a cada máquina. Cada botón incrementa en 1 los contadores que tiene asociados, y todos comienzan en cero. El objetivo es alcanzar exactamente los valores de voltaje requeridos usando el menor número total de pulsaciones. El reto se centra en resolver sistemas de incrementos mínimos y sumar el coste mínimo de configuración de todas las máquinas.

---
## 1. Metodología: Test Driven Development (TDD)
El problema se resolvió utilizando un enfoque **TDD**, lo cual se refleja directamente en la estructura del código resultante.
Primero se definieron los test y a partir de ellos la creacion de codigo para poder pasar dichos tests

---

## Flujo de Ejecución

1.  **Main** invoca a `FactoryManager`.
2.  **Lectura y Parsing:**
    * `FactoryManager` lee el archivo de entrada.
    * Por cada línea (máquina), instancia un `StateCalculator` pasándole los datos crudos.
3.  **Selección de Estrategia (Reto A vs B):**
    * **Para el Reto A (`getMinPushForState`):**
        * `StateCalculator` instancia un `NaryTree`.
        * Se ejecuta un algoritmo **BFS**: Se exploran todos los estados posibles generados por los botones nivel por nivel hasta encontrar el estado objetivo.
    * **Para el Reto B (`minimizarPulsaciones`):**
        * `StateCalculator` transforma los strings en matrices numéricas (`passToMatriz`, `passToVector`).
        * Se invoca a `LinearSystemsOptimizer`.
        * Se configura una función objetivo (minimizar suma de pulsaciones) y restricciones lineales ($Ax = Voltaje$) y se resuelve usando el método **Simplex**.

---

## 2. Principios SOLID

### S - Single Responsibility Principle (SRP)
* **`NaryTree`**: Responsabilidad algorítmica de grafos (BFS). Solo sabe expandir nodos y buscar caminos.
* **`LinearSystemsOptimizer`**: Responsabilidad matemática pura. Adapta el problema a la librería de optimización lineal.
* **`StateCalculator`**: Responsabilidad de Adaptador (Adapter). Traduce el dominio del problema (Strings de botones y luces) a las estructuras de datos que necesitan los algoritmos (Grafos o Matrices).
* **`FactoryManager`**: Orquestación y I/O.

### O - Open/Closed Principle (OCP)
* La interfaz `PushCalculator` permite extender el sistema. Si surgiera un "Reto C" que requiriera un algoritmo genético o fuerza bruta, se podría implementar una nueva clase o método sin romper el contrato existente con el cliente `FactoryManager`.

### I - Interface Segregation Principle (ISP)
* `PushCalculator` expone `getMinPushForState` (Reto A) y `minimizarPulsaciones` (Reto B).

---

## 3. Patrones de Diseño

* **Adapter Pattern:**
    * `StateCalculator` actúa como un adaptador. Convierte la entrada textual del problema ("Button 1: 1,2,3") en:
        1.  Nodos y aristas para `NaryTree` (Reto A).
        2.  Vectores y matrices de coeficientes para `LinearSystemsOptimizer` (Reto B).
* **Facade Pattern:**
    * `LinearSystemsOptimizer` actúa como una fachada sobre la compleja librería `org.apache.commons.math3`. El resto de la aplicación no necesita saber qué es un `SimplexSolver`, `LinearConstraint` o `PointValuePair`; solo piden "minimizar pulsaciones".
* **Composite / Tree Structure:**
    * `Node` y `NaryTree` implementan una estructura de árbol n-ario explícita para representar el espacio de estados.
* **Static Factory Method:**
    * `FactoryManager.create()` encapsula la creación del objeto y sus dependencias internas.

---

## 4. Clean Code y Estilo

* **Naming Intencional:** `expandStates`, `buttonToState`, `restrictIndependentTerms`.
* **Uso de Librerías Robustas:** En lugar de reimplementar el algoritmo Simplex (propenso a errores numéricos), se utiliza `Apache Commons Math`, lo cual es una decisión de "Clean Architecture" al delegar detalles de bajo nivel a herramientas probadas.
* **Inmutabilidad (Parcial):** La clase `Node` es mayormente inmutable (estado final), lo que facilita su uso en estructuras como `HashSet` para evitar ciclos en el grafo.

---

## 5. Justificación de Decisiones Técnicas

### Reto A: Uso de BFS (`NaryTree`)
El reto A pide el mínimo de pulsaciones para un patrón de luces (encendido/apagado).
* **Decisión:** Modelar como un grafo donde cada nodo es una configuración de luces (String) y las aristas son pulsaciones de botones. Usar BFS (Cola `LinkedList`).
* **Justificación:** BFS garantiza encontrar el camino más corto (mínimo nivel de profundidad) en un grafo no ponderado. Dado que el espacio de estados es finito (patrones de luces), esta exploración exhaustiva es viable y correcta.

### Reto B: Uso de Programación Lineal (`LinearSystemsOptimizer`)
El reto B cambia a contadores de voltaje que suman (+1).
* **Decisión:** Modelar como un sistema de ecuaciones lineales.
    * Variables: $x_1, x_2, ...$ (veces que se pulsa cada botón).
    * Ecuación: $Button_A \cdot x_1 + Button_B \cdot x_2 = TargetVoltage$.
    * Objetivo: Minimizar $\sum x_i$.
* **Justificación:** BFS es inviable aquí porque los voltajes pueden ser números altos, creando un espacio de estados infinito o gigantesco. El problema se traduce perfectamente a **Programación Lineal Entera**.
* **Implementación:** Se usa `SimplexSolver`. Para forzar la igualdad $Ax = b$ en la librería, se añaden dos restricciones: $Ax \ge b$ y $Ax \le b$.

### Representación de Estados (String vs Array)
* En `NaryTree`, el estado se guarda como `String` ("#.#.").
* **Justificación:** Permite usar el método `hashCode` nativo de Java para almacenar estados visitados en un `HashSet<String>`, evitando bucles infinitos y re-procesamiento de manera eficiente.