package software.aoc.day12.a;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

record Tree(int width, int height, int[] presentCounts) {

    public static Tree parse(String line) {
        int colonPos = line.indexOf(':');

        String size = line.substring(0, colonPos);
        String[] widthAndHeight = size.split("x");
        int width = Integer.parseInt(widthAndHeight[0]);
        int height = Integer.parseInt(widthAndHeight[1]);

        String[] presentCountStrings = line.substring(colonPos + 2).split(" ");
        int[] presentCounts = stringsToInts(presentCountStrings);

        return new Tree(width, height, presentCounts);
    }

    private static int[] stringsToInts(String[] presentCountStrings) {
        int[] presentCounts = new int[presentCountStrings.length];
        for (int i = 0; i < presentCountStrings.length; i++) {
            presentCounts[i] = Integer.parseInt(presentCountStrings[i]);
        }
        return presentCounts;
    }

    public boolean allPresentsFit(List<Present> presents) {
        List<Present> presentsToFit = expandPresents(presents);

        PresentPacker packer = new PresentPacker(width, height);
        return packer.canFitAllPresents(presentsToFit);
    }


    private List<Present> expandPresents(List<Present> presents) {
        return IntStream.range(0, presentCounts.length)
                .mapToObj(i -> Collections.nCopies(presentCounts[i], presents.get(i)))
                .flatMap(List::stream)
                .toList();
    }
}