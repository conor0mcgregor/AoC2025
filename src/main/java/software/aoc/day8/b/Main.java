package software.aoc.day8.b;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();
        long result = junctionBoxes.getProductX("day8/input_day8.txt");
        System.out.println(result);
    }
}
