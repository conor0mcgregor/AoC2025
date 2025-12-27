package software.aoc.day6.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws IOException, URISyntaxException {
        TrashCompactor trashCompactor = TrashCompactor.create();
        long result = trashCompactor.solveOperation("day6/input_day6.txt");
        System.out.println(result);
    }
}
