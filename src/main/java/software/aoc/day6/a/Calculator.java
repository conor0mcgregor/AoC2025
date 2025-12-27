package software.aoc.day6.a;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    public long solveWorksheet(List<String> lines) {
        List<Problem> problems = parseProblems(lines);

        long total = 0;
        for (Problem problem : problems) {
            long result = problem.solve();
            total += result;
        }

        return total;
    }

    private List<Problem> parseProblems(List<String> lines) {
        List<Problem> problems = new ArrayList<>();

        String[] operators = lines.getLast().trim().split("\\s+");
        int columns =operators.length;

        // Recorrer columna por columna
        for (int col = 0; col < columns; col++) {
            char operator = operators[col].charAt(0);

            List<Long> numbers = extractNumbersFromColumn(lines, col);
            problems.add(new Problem(numbers, operator));

        }

        return problems;
    }

    /**
     * Extrae los números de una columna específica.
     */
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

    private static class Problem {
        private final List<Long> numbers;
        private final char operator;

        Problem(List<Long> numbers, char operator) {
            this.numbers = numbers;
            this.operator = operator;
        }

        long solve() {
            long result = 0;

            if (operator == '+') {
                result = numbers.stream().mapToLong(Long::longValue).sum();
            } else if (operator == '*') {
                result = numbers.stream()
                        .mapToLong(Long::longValue)
                        .reduce(1L, (a, b) -> a * b);
            }

            return result;
        }
    }
}