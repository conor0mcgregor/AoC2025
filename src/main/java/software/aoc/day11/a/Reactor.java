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

    public Reactor() {
        this.reader = new ResourceFileReader();
    }

    public long countPaths(String fileName) throws URISyntaxException, IOException {
        Digraph digraph = parserPaths(fileName);
        return digraph.getNumPaths("you", "out");
    }
    public long countPaths(List<String> dates){
        Digraph digraph = buildGraph(dates.stream());
        return digraph.getNumPaths("you", "out");
    }

    private Digraph parserPaths(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        return buildGraph(br.lines());
    }

    private Digraph buildGraph(Stream<String> lines) {
        Digraph digraph = new Digraph();
        lines.forEach(line -> parserNodes(digraph, line));
        return digraph;
    }

    private void parserNodes(Digraph digraph, String line) {
        String idOrigin = line.split(": ")[0];
        String[] idsDest = line.split(": ")[1].split(" ");
        digraph.addDestsNodes(idOrigin, idsDest);
    }


}
