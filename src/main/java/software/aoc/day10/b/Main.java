package software.aoc.day10.b;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        long result = factoryManager.getSumMinPulsesFromFile("day10/input_day10.txt");
        System.out.println(result);
    }
}
