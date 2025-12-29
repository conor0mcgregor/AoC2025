package day10.a;

import org.junit.jupiter.api.Test;
import software.aoc.day10.a.FactoryManager;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class FactoryManagerTest {
    @Test
    void should_return_the_sum_from_the_example_correctly() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        long result = factoryManager.getSumMinPulsesFromFile("day10/file1.txt");
        assertThat(result).isEqualTo(7);
    }

    @Test
    void should_return_two_in_this_example() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        String machines = """
                [####] (0,2) (1,3) {1}
                """;
        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void should_return_zero_when_single_light_already_correct() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.] (0) {1}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_return_one_when_single_light_needs_toggle() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [#] (0) {1}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_ignore_useless_buttons() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.#] (0) (1) (0,1) {1}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(1);
    }




}
