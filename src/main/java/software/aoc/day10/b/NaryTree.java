package software.aoc.day10.b;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class NaryTree {
    private final Node root;

    public NaryTree(int sizeLights) {
        this.root = createRoot(sizeLights);
    }

    private Node createRoot(int sizeLights) {
        String state = new String(new char[sizeLights]).replace("\0", ".");
        return new Node(null, state);
    }

    public int getShortestPath(String stateDest, String[] buttons){
        if (root.getState().equals(stateDest)) return 0;

        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        visited.add(root.getState());
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            level++;

            for (int i = 0; i < levelSize; i++) {
                Node currentState = queue.poll();

                expandStates(buttons, currentState);

                for (Node child : currentState.getChilds()) {
                    String childState = child.getState();

                    if (childState.equals(stateDest)) {
                        return level;
                    }

                    if (!visited.contains(childState)) {
                        visited.add(childState);
                        queue.add(child);
                    }
                }
            }
        }

        return -1; // No se encontró el camino
    }

    private void expandStates(String[] buttons, Node currentState) {
        for (String button : buttons) {
            String newState = buttonToState(currentState.getState(), button);
            currentState.addChild(newState);
        }
    }

    private String buttonToState(String currentState, String button) {
        char[] newState = currentState.toCharArray();
        for (Integer index : extraerNumeros(button)) {
            newState[index] = inverChar(currentState.charAt(index));
        }
        return new String(newState); // Cambio: usar String constructor, no Arrays.toString
    }

    private char inverChar(char c) {
        return (c == '.') ? '#' : '.';
    }

    public int[] extraerNumeros(String texto) {
        String limpio = texto.replaceAll("[()\\s]", "");
        String[] partes = limpio.split(",");

        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i]);
        }

        return numeros;
    }

    public Node getRoot() {
        return root;
    }
}