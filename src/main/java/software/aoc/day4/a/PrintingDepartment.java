package software.aoc.day4.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class PrintingDepartment {
    private final CountAccessRolls countAccessRolls;
    private final ResourceFileReader reader;

    private PrintingDepartment(){
        this.countAccessRolls = new CountAccessRolls();
        this.reader = new ResourceFileReader();
    }

    public static PrintingDepartment create() {
        return new PrintingDepartment();
    }

    public int countAccessibleRolls(List<String> grid) {
        return countAccessRolls.parse(grid);
    }

    public int countAccessbleRollsFrom(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        List<String> gred = readGridFrom(br);
        return countAccessRolls.parse(gred);
    }

    private List<String> readGridFrom(BufferedReader br) throws IOException {
        List<String> gred = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            gred.add(line);
        }
        return gred;
    }

}
