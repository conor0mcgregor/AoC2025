package software.aoc.day4.b;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws IOException, URISyntaxException {
        PrintingDepartment printingDepartment = PrintingDepartment.create();
        int accessbleRolls = printingDepartment.countAccessibleRollsFrom("day4/input_day4.txt");
        System.out.println(accessbleRolls);
    }
}
