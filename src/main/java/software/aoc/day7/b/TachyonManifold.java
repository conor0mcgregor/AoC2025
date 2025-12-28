package software.aoc.day7.b;

import software.aoc.day7.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class TachyonManifold {

    private final ResourceFileReader reader;

    private TachyonManifold() {
        this.reader = new ResourceFileReader();
    }

    public static TachyonManifold create() {
        return new TachyonManifold();
    }

    public long countTimeLine(String fileName) throws URISyntaxException, IOException {
        List<String> layers = loadLayers(fileName);
        QuantumManifold manifold = new QuantumManifold(layers);
        return manifold.countTimelines();
    }

    private List<String> loadLayers(String fileName) throws URISyntaxException, IOException {
        return reader.read(fileName).lines().toList();
    }

}