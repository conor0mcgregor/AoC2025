package software.aoc.day6.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TrashCompactor {
    FileReader reader;
    ProblemSolver calculator;

    private TrashCompactor() {
        this.reader = new ResourceFileReader();
        this.calculator = new Calculator();
    }

    public static TrashCompactor create(){
        return new TrashCompactor();
    }

    public long solveOperation(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        return calculator.solveWorksheet(br.lines().toList());
    }
    public long solveOperation(List<String> lines) {
        return calculator.solveWorksheet(lines);
    }

}
