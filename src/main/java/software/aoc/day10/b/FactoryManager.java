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
        return parserDates(dates);
    }
    public long getSumMinPulsesFrom(String machines) throws IOException {
        BufferedReader dates = reader.StringToBR(machines);
        return parserDates(dates);
    }

    private long parserDates(BufferedReader dates) throws IOException {
        return dates.lines()
                .filter(line -> !line.isBlank())
                .mapToLong(this::calculateMinPulses)
                .sum();
    }

    private long calculateMinPulses(String machine) {
        String[] machineDates = machine.split(" ");
        String[] buttons = Arrays.copyOfRange(machineDates, 1, machineDates.length - 1);
        String voltage = machineDates[machineDates.length - 1];


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
