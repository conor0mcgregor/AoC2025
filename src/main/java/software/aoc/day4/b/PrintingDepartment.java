package software.aoc.day4.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {
    private final CountAccessRolls parser;
    private final ResourceFileReader reader;

    private PrintingDepartment(){
        this.parser = new CountAccessRolls();
        this.reader = new ResourceFileReader();
    }

    public static PrintingDepartment create() {
        return new PrintingDepartment();
    }

    public int countAccessibleRolls(List<String> grid) {
        return parser.parse(grid);
    }

    public int countAccessibleRollsFrom(String fileName) throws URISyntaxException, IOException {
        try (BufferedReader br = reader.read(fileName)) {
            List<String> grid = new ArrayList<>(br.lines().toList());
            return parser.parse(grid);
        }
    }

}
