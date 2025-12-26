package software.aoc.day4.a;

import java.util.List;

public class PrintingDepartment {
    private PrintingDepartment(){};
    public static PrintingDepartment create() {
        return new PrintingDepartment();
    }

    public int countAccessibleRolls(List<String> grid) {
        return calulerRolls(grid);
    }

    private int calulerRolls(List<String> grid) {
        return 0;
    }
}
