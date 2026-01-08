package day12;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.apache.commons.math3.linear.DecompositionSolver;
import org.junit.jupiter.api.Test;
import software.aoc.day12.a.ChristmasTreeFarm;

class ChristmasTreeFarmTest {

    private ChristmasTreeFarm ChristmasTreeFarm;

    @Test
    void exampleFromStatement_shouldReturn2() {
        List<String> input = List.of(
                "0:",
                "###",
                "##.",
                "##.",
                "",
                "1:",
                "###",
                "##.",
                ".##",
                "",
                "2:",
                ".##",
                "###",
                "##.",
                "",
                "3:",
                "##.",
                "###",
                "##.",
                "",
                "4:",
                "###",
                "#..",
                "###",
                "",
                "5:",
                "###",
                ".#.",
                "###",
                "",
                "4x4: 0 0 0 0 2 0",
                "12x5: 1 0 1 0 2 2",
                "12x5: 1 0 1 0 3 2"
        );

        long result = ChristmasTreeFarm.solve(input);

        assertEquals(2, result);
    }

    @Test
    void presentBiggerThanRegion_shouldNotFit() {
        List<String> input = List.of(
                "0:",
                "##",
                "##",
                "",
                "1x1: 1"
        );

        long result = ChristmasTreeFarm.solve(input);

        assertEquals(0, result);
    }

    @Test
    void multipleRegions_mixedResults() {
        List<String> input = List.of(
                "0:",
                "#",
                "",
                "1x1: 1",
                "1x1: 2",
                "2x1: 2"
        );

        long result = ChristmasTreeFarm.solve(input);

        // 1x1 con 1 regalo → sí
        // 1x1 con 2 regalos → no
        // 2x1 con 2 regalos → sí
        assertEquals(2, result);
    }


}

