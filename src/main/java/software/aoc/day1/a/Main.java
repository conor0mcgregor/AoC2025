package software.aoc.day1.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws IOException, URISyntaxException {
        DialManeger dialManeger = DialManeger.create();
        dialManeger.ordersOfSpinsInFile("day1/a/input_Day1_A.txt");
        int password = dialManeger.getPassword();
        System.out.println(password);
    }

}
