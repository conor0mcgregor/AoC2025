package software.aoc.day4.b;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {
    private final FileReader reader;

    private PrintingDepartment(){
        this.reader = new ResourceFileReader();
    }

    public static PrintingDepartment create() {
        return new PrintingDepartment();
    }

    public int countAccessibleRolls(List<String> grid) {
        DepartmentParser parser = CounterAccessRolls.create(grid);
        return parser.parse();
    }

    public int countAccessibleRollsFrom(String fileName) throws URISyntaxException, IOException {
        try (BufferedReader br = reader.read(fileName)) {
            return countAccessibleRolls(br.lines().toList());
        }
    }

}
