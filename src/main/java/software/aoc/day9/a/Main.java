package software.aoc.day9.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        CinemaSolver cinemaSolver = CinemaSolver.create();
        long result = cinemaSolver.findMaxRectangle("day9/input_day9.txt");
        System.out.println(result);
    }
}
