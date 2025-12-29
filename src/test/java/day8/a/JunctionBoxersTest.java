package day8.a;

import org.junit.jupiter.api.Test;
import software.aoc.day8.a.JunctionBoxes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JunctionBoxersTest {
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
