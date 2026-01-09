package software.aoc.day6.a;

import java.util.List;

public record Problem(List<Long> numbers, char operator) {
    long solve() {
        return operator == '+'
                ? sum()
                : multiply();
    }

    private long sum() {
        return numbers.stream().mapToLong(Long::longValue).sum();
    }

    private long multiply() {
        long result;
        result = numbers.stream()
                .mapToLong(Long::longValue)
                .reduce(1L, (a, b) -> a * b);
        return result;
    }
}
