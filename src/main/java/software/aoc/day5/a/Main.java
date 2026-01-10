package software.aoc.day5.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        RangesIdParser counterFreshId = CounterFreshId.create();
        long numFreshId = counterFreshId.countFreshId("day5/input_day5.txt");
        System.out.println(numFreshId);
    }
}
