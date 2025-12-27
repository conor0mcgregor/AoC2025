package software.aoc.day6.b;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public long solveWorksheet(List<String> lines) {
        List<Problem> problems = parseProblems(lines);

        return problems.stream().mapToLong(Problem::solve).sum();
    }

    private List<Problem> parseProblems(List<String> lines) {
        List<Problem> problems = new ArrayList<>();
        String operators = lines.getLast();
        int columns = operators.length();

        for (int col = 0; col < columns; col++) {
            char operator = operators.charAt(col);
            if(operator == ' ') continue;
            List<Long> numbers = extractNumbersFromColumn(lines, col);
            problems.add(new Problem(numbers, operator));

        }

        return problems;
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
                .limit(lines.size() - 1) // Excluir última fila (operadores)
                .map(line -> getCharAt(line, col))
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private char getCharAt(String line, int col) {
        return col < line.length() ? line.charAt(col) : ' ';
    }

    private record Problem(List<Long> numbers, char operator) {

        long solve() {
                long result = 0;

                if (operator == '+') {
                    result = numbers.stream().mapToLong(Long::longValue).sum();
                } else if (operator == '*') {
                    result = numbers.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
                }

                return result;
            }
        }
}