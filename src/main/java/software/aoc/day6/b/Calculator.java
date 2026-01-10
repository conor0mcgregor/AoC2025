package software.aoc.day6.b;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator implements ProblemSolver {

    @Override
    public long solveWorksheet(List<String> lines) {
        return parseProblems(lines).stream()
                .mapToLong(Problem::solve)
                .sum();
    }

    private List<Problem> parseProblems(List<String> lines) {
        List<Problem> problems = new ArrayList<>();
        String operators = lines.getLast();

        extractProblem(lines, operators, problems);

        return problems;
    }

    private void extractProblem(List<String> lines, String operators, List<Problem> problems) {
        for (int col = 0; col < operators.length(); col++) {
            char operator = operators.charAt(col);
            if(operator == ' ') continue;
            List<Long> numbers = extractNumbersFromColumn(lines, col);
            problems.add(new Problem(numbers, operator));
        }
    }

    private List<Long> extractNumbersFromColumn(List<String> lines, int col) {
        List<Long> numbers = new ArrayList<>();
        String number;
        while (col < getMaxLength(lines)) {
            number = extractNumberEndingAt(lines, col);
            if (number.isBlank()) break;
            numbers.add(Long.parseLong(number.trim()));
            col++;
        }

        return numbers;
    }

    private int getMaxLength(List<String> lines) {
        return lines.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private String extractNumberEndingAt(List<String> lines, int col) {
        return lines.stream()
                .limit(lines.size() - 1)
                .map(line -> getCharAt(line, col))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private char getCharAt(String line, int col) {
        return col < line.length() ? line.charAt(col) : ' ';
    }


}