package software.aoc.day8.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();
        long result = junctionBoxes.getBiggersConnection("day8/input_day8.txt", 1000);
        System.out.println(result);
    }
}
