package software.aoc.day11.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        Reactor reactor = Reactor.create();
        long result = reactor.countPaths("day11/input_day11.txt");
        System.out.println(result);
    }
}
