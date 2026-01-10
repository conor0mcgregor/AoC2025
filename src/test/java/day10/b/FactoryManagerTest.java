package day10.b;

import org.junit.jupiter.api.Test;
import software.aoc.day10.b.FactoryManager;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
public class FactoryManagerTest {

    @Test
    void should_sum_minimum_pulses_for_all_machines_from_example_file() throws URISyntaxException, IOException {
        FactoryManager factoryManager = FactoryManager.create();
        long result = factoryManager.getSumMinPulsesFrom("day10/file1.txt");
        assertThat(result).isEqualTo(33);
    }

    @Test
    void should_calculate_minimum_pulses_for_single_machine_with_two_counters() throws IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [####] (0) (1) {1,5}
            """;

        long result = factoryManager.getSumMinPulses(machines);
        assertThat(result).isEqualTo(6);
    }

    @Test
    void should_sum_minimum_pulses_across_multiple_identical_machines() throws IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [####] (0) (1) {1,0}
            [####] (0) (1) {1,0}
            [####] (0) (1) {1,0}
            [####] (0) (1) {1,0}
            [####] (0) (1) {1,0}
            """;

        long result = factoryManager.getSumMinPulses(machines);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void should_return_one_pulse_when_single_counter_requires_one_increment() throws IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.] (0) {1}
            """;

        long result = factoryManager.getSumMinPulses(machines);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_ignore_buttons_that_do_not_contribute_to_target_counters() throws IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [.#] (0) (0) (0) {1}
            """;

        long result = factoryManager.getSumMinPulses(machines);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void should_minimize_pulses_by_using_multi_counter_buttons() throws IOException {
        FactoryManager factoryManager = FactoryManager.create();

        String machines = """
            [....] (0) (1) {2,5}
            """;

        long result = factoryManager.getSumMinPulses(machines);
        assertThat(result).isEqualTo(7);
    }
}
