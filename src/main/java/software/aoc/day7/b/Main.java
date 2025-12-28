package software.aoc.day7.b;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    static void main() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        long result = tachyonManifold.countTimeLine("day7/input_day7.txt");
        System.out.println(result);
    }


}
