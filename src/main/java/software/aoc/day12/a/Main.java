package software.aoc.day12.a;

import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

final class Main {

    static void main() throws URISyntaxException, IOException {
        ResourceFileReader reader = new ResourceFileReader();
        List<String> input = reader.read("day12/input_day12.txt").readAllLines();
        IO.println("solution = " + ChristmasTreeFarm.solve(input));
    }
}
