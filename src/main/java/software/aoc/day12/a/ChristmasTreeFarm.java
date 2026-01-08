package software.aoc.day12.a;

import java.util.List;

public final class ChristmasTreeFarm {
  private ChristmasTreeFarm() {
  }

  public static long solve(List<String> lines) {
    PuzzleInput input = PuzzleInput.parse(lines);
    return input.trees().stream()
        .filter(tree -> tree.allPresentsFit(input.presents()))
        .count();
  }

}