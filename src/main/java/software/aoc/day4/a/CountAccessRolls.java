package software.aoc.day4.a;

import java.util.List;

public class CountAccessRolls implements DepertmentParser{
    @Override
    public int parse(List<String> grid) {
        int count = 0;
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (grid.get(i).charAt(j) == '@' && isAccessible(grid, i, j)) {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isAccessible(List<String> grid, int row, int col) {
        int neighbors = countNeighbors(grid, row, col);
        return neighbors < 4;
    }

    private int countNeighbors(List<String> grid, int row, int col) {
        int count = 0;

        int[] rowOffsets = {-1, -1, -1,  0,  0,  1,  1,  1};
        int[] colOffsets = {-1,  0,  1, -1,  1, -1,  0,  1};

        for (int i = 0; i < 8; i++) {
            int newRow = row + rowOffsets[i];
            int newCol = col + colOffsets[i];

            if (isValidPosition(grid, newRow, newCol)) {
                if (grid.get(newRow).charAt(newCol) == '@') {
                    count++;
                }
            }
        }

        return count;
    }

    private boolean isValidPosition(List<String> grid, int row, int col) {
        return row >= 0 && row < grid.size() &&
                col >= 0 && col < grid.getFirst().length();
    }

}
