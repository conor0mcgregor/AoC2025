package software.aoc.day10.b;

import java.util.Arrays;

public class StateCalculator implements PushCalculator {

    private final String stateDest;
    private final String[] buttons;
    private final String voltage;

    public StateCalculator(String[] machineDates) {

        this.stateDest = machineDates[0].substring(1, machineDates[0].length() - 1);
        this.buttons = Arrays.copyOfRange(machineDates, 1, machineDates.length - 1);
        this.voltage = machineDates[machineDates.length - 1];
    }


    @Override
    public long getMinPushForState() {

        NaryTree naryTree = new NaryTree(stateDest.length());

        return naryTree.getShortestPath(stateDest, buttons);
    }

    @Override
    public long minimizarPulsaciones() {
        int[] vector = passToVector(voltage);
        return LinearSystemsOptimizer.minimizarPulsaciones(passToMatriz(buttons, vector.length), vector);
    }


    private int[] passToVector(String string) {
        String inner = string.substring(1, string.length() - 1).trim();

        if (inner.isEmpty()) {
            return new int[0];
        }

        return Arrays.stream(inner.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private int[][] passToMatriz(String[] buttons, int rows) {
        int[][] matriz = new int[rows][buttons.length];

        for (int col = 0; col < buttons.length; col++) {
            for (int row : passToVector(buttons[col])) {
                matriz[row][col] = 1;
            }
        }

        return matriz;
    }


}
