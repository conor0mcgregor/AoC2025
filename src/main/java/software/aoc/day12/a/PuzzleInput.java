package software.aoc.day12.a;

import java.util.ArrayList;
import java.util.List;

record PuzzleInput(List<Present> presents, List<Tree> trees) {

    static PuzzleInput parse(List<String> lines) {
        List<List<String>> blocks = splitByEmptyLines(lines);
        List<Present> presents = blocks.stream().limit(blocks.size() - 1L).map(Present::parse).toList();
        List<Tree> trees = blocks.getLast().stream().map(Tree::parse).toList();
        return new PuzzleInput(presents, trees);
    }

    private static List<List<String>> splitByEmptyLines(List<String> lines) {
        List<List<String>> blocks = new ArrayList<>();
        List<String> currentBlock = new ArrayList<>();

        for (String line : lines) {
            if (line.isBlank()) {
                currentBlock = addBlock(blocks, currentBlock);
            } else {
                currentBlock.add(line);
            }
        }
        blocks.add(currentBlock);
        return blocks;
    }

    private static List<String> addBlock(List<List<String>> blocks, List<String> currentBlock) {
        blocks.add(currentBlock);
        currentBlock = new ArrayList<>();
        return currentBlock;
    }
}
