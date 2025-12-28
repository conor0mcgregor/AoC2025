package day9;

import org.junit.jupiter.api.Test;
import software.aoc.day9.a.CinemaSolver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CinemaSolverTest {
    @Test
    void shouldReturnMaxRectangle() throws URISyntaxException, IOException {
        CinemaSolver cinemaSolver = CinemaSolver.create();
        assertThat(cinemaSolver.findMaxRectangle("day9/file1.txt"))
                .isEqualTo(50);
    }

    @Test
    void shouldReturnAreaOneWhenSamePoint() throws URISyntaxException, IOException {
        CinemaSolver cinemaSolver = CinemaSolver.create();

        List<String> points = List.of(
            "5,5",
            "5,5"
        );

        assertThat(cinemaSolver.findMaxRectangle(points))
                .isEqualTo(1);
    }

    @Test
    void shouldCalculateHorizontalRectangleCorrectly() throws URISyntaxException, IOException {
        CinemaSolver cinemaSolver = CinemaSolver.create();

        List<String> points = List.of(
            "2,3",
            "5,3"
        );

        assertThat(cinemaSolver.findMaxRectangle(points))
                .isEqualTo(4);
    }

    @Test
    void shouldPickLargestPossibleRectangle() throws URISyntaxException, IOException {
        CinemaSolver cinemaSolver = CinemaSolver.create();

        List<String> points = List.of(
            "1,1",
            "3,3",
            "10,1",
            "10,5"
        );

        assertThat(cinemaSolver.findMaxRectangle(points))
                .isEqualTo(50);
    }

}
