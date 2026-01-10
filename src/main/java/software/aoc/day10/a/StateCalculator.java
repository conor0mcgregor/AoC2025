package software.aoc.day10.a;

import java.util.Arrays;

public class StateCalculator implements PushCalculator{
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
}
