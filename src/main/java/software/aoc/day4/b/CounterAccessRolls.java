package software.aoc.day4.b;

import java.util.ArrayList;
import java.util.List;

public class CounterAccessRolls implements DepartmentParser {
    private List<String> grid;

    private CounterAccessRolls(List<String> grid) {
        this.grid = new ArrayList<>(grid);
    }

    public static CounterAccessRolls create(List<String> grid) {
        return new CounterAccessRolls(grid);
    }

    @Override
    public int parse() {
        int count = 0;
        boolean change = true;
        while (change){
            change = false;
            for (int i = 0; i < grid.size(); i++) {
                for (int j = 0; j < grid.get(i).length(); j++) {
                    if (grid.get(i).charAt(j) == '@' && isAccessible(i, j)) {
                        removeRoll(i, j);
                        count++;
                        change = true;
                    }
                }
            }
        }
        return count;
    }

    private void removeRoll(int row, int col) {
        String currentRow = grid.get(row);

        String newRow = currentRow.substring(0, col) + '.' + currentRow.substring(col + 1);

        grid.set(row, newRow);
    }

    private boolean isAccessible(int row, int col) {
        int neighbors = countNeighbors(row, col);
        return neighbors < 4;
    }

    private int countNeighbors(int row, int col) {
        int count = 0;

        int[] rowOffsets = {-1, -1, -1,  0,  0,  1,  1,  1};
        int[] colOffsets = {-1,  0,  1, -1,  1, -1,  0,  1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (isValidPosition(newRow, newCol)) {
                if (grid.get(newRow).charAt(newCol) == '@') {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < grid.size() &&
                col >= 0 && col < grid.getFirst().length();
    }

}
