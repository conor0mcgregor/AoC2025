package software.aoc.day10.b;

import java.util.Arrays;
import java.util.Objects;

public class State {
    private final int[] state;

    public State(int[] state) {
        this.state = state;
    }

    public static State of(String s) {
        String inner = s.substring(1, s.length() - 1).trim();

        if (inner.isEmpty()) {
            return new State(new int[0]);
        }

        int[] result = Arrays.stream(inner.split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        return new State(result);
    }

    public int[] getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        State state1 = (State) o;
        return Objects.deepEquals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(state);
    }
}