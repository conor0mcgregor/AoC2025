package software.aoc.day1.a;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        DialManeger dialManeger = DialManeger.create();
        dialManeger.ordersOfSpinsInFile("day1/a/input_Day1_A.txt");
        int posicion = dialManeger.getPosition();
        int password = dialManeger.getPassword();
        System.out.println(password);
    }

}
