package software.aoc.day7.a;

import software.aoc.day7.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TachyonManifold {

    private final ResourceFileReader reader;

    private TachyonManifold() {
        this.reader = new ResourceFileReader();
    }

    public static TachyonManifold create() {
        return new TachyonManifold();
    }

    public int countRays(String fileName) throws URISyntaxException, IOException {
        List<String> layers = loadLayers(fileName);

        int startPosition = findStartPosition(layers.getFirst());

        QuantumManifold manifold = new QuantumManifold(layers);
        return manifold.countTimelines(startPosition);
    }

    private List<String> loadLayers(String fileName) throws URISyntaxException, IOException {
        return reader.read(fileName).lines().toList();
    }

    private int findStartPosition(String firstLayer) {
        return firstLayer.indexOf('S');
    }
}