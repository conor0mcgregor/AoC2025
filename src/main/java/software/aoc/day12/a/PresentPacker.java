package software.aoc.day12.a;

import java.util.List;

/**
 * Responsible for packing presents into a rectangular area.
 * Uses a backtracking algorithm to determine if all presents fit.
 */
public class PresentPacker {

    private final int width;
    private final int height;

    public PresentPacker(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    /**
     * Checks if all presents can fit in the available area.
     */
    public boolean canFitAllPresents(List<Present> presentsToFit) {
        if (presentsToFit.isEmpty()) {
            return true;
        }

        // Quick check: total pixels
        if (!hasEnoughPixels(presentsToFit)) {
            return false;
        }

        // Optimistic check: based on max dimensions
        if (fitsWithOptimisticEstimate(presentsToFit)) {
            return true;
        }

        // Full backtracking algorithm
        boolean[][] area = new boolean[height][width];
        return tryPlaceAllPresents(area, presentsToFit, 0);
    }

    private boolean hasEnoughPixels(List<Present> presents) {
        int pixelsAvailable = width * height;
        int pixelsRequired = presents.stream()
                .mapToInt(present -> present.shapeRotations().getFirst().pixelCount())
                .sum();
        return pixelsRequired <= pixelsAvailable;
    }

    private boolean fitsWithOptimisticEstimate(List<Present> presents) {
        int maxWidth = presents.stream()
                .mapToInt(p -> p.shapeRotations().getFirst().width())
                .max()
                .orElse(0);

        int maxHeight = presents.stream()
                .mapToInt(p -> p.shapeRotations().getFirst().height())
                .max()
                .orElse(0);

        int slotsAvailable = (width / maxWidth) * (height / maxHeight);
        return presents.size() <= slotsAvailable;
    }

    /**
     * Recursive backtracking to place presents.
     */
    private boolean tryPlaceAllPresents(boolean[][] area, List<Present> presents, int index) {
        if (index >= presents.size()) {
            return true; // All presents placed successfully
        }

        Present present = presents.get(index);

        // Try each rotation of the present
        for (Shape shape : present.shapeRotations()) {
            if (tryPlaceShape(area, shape, presents, index)) {
                return true;
            }
        }

        return false;
    }

    private boolean tryPlaceShape(boolean[][] area, Shape shape, List<Present> presents, int index) {
        int maxCol = width - shape.width();
        int maxRow = height - shape.height();

        // Try each position
        for (int row = 0; row <= maxRow; row++) {
            for (int col = 0; col <= maxCol; col++) {
                if (canPlaceAt(area, shape, col, row)) {
                    // Create a copy of the area
                    boolean[][] newArea = cloneArea(area);

                    // Place the shape
                    placeShapeAt(newArea, shape, col, row);

                    // Try to place remaining presents
                    if (tryPlaceAllPresents(newArea, presents, index + 1)) {
                        return true;
                    }

                    // Backtrack (implicit - we don't modify original area)
                }
            }
        }

        return false;
    }

    private boolean canPlaceAt(boolean[][] area, Shape shape, int startCol, int startRow) {
        for (int row = 0; row < shape.height(); row++) {
            for (int col = 0; col < shape.width(); col++) {
                if (shape.parts()[row][col] && area[startRow + row][startCol + col]) {
                    return false; // Overlap detected
                }
            }
        }
        return true;
    }

    private void placeShapeAt(boolean[][] area, Shape shape, int startCol, int startRow) {
        for (int row = 0; row < shape.height(); row++) {
            for (int col = 0; col < shape.width(); col++) {
                if (shape.parts()[row][col]) {
                    area[startRow + row][startCol + col] = true;
                }
            }
        }
    }

    private boolean[][] cloneArea(boolean[][] area) {
        boolean[][] clone = new boolean[area.length][];
        for (int i = 0; i < area.length; i++) {
            clone[i] = area[i].clone();
        }
        return clone;
    }
}