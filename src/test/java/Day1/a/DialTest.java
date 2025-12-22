package Day1.a;

import org.junit.Test;
import software.aoc.day1.a.Dial;
import software.aoc.day1.a.DialManeger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DialTest {
    private static final String orders = """
            L68
            L30
            R48
            L5
            R60
            L55
            L1
            L99
            R14
            L82
            """;

    @Test
    public void given_dial_creation_should_initialize_position_to_50() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.getPosition()).isEqualTo(50);
    }

    @Test
    public void given_right_orders_should_rotate_dial_clockwise() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.spin("R10")).isEqualTo(60);
        assertThat(dialManeger.spin("R5")).isEqualTo(65);
        assertThat(dialManeger.spin("R30")).isEqualTo(95);
        assertThat(dialManeger.spin("R9")).isEqualTo(4);
        assertThat(dialManeger.spin("R96")).isEqualTo(0);
    }

    @Test
    public void given_left_orders_should_rotate_dial_counterclockwise() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.spin("L10")).isEqualTo(40);
        assertThat(dialManeger.spin("L5")).isEqualTo(35);
        assertThat(dialManeger.spin("L30")).isEqualTo(5);
        assertThat(dialManeger.spin("L5")).isEqualTo(0);
        assertThat(dialManeger.spin("L2")).isEqualTo(98);
    }

    @Test
    public void given_large_rotation_orders_should_wrap_position_correctly() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.spin("R500")).isEqualTo(50);
        assertThat(dialManeger.spin("L550")).isEqualTo(0);
        assertThat(dialManeger.spin("R1000")).isEqualTo(0);
    }

    @Test
    public void given_multiple_rotation_string_should_wrap_position_correctly() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.ordersOfSpins(orders)).isEqualTo(32);
    }

    @Test
    public void should_calculate_final_position_from_spin_sequence_file() throws IOException, URISyntaxException {
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia1.txt")).isEqualTo(15);
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia2.txt")).isEqualTo(29);
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia3.txt")).isEqualTo(0);
    }

    @Test
    public void should_validate_correct_password() throws IOException, URISyntaxException {
        DialManeger dialManeger = DialManeger.create();
        dialManeger.ordersOfSpinsInFile("day1/a/secuencia3.txt");

        assertThat(dialManeger.getPassword()).isEqualTo(4);
    }


}

