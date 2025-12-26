package day4.a;

import org.junit.Test;
import software.aoc.day4.a.PrintingDepartment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PrintingDepartmentTest {
    @Test
    public void should_return_zero_when_grid_is_empty() {
        PrintingDepartment department = PrintingDepartment.create();

        assertThat(department.countAccessibleRolls(List.of()))
                .isEqualTo(0);
    }

    @Test
    public void should_allow_roll_with_three_adjacent_rolls() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@@.",
                "@@.",
                "..."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }

    @Test
    public void should_not_allow_roll_with_four_adjacent_rolls() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@@@",
                "@@.",
                "..."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(0);
    }

    @Test
    public void should_count_diagonal_neighbors_as_adjacent() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@.@",
                ".@.",
                "@.@"
        );

        // el centro tiene 4 vecinos diagonales
        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(0);
    }

    @Test
    public void should_handle_edges_without_out_of_bounds() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@.",
                ".."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }

    @Test
    public void should_count_only_accessible_rolls_in_mixed_grid() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@@@",
                "@.@",
                "@@@"
        );

        // solo el centro tiene menos de 4 vecinos
        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }

    @Test
    public void should_match_example_from_problem_statement() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "..@@.@@@@.",
                "@@@.@.@.@@",
                "@@@@@.@.@@",
                "@.@@@@..@.",
                "@@.@@@@.@@",
                ".@@@@@@@.@",
                ".@.@.@.@@@",
                "@.@@@.@@@@",
                ".@@@@@@@@.",
                "@.@.@@@.@."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(13);
    }


    @Test
    public void should_count_single_roll_as_accessible() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = List.of(
                "@"
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }
}
