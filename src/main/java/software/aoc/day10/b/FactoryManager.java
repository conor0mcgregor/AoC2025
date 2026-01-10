package software.aoc.day10.b;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class FactoryManager {
    private final FileReader reader;

    private FactoryManager() {
        this.reader = new ResourceFileReader();
    }

    public static FactoryManager create(){
        return new FactoryManager();
    }

    public long getSumMinPulsesFrom(String fileName) throws URISyntaxException, IOException {
        BufferedReader dates = reader.read(fileName);
        return parserDates(dates);
    }
    public long getSumMinPulses(String machine) throws IOException {
        BufferedReader dates = reader.StringToBR(machine);
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

        PushCalculator pushCalculator = new StateCalculator(machineDates);

        return pushCalculator.minimizarPulsaciones();

    }

}
