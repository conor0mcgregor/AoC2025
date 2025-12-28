package software.aoc.day7.b;
import java.util.*;

class QuantumManifold {

    private final Map<String, Long> memo = new HashMap<>();
    private final List<String> grid;
    private int lastRow;

    QuantumManifold(List<String> layers) {
        grid = layers;
    }

    public long countTimelines() {
        lastRow = grid.size() - 1;
        memo.clear();

        int startCol = grid.getFirst().indexOf('S');
        return getTimelines(0, startCol);
    }
    private long getTimelines(int row, int col) {
        // Caso base: fuera de límites (si el movimiento lateral se sale)
        if (col < 0 || col >= grid.getFirst().length()) {
            return 0;
        }

        // Crear una clave única para la memoización
        String key = row + "," + col;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Caso base: llegamos a la última fila
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