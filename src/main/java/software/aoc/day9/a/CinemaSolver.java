package software.aoc.day9.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CinemaSolver {
    private final FileReader reader;

    private CinemaSolver() {
        this.reader = new ResourceFileReader();
    }

    public static CinemaSolver create() {
        return new CinemaSolver();
    }

    public long findMaxRectangle(String fileName) throws URISyntaxException, IOException {
        return findMaxRectangle(reader.read(fileName).readAllLines());
    }

    public long findMaxRectangle(List<String> listPoints) {
        List<Point> points = extractPoints(listPoints);
        Map board = new Board(points);
        return board.getBigerRectangle();
    }

    private List<Point> extractPoints(List<String> listPoints) {
        return listPoints.stream().map(this::stringToPoint).toList();
    }

    private Point stringToPoint(String coord) {
        String[] coordsArray = coord.split(",");
        int x = Integer.parseInt(coordsArray[0]);
        int y = Integer.parseInt(coordsArray[1]);
        return new Point(x, y);
    }
}
