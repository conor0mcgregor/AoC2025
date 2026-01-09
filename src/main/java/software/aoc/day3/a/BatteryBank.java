package software.aoc.day3.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class BatteryBank {
    private final FileReader fileReader;
    private final BatteryParser batteryParser;

    private BatteryBank(FileReader fileReader, BatteryParser batteryParser) {
        this.fileReader = fileReader;
        this.batteryParser = batteryParser;
    }


    public static BatteryBank create() {
        return new BatteryBank(
            new ResourceFileReader(),
            new MaxJoltageParser()
        );
    }


    public int sumAllMaxJoltageFromFile(String fileName) throws URISyntaxException, IOException {
        try (BufferedReader reader = fileReader.read(fileName)) {
            return calculateTotalJoltage(reader);
        }
    }


    public int getMaxJoltage(String batteryBank) {
        return batteryParser.parse(batteryBank);
    }

    private int calculateTotalJoltage(BufferedReader reader) {
        return reader.lines()
                .mapToInt(batteryParser::parse)
                .sum();
    }
}