package software.aoc.day10.b;

import software.aoc.day10.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.lang.Math.max;

public class FactoryManager {
    private final ResourceFileReader reader;

    private FactoryManager() {
        this.reader = new ResourceFileReader();
    }

    public static FactoryManager create(){
        return new FactoryManager();
    }

    public long getSumMinPulsesFromFile(String fileName) throws URISyntaxException, IOException {
        BufferedReader dates = reader.read(fileName);
        return max(parserDates(dates), 0);
    }
    public long getSumMinPulsesFrom(String machines) throws IOException {
        BufferedReader dates = reader.StringToBR(machines);
        return max(parserDates(dates), 0);
    }

    private long parserDates(BufferedReader dates) throws IOException {
        long result = 0;
        String line;
        while ((line = dates.readLine()) != null){
            result += calculateMinPulses(line);
        }
        return result;
    }

    private long calculateMinPulses(String machine) {
        String[] machineDates = machine.split(" ");
        String stateDest = machineDates[0].substring(1, machineDates[0].length() - 1);
        String[] buttons = Arrays.copyOfRange(machineDates, 1, machineDates.length - 1);
        String voltage = machineDates[machineDates.length - 1];

        NaryTree naryTree = new NaryTree(voltage.split(",").length);

        return naryTree.getShortestPath(voltage, buttons);
    }


}
