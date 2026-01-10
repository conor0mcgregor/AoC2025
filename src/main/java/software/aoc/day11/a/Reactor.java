package software.aoc.day11.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Stream;

public class Reactor {
    private final FileReader reader;
    private PathGraph digraph;

    private Reactor() {
        this.reader = new ResourceFileReader();
    }
    public static Reactor create() { return new Reactor(); }

    public long countPaths(String fileName) throws URISyntaxException, IOException {
        parserPaths(fileName);
        return digraph.getNumPaths("you", "out");
    }
    public long countPaths(List<String> dates){
        buildGraph(dates.stream());
        return digraph.getNumPaths("you", "out");
    }

    private void parserPaths(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        buildGraph(br.lines());
    }

    private void buildGraph(Stream<String> lines) {
        Digraph digraph = new Digraph();
        lines.forEach(line -> parserNodes(digraph, line));
        this.digraph = digraph;
    }

    private void parserNodes(Digraph digraph, String line) {
        String idOrigin = line.split(": ")[0];
        String[] idsDest = line.split(": ")[1].split(" ");
        digraph.addDestsNodes(idOrigin, idsDest);
    }


}
