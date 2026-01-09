package software.aoc.day4.a;

import java.util.List;

public class CountAccessRolls implements DepertmentParser{
    private List<String> grid;

    private CountAccessRolls(List<String> grid) {
        this.grid = grid;
    }

    public static CountAccessRolls create(List<String> grid){
        return new CountAccessRolls((grid));
    }

    @Override
    public int parse() {
        int count = 0;

        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).length(); j++) {
                if (grid.get(i).charAt(j) == '@' && isAccessible(i, j)) {
                    count++;
                }
            }
        }

        return count;
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
