package software.aoc.day12.a;

import java.util.ArrayList;
import java.util.List;

record Tree(int width, int height, int[] presentCounts) {

    static Tree parse(String line) {
        int colonPos = line.indexOf(':');

        String size = line.substring(0, colonPos);
        String[] widthAndHeight = size.split("x");
        int width = Integer.parseInt(widthAndHeight[0]);
        int height = Integer.parseInt(widthAndHeight[1]);

        String[] presentCountStrings = line.substring(colonPos + 2).split(" ");
        int[] presentCounts = new int[presentCountStrings.length];
        for (int i = 0; i < presentCountStrings.length; i++) {
            presentCounts[i] = Integer.parseInt(presentCountStrings[i]);
        }

        return new Tree(width, height, presentCounts);
    }

    boolean allPresentsFit(List<Present> presents) {
        List<Present> presentsToFit = expandPresents(presents);

        PresentPacker packer = new PresentPacker(width, height);
        return packer.canFitAllPresents(presentsToFit);
    }

    private List<Present> expandPresents(List<Present> presents) {
        List<Present> expanded = new ArrayList<>();
        for (int presentIndex = 0; presentIndex < presentCounts.length; presentIndex++) {
            int count = presentCounts[presentIndex];
            Present present = presents.get(presentIndex);
            for (int i = 0; i < count; i++) {
                expanded.add(present);
            }
        }
        return expanded;
    }
}