package software.aoc.day5.b;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        CounterFreshId counterFreshId = CounterFreshId.create();
        long numFreshId = counterFreshId.getAvailableIDs("day5/input_day5.txt");
        System.out.println(numFreshId);
    }
}
