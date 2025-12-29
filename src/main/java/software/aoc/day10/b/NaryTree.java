package software.aoc.day10.b;

import java.util.*;

public class NaryTree {
    private final Node root;

    public NaryTree(int sizeLights) {
        this.root = createRoot(sizeLights);
    }

    private Node createRoot(int sizeLights) {
        State state = new State(new int[sizeLights]);
        return new Node(null, state);
    }

    public int getShortestPath(String stateDest, String[] buttons){
        State stateDestine = State.of(stateDest);
        return bfs(buttons, stateDestine);
    }

    private int bfs(String[] buttons, State stateDestine) {
        if (root.getState().equals(stateDestine)) return 0;

        Queue<Node> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();

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
                    State childState = child.getState();

                    if (childState.equals(stateDestine)) {
                        return level;
                    }

                    if (!visited.contains(childState)) {
                        visited.add(childState);
                        queue.add(child);
                    }
                }
            }
        }

        return -1;
    }

    private void expandStates(String[] buttons, Node currentState) {
        for (String button : buttons) {
            State newState = buttonToState(currentState.getState(), button);
            currentState.addChild(newState);
        }
    }

    private State buttonToState(State currentState, String button) {
        int[] newState = currentState.getState().clone();
        for (Integer index : extraerNumeros(button)) {
            newState[index]++;
        }
        return new State(newState);
    }

    public int[] extraerNumeros(String texto) {
        String limpio = texto.replaceAll("[()\\s]", "");
        String[] partes = limpio.split(",");

        return Arrays.stream(partes)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public Node getRoot() {
        return root;
    }

}