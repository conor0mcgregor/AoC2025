package day6.b;

import org.junit.jupiter.api.Test;
import software.aoc.day6.b.TrashCompactor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrashCompactorTest {
    @Test
    void should_solve_single_addition_problem_from_lines() {
        TrashCompactor compactor = TrashCompactor.create();

        List<String> lines = List.of(
                "12",
                "+"
        );

        assertThat(compactor.solveOperation(lines)).isEqualTo(3);
    }
    @Test
    void should_solve_single_multiplication_problem_from_lines() {
        TrashCompactor compactor = TrashCompactor.create();

        List<String> lines = List.of(
                "3",
                "4",
                "5",
                "*"
        );

        assertThat(compactor.solveOperation(lines)).isEqualTo(345);
    }

    @Test
    void should_solve_two_problems_from_lines() {
        TrashCompactor compactor = TrashCompactor.create();

        List<String> lines = List.of(
                "12  3",
                " 4  5",
                "*   +"
        );

        assertThat(compactor.solveOperation(lines)).isEqualTo(24+35);
    }

    @Test
    void should_match_example_from_problem_statement() {
        TrashCompactor compactor = TrashCompactor.create();

        List<String> lines = List.of(
                "123 328  51 64",
                " 45 64  387 23",
                "  6 98  215 314",
                "*   +   *   +"
        );

        assertThat(compactor.solveOperation(lines)).isEqualTo(3263827);
    }

    @Test
    void should_return_correct_result() throws URISyntaxException, IOException {
        TrashCompactor trashCompactor = TrashCompactor.create();
        assertThat(trashCompactor.solveOperation("day6/file1.txt"))
                .isEqualTo(3263827);
    }


}
