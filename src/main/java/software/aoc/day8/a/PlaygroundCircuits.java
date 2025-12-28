package software.aoc.day8.a;

import software.aoc.day7.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class PlaygroundCircuits {
    private ResourceFileReader reader;
    private PlaygroundCircuits() {
        this.reader = new ResourceFileReader();
    }
    public static PlaygroundCircuits create(){
        return new PlaygroundCircuits();
    }

    public long parserPoints(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        return solve(br.readAllLines());
    }

    public long solve(List<String> coordinates) {
        List<Point> points = transforToPoints(coordinates);

        List<Edge> edges = calculedDistance(points);

        Collections.sort(edges);

        UnionFind dsu = apply(points, edges);

        Map<Integer, Integer> circuitSizes = new HashMap<>();
        for (int i = 0; i < points.size(); i++) {
            int root = dsu.find(i);
            // Solo contamos el tamaño de la raíz una vez por cada elemento
            if (i == root) {
                circuitSizes.put(root, dsu.size[root]);
            }
        }

        List<Integer> sizes = new ArrayList<>(circuitSizes.values());
        Collections.sort(sizes, Collections.reverseOrder());

        // 8. Multiplicar los 3 más grandes
        long result = 1;
        for (int i = 0; i < Math.min(3, sizes.size()); i++) {
            result *= sizes.get(i);
        }

        return result;

    }

    private UnionFind apply(List<Point> points, List<Edge> edges) {
        UnionFind dsu = new UnionFind(points.size());
        int limit = Math.min(1000, edges.size());
        for (int i = 0; i < limit; i++) {
            Edge e = edges.get(i);
            dsu.union(e.getA(), e.getB());
        }
        return dsu;
    }

    private List<Edge> calculedDistance(List<Point> points) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point p1 = points.get(i);
                Point p2 = points.get(j);
                double dSq = Math.pow(p1.x() - p2.x(), 2) +
                        Math.pow(p1.y() - p2.y(), 2) +
                        Math.pow(p1.z() - p2.z(), 2);
                edges.add(new Edge(i, j, dSq));
            }
        }
        return edges;
    }

    private List<Point> transforToPoints(List<String> coordinates) {
        List<Point> points = new ArrayList<>();
        for(int i = 0; i< coordinates.size() ; i++){
            String[] coords = coordinates.get(i).split(",");
            points.add(new Point(
                    i,
                    Integer.parseInt(coords[0]),
                    Integer.parseInt(coords[1]),
                    Integer.parseInt(coords[2])
            ));
        }
        return points;
    }

}
