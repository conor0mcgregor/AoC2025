package software.aoc.day6.a;

import java.util.ArrayList;
import java.util.List;

public class Calculator implements ProblemSolver{

    @Override
    public long solveWorksheet(List<String> lines) {
        return parseProblems(lines).stream()
                .mapToLong(Problem::solve)
                .sum();
    }

    private List<Problem> parseProblems(List<String> lines) {
        List<Problem> problems = new ArrayList<>();

        String[] operators = lines.getLast().trim().split("\\s+");

        extractProblems(lines, operators, problems);

        return problems;
    }

    private void extractProblems(List<String> lines, String[] operators, List<Problem> problems) {
        for (int col = 0; col < operators.length; col++) {
            char operator = operators[col].charAt(0);

            List<Long> numbers = extractNumbersFromColumn(lines, col);
            problems.add(new Problem(numbers, operator));
        }
    }

    private List<Long> extractNumbersFromColumn(List<String> lines, int col) {
        List<Long> numbers = new ArrayList<>();

        for (int row = 0; row < lines.size() - 1; row++) {
            String line = lines.get(row);

            String number = extractNumberEndingAt(line, col);

            numbers.add(Long.parseLong(number.trim()));

        }

        return numbers;
    }

    private String extractNumberEndingAt(String line, int col) {
        return line.trim().split("\\s+")[col];
    }


}