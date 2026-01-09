package software.aoc.day8.b;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class JunctionBoxes {
    private final GraphCollection graphSet;
    private List<Edge> edges;
    private final List<Node> nodes;
    private final FileReader reader;

    private JunctionBoxes() {
        this.graphSet = new GraphSet();
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.reader = new ResourceFileReader();
    }

    public static JunctionBoxes create(){
        return new JunctionBoxes();
    }

    public long getBiggersConnection(String fileName, int numConections) throws URISyntaxException, IOException {
        parserNodes(reader.read(fileName).lines());
        parserEdges();

        groupNodes(numConections);

        return productTreeBiggers();
    }

    public long getProductX(String fileName) throws URISyntaxException, IOException {
        return getProductX(reader.read(fileName).readAllLines());
    }

    public long getProductX(List<String> dates){
        parserNodes(dates.stream());
        parserEdges();

        return productXLastConection();
    }

    private long productXLastConection() {
        for(Edge edge : edges){
            graphSet.addNewConnection(edge.getNodeA(), edge.getNodeB());
            if(graphSet.lenght() == 1){
                return multiplyX(edge);
            }
        }
        return 0;
    }

    private static long multiplyX(Edge edge) {
        return (long) edge.getNodeA().getPoint().x() * edge.getNodeB().getPoint().x();
    }


    public long getBiggersConnection(List<String> dates, int numConections){
        parserNodes(dates.stream());
        parserEdges();

        groupNodes(numConections);

        return productTreeBiggers();
    }

    private long productTreeBiggers() {
        List<Integer> sizes = graphSet.graphsSizes();
        return sizes.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToLong(Integer::longValue)
                .reduce(1, (a, b) -> a * b);
    }

    private void groupNodes(int limit) {
        edges.stream()
                .limit(limit)
                .forEach(edge ->
                        graphSet.addNewConnection(edge.getNodeA(), edge.getNodeB())
                );
    }

    private void parserEdges() {
        edges = IntStream.range(0, nodes.size())
                .boxed()
                .flatMap(i -> IntStream.range(i + 1, nodes.size())
                        .mapToObj(j -> new Edge(nodes.get(i), nodes.get(j))))
                .sorted()
                .toList();
    }


    private void parserNodes(Stream<String> lines) {
        AtomicInteger index = new AtomicInteger();

        List<Node> newNodes = lines.map(line -> line.split(","))
                .map(coords -> new Node(
                        index.getAndIncrement(),
                        Integer.parseInt(coords[0]),
                        Integer.parseInt(coords[1]),
                        Integer.parseInt(coords[2])
                ))
                .toList();
        this.nodes.addAll(newNodes);
        newNodes.forEach(graphSet::addSingleNode);
    }

}
