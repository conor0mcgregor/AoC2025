package software.aoc.day7.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TachyonManifold {

    private final FileReader reader;

    private TachyonManifold() {
        this.reader = new ResourceFileReader();
    }

    public static TachyonManifold create() {
        return new TachyonManifold();
    }

    public int countRays(String fileName) throws URISyntaxException, IOException {
        List<String> layers = loadLayers(fileName);


        QuantumSimulator manifold = new QuantumSpace(layers);
        return manifold.countTimelines();
    }

    private List<String> loadLayers(String fileName) throws URISyntaxException, IOException {
        return reader.read(fileName).lines().toList();
    }
}