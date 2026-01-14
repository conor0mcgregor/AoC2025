package software.aoc.day12.a;

import java.util.List;

public class PresentPacker {

    private final int width;
    private final int height;

    private PresentPacker(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        this.width = width;
        this.height = height;
    }

    public static PresentPacker create(int width, int height) {
        return new PresentPacker(width, height);
    }

    public boolean canFitAllPresents(List<Present> presentsToFit) {
        if (presentsToFit.isEmpty()) {
            return true;
        }

        if (!hasEnoughPixels(presentsToFit)) {
            return false;
        }

        if (fitsWithOptimisticEstimate(presentsToFit)) {
            return true;
        }

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


    private boolean tryPlaceAllPresents(boolean[][] area, List<Present> presents, int index) {
        if (index >= presents.size()) {
            return true;
        }

        Present present = presents.get(index);

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

        for (int row = 0; row <= maxRow; row++) {
            for (int col = 0; col <= maxCol; col++) {
                if (canPlaceAt(area, shape, col, row)) {
                    boolean[][] newArea = cloneArea(area);

                    placeShapeAt(newArea, shape, col, row);

                    if (tryPlaceAllPresents(newArea, presents, index + 1)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canPlaceAt(boolean[][] area, Shape shape, int startCol, int startRow) {
        for (int row = 0; row < shape.height(); row++) {
            for (int col = 0; col < shape.width(); col++) {
                if (shape.parts()[row][col] && area[startRow + row][startCol + col]) {
                    return false;
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