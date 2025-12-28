package software.aoc.day7.a;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class QuantumManifold {

    private final List<String> layers;

    QuantumManifold(List<String> layers) {
        this.layers = layers;
    }

    int countTimelines(int startPosition) {
        int countCristal = 0;
        Set<Integer> positions = new HashSet<>();
        positions.add(startPosition);
        for (int level = 1; level < layers.size(); level++){
            countCristal = processLevel(countCristal, layers.get(level), positions);
        }
        return countCristal;
    }

    private int processLevel(int currentTimelines, String layer, Set<Integer> positions) {
        List<Integer> snapshot = new ArrayList<>(positions);
        for (Integer rayPosition : snapshot) {
            if (isCrystalAt(layer, rayPosition)) {
                currentTimelines++;
                splitRay(positions, rayPosition);
            }
        }
        return currentTimelines;
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