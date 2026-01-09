package software.aoc.day9.a;

import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CinemaSolver {
    private final ResourceFileReader reader;

    private CinemaSolver() {
        this.reader = new ResourceFileReader();
    }

    public static CinemaSolver create() {
        return new CinemaSolver();
    }

    public long findMaxRectangle(String fileName) throws URISyntaxException, IOException {
        List<Point> points = extractPoints(fileName);
        Board board = new Board(points);
        return board.getBigerRectangle();
    }

    public long findMaxRectangle(List<String> listPoints) throws URISyntaxException, IOException {
        List<Point> points = extractPoints(listPoints);
        Board board = new Board(points);
        return board.getBigerRectangle();
    }


    private List<Point> extractPoints(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        return br.lines().map(this::stringToPoint).toList();
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
