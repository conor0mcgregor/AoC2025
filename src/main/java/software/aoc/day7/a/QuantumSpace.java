package software.aoc.day7.a;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class QuantumSpace implements QuantumSimulator{

    private final List<String> layers;

    QuantumSpace(List<String> layers) {
        this.layers = layers;
    }

    @Override
    public int countTimelines() {
        Set<Integer> positions = new HashSet<>();
        addStartPosition(positions);

        return IntStream.range(1, layers.size())
                .reduce(0, (acc, level) -> processLevel(acc, layers.get(level), positions));
    }

    private void addStartPosition(Set<Integer> positions) {
        int startPosition = layers.getFirst().indexOf('S');

        positions.add(startPosition);
    }

    private int processLevel(int currentTimelines, String layer, Set<Integer> positions) {
        for (Integer rayPosition : List.copyOf(positions)) {
            currentTimelines += cristalIn(layer, positions, rayPosition);
        }
        return currentTimelines;
    }

    private int cristalIn(String layer, Set<Integer> positions, Integer rayPosition) {
        if (isCrystalAt(layer, rayPosition)) {
            splitRay(positions, rayPosition);
            return 1;
        }
        return 0;
    }


    private static void splitRay(Set<Integer> positions, Integer rayPotition) {
        positions.add(rayPotition -1);
        positions.remove(rayPotition);
        positions.add(rayPotition +1);
    }

    private boolean isCrystalAt(String layer, int position) {
        if (position < 0 || position >= layer.length()) {
            return false;
        }
        return layer.charAt(position) == '^';
    }
    
}