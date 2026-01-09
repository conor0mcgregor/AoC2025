package software.aoc.day6.b;

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
        return numbers.stream().mapToLong(Long::longValue).reduce(1, (a, b) -> a * b);
    }
}
