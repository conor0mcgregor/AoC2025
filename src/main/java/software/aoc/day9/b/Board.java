package software.aoc.day9.b;

import java.util.*;

import static java.lang.Math.abs;

public class Board {

    private final List<Point> redTiles;
    private final List<Integer> xMap = new ArrayList<>();
    private final List<Integer> yMap = new ArrayList<>();

    // Grilla comprimida: 0 = interior/borde (valido), 1 = exterior (invalido)
    // Usamos 1 para invalido para facilitar la suma (si suma > 0, es invalido)
    private int[][] compressedGrid;
    private int[][] prefixSum;

    public Board(List<Point> points) {
        this.redTiles = new ArrayList<>(points);
        if (!points.isEmpty()) {
            compressCoordinates();
            buildCompressedGrid();
            buildPrefixSum();
        }
    }

    private void compressCoordinates() {
        TreeSet<Integer> distinctX = new TreeSet<>();
        TreeSet<Integer> distinctY = new TreeSet<>();

        for (Point p : redTiles) {
            compressPoint(p, distinctX, distinctY);
        }
        addBorders(distinctX, distinctY);

        xMap.addAll(distinctX);
        yMap.addAll(distinctY);
    }

    private static void compressPoint(Point p, TreeSet<Integer> distinctX, TreeSet<Integer> distinctY) {
        distinctX.add(p.x());
        distinctX.add(p.x() + 1); // Importante para rangos
        distinctY.add(p.y());
        distinctY.add(p.y() + 1);
    }

    private void addBorders(TreeSet<Integer> distinctX, TreeSet<Integer> distinctY) {
        int minX = distinctX.first();
        int maxX = distinctX.last();
        int minY = distinctY.first();
        int maxY = distinctY.last();

        distinctX.add(minX - 1);
        distinctX.add(maxX + 2);
        distinctY.add(minY - 1);
        distinctY.add(maxY + 2);
    }
    
    private int getXIndex(int x) { return Collections.binarySearch(xMap, x); }
    private int getYIndex(int y) { return Collections.binarySearch(yMap, y); }


    private void buildCompressedGrid() {
        int w = xMap.size() - 1;
        int h = yMap.size() - 1;

        int[][] tempGrid = buildTempGrid(h, w);

        floodFill(h, w, tempGrid);
    }

    private void floodFill(int h, int w, int[][] tempGrid) {
        compressedGrid = new int[h][w]; // 1 = invalido (exterior), 0 = valido (interior/pared)

        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        boolean[][] visited = new boolean[h][w];
        visited[0][0] = true;
        compressedGrid[0][0] = 1; // Marcar como exterior

        while (!q.isEmpty()) {
            Point curr = q.poll();
            int cx = curr.x();
            int cy = curr.y();

            int[] dx = {0, 0, 1, -1};
            int[] dy = {1, -1, 0, 0};

            for (int k = 0; k < 4; k++) {
                int nx = cx + dx[k];
                int ny = cy + dy[k];

                if (nx >= 0 && nx < w && ny >= 0 && ny < h) {
                    // Si no visitado y NO es una pared
                    if (!visited[ny][nx] && tempGrid[ny][nx] != -1) {
                        visited[ny][nx] = true;
                        compressedGrid[ny][nx] = 1; // Es exterior
                        q.add(new Point(nx, ny));
                    }
                }
            }
        }
    }

    private int[][] buildTempGrid(int h, int w) {
        int[][] tempGrid = new int[h][w];

        int n = redTiles.size();
        for (int i = 0; i < n; i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % n);

            int ix1 = getXIndex(p1.x());
            int ix2 = getXIndex(p2.x());
            int iy1 = getYIndex(p1.y());
            int iy2 = getYIndex(p2.y());

            int xStart = Math.min(ix1, ix2);
            int xEnd = Math.max(ix1, ix2);
            int yStart = Math.min(iy1, iy2);
            int yEnd = Math.max(iy1, iy2);

            if (ix1 == ix2) { // Linea Vertical
                for (int y = yStart; y <= yEnd; y++) tempGrid[y][xStart] = -1;
            } else { // Linea Horizontal
                for (int x = xStart; x <= xEnd; x++) tempGrid[yStart][x] = -1;
            }
        }
        return tempGrid;
    }

    private void buildPrefixSum() {
        int w = compressedGrid[0].length;
        int h = compressedGrid.length;
        prefixSum = new int[h + 1][w + 1];

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                prefixSum[y + 1][x + 1] = compressedGrid[y][x]
                        + prefixSum[y][x + 1]
                        + prefixSum[y + 1][x]
                        - prefixSum[y][x];
            }
        }
    }

    private boolean isValidRect(int x1, int y1, int x2, int y2) {
        int ix1 = getXIndex(Math.min(x1, x2));
        int ix2 = getXIndex(Math.max(x1, x2));
        int iy1 = getYIndex(Math.min(y1, y2));
        int iy2 = getYIndex(Math.max(y1, y2));

        int invalidCount =
                prefixSum[iy2 + 1][ix2 + 1]
                        - prefixSum[iy1][ix2 + 1]
                        - prefixSum[iy2 + 1][ix1]
                        + prefixSum[iy1][ix1];

        return invalidCount == 0;
    }

    public long getMaxRect() {
        long maxArea = 0;
        int n = redTiles.size();

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                Point p1 = redTiles.get(i);
                Point p2 = redTiles.get(j);

                long area = getAreaOf(p1, p2);

                if (area <= maxArea) continue;

                if (isValidRect(p1.x(), p1.y(), p2.x(), p2.y())) maxArea = area;

            }
        }
        return maxArea;
    }

    private static long getAreaOf(Point p1, Point p2) {
        long width = Math.abs((long) p1.x() - p2.x()) + 1;
        long height = Math.abs((long) p1.y() - p2.y()) + 1;
        long area = width * height;
        return area;
    }
}