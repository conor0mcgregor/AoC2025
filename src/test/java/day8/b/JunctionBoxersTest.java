package day8.b;

import org.junit.jupiter.api.Test;
import software.aoc.day8.b.JunctionBoxes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JunctionBoxersTest {

    @Test
    void should_return_the_correct_pruduct_x_uf_last_conection() throws URISyntaxException, IOException {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        long result = junctionBoxes.getProductX("day8/file1.txt");
        assertThat(result).isEqualTo(25272);
    }

    @Test
    void should_return_product_x_when_only_two_boxes_exist() {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "3,0,0",
                "7,10,10"
        );

        long result = junctionBoxes.getProductX(input);
        assertThat(result).isEqualTo(21); // 3 * 7
    }

    @Test
    void should_return_product_x_for_boxes_in_a_line() {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "1,0,0",
                "2,0,0",
                "3,0,0",
                "10,0,0"
        );

        long result = junctionBoxes.getProductX(input);
        assertThat(result).isEqualTo(30); // 3 * 10
    }

    @Test
    void should_not_use_the_longest_edge_but_the_last_union() {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "0,0,0",
                "1,0,0",
                "2,0,0",
                "100,0,0"
        );

        // MST:
        // 0-1
        // 1-2
        // 2-100  ← última unión
        long result = junctionBoxes.getProductX(input);

        assertThat(result).isEqualTo(200); // 2 * 100
    }




    @Test
    void should_return_the_correct_result_of_the_input() throws URISyntaxException, IOException {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        long result = junctionBoxes.getBiggersConnection("day8/file1.txt", 10);
        assertThat(result).isEqualTo(40);
    }

    @Test
    void should_return_1_when_only_one_junction_box(){
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "0,0,0"
        );

        long result = junctionBoxes.getBiggersConnection(input, 10);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_connect_two_boxes_into_one_circuit() {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "0,0,0",
                "1,0,0"
        );

        long result = junctionBoxes.getBiggersConnection(input, 1);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void should_not_create_duplicate_connections() {
        JunctionBoxes junctionBoxes = JunctionBoxes.create();

        List<String> input = List.of(
                "0,0,0",
                "1,0,0",
                "2,0,0"
        );

        long result = junctionBoxes.getBiggersConnection(input, 3);

        assertThat(result).isEqualTo(3);
    }







}
