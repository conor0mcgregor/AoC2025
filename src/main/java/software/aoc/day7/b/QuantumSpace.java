package software.aoc.day7.b;
import java.util.*;

public class QuantumSpace implements QuantumSimulator {

    private final Map<String, Long> memo = new HashMap<>();
    private final List<String> grid;
    private int lastRow;

    public QuantumSpace(List<String> layers) {
        grid = layers;
    }

    @Override
    public long countTimelines() {
        lastRow = grid.size() - 1;
        memo.clear();

        int startCol = grid.getFirst().indexOf('S');
        return getTimelines(0, startCol);
    }

    private long getTimelines(int row, int col) {
        if (col < 0 || col >= grid.getFirst().length()) {
            return 0;
        }

        String key = row + "," + col;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (row == lastRow) {
            return 1;
        }

        long result = getResult(row, col);

        memo.put(key, result);
        return result;
    }

    private long getResult(int row, int col) {
        char nextCell = grid.get(row + 1).charAt(col);
        return (nextCell == '.')? getTimelines(row + 1, col): getTimelines(row, col - 1) + getTimelines(row, col + 1);
    }

}