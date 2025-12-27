package day4.b;

import org.junit.Test;
import software.aoc.day4.b.PrintingDepartment;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class PrintingDepartmentTest {
    @Test
    public void should_return_zero_when_grid_is_empty() {
        PrintingDepartment department = PrintingDepartment.create();

        assertThat(department.countAccessibleRolls(Arrays.asList()))
                .isEqualTo(0);
    }

    @Test
    public void should_allow_roll_with_three_adjacent_rolls() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@@.",
                "@@.",
                "..."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(4);
    }

    @Test
    public void should_not_allow_roll_with_four_adjacent_rolls() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@@@",
                "@@.",
                "..."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(5);
    }

    @Test
    public void should_count_diagonal_neighbors_as_adjacent() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@.@",
                ".@.",
                "@.@"
        );

        // el centro tiene 4 vecinos diagonales
        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(5);
    }

    @Test
    public void should_handle_edges_without_out_of_bounds() {
        PrintingDepartment department =  PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@.",
                ".."
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }

    @Test
    public void should_count_only_accessible_rolls_in_mixed_grid() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@@@",
                "@.@",
                "@@@"
        );

        // solo el centro tiene menos de 4 vecinos
        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(8);
    }

    @Test
    public void should_match_example_from_problem_statement() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
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
                .isEqualTo(43);
    }


    @Test
    public void should_count_single_roll_as_accessible() {
        PrintingDepartment department = PrintingDepartment.create();

        List<String> grid = Arrays.asList(
                "@"
        );

        assertThat(department.countAccessibleRolls(grid))
                .isEqualTo(1);
    }

    @Test
    public void should_return_zero_when_file_is_empty() throws Exception {
        PrintingDepartment department = PrintingDepartment.create();

        assertThat(department.countAccessibleRollsFrom("day4/empty.txt"))
                .isEqualTo(0);
    }

    @Test
    public void should_match_example_from_problem_statement_file() throws Exception {
        PrintingDepartment department = PrintingDepartment.create();

        assertThat(department.countAccessibleRollsFrom("day4/file1.txt"))
                .isEqualTo(43);
    }

    @Test
    public void should_count_single_roll_from_file() throws Exception {
        PrintingDepartment department = PrintingDepartment.create();

        assertThat(department.countAccessibleRollsFrom("day4/single_roll.txt"))
                .isEqualTo(1);
    }



}
