package software.aoc.day10.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

import static java.lang.Math.max;

public class FactoryManager {
    private final FileReader reader;

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
        return dates.lines()
                .mapToLong(this::calculateMinPulses)
                .sum();
    }

    private long calculateMinPulses(String machine) {
        String[] machineDates = machine.split(" ");
        PushCalculator pushCalculator = new StateCalculator(machineDates);
        return pushCalculator.getMinPushForState();
    }


}
