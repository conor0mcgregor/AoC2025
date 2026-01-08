package software.aoc.day10.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        long result = factoryManager.getSumMinPulsesFromFile("day10/input.txt");
        System.out.println(result);
    }
}
