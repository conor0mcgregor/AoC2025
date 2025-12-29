package day10.b;

import org.junit.jupiter.api.Test;
import software.aoc.day10.b.FactoryManager;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


public class FactoryManagerTest {
    @Test
    void should_return_the_sum_from_the_example_correctly() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        long result = factoryManager.getSumMinPulsesFromFile("day10/file1.txt");
        assertThat(result).isEqualTo(33);
    }

    @Test
    void should_return_five_pulses_in_this_example_part_two() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [####] (0) (1) {1,5}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(6);
    }

    @Test
    void should_return_one_when_single_counter_needs_one_increment() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.] (0) {1}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_ignore_buttons_that_do_not_help_reach_target_part_two() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.#] (0) (0) (0) {1}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_use_multi_counter_button_optimally() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [....] (0,1) (1) {3,3}
            """;

        long result = factoryManager.getSumMinPulsesFrom(machines);
        assertThat(result).isEqualTo(3);
    }





}
