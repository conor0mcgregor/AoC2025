package software.aoc.day4.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {
    private final FileReader reader;

    private PrintingDepartment(FileReader reader){
        this.reader = reader;
    }

    public static PrintingDepartment create() {
        return new PrintingDepartment(new ResourceFileReader());
    }

    public int countAccessibleRolls(List<String> grid) {
        DepertmentParser countAccessRolls = CountAccessRolls.create(grid);
        return countAccessRolls.parse();
    }

    public int countAccessbleRollsFrom(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        return countAccessibleRolls(readGridFrom(br));
    }

    private List<String> readGridFrom(BufferedReader br) {
        return br.lines().toList();
    }

}
