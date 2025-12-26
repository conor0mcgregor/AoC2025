package software.aoc.day3.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class BatteryBank {
    private static final int JOLTAGE_DIGITS = 12;
    
    private final FileReader fileReader;
    private final BatteryParser batteryParser;

    private BatteryBank(FileReader fileReader, BatteryParser batteryParser) {
        this.fileReader = fileReader;
        this.batteryParser = batteryParser;
    }


    public static BatteryBank create() {
        return new BatteryBank(
            new ResourceFileReader(),
            new MaxJoltageParser(JOLTAGE_DIGITS)
        );
    }


    public long sumAllMaxJoltageFromFile(String fileName) throws URISyntaxException, IOException {
        try (BufferedReader reader = fileReader.read(fileName)) {
            return calculateTotalJoltage(reader);
        }
    }


    public long getMaxJoltage(String batteryBank) {
        return batteryParser.parse(batteryBank);
    }

    private long calculateTotalJoltage(BufferedReader reader) throws IOException {
        long total = 0;
        String line;
        
        while ((line = reader.readLine()) != null) {
            total += batteryParser.parse(line);
        }
        
        return total;
    }
}