package Day1.b;

import org.junit.Test;
import software.aoc.day1.b.DialManeger;

import java.io.IOException;
import java.net.URISyntaxException;

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
        assertThat(dialManeger.spin("R10").getPosition()).isEqualTo(60);
        assertThat(dialManeger.spin("R5").getPosition()).isEqualTo(65);
        assertThat(dialManeger.spin("R30").getPosition()).isEqualTo(95);
        assertThat(dialManeger.spin("R9").getPosition()).isEqualTo(4);
        assertThat(dialManeger.spin("R96").getPosition()).isEqualTo(0);
    }

    @Test
    public void given_left_orders_should_rotate_dial_counterclockwise() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.spin("L10").getPosition()).isEqualTo(40);
        assertThat(dialManeger.spin("L5").getPosition()).isEqualTo(35);
        assertThat(dialManeger.spin("L30").getPosition()).isEqualTo(5);
        assertThat(dialManeger.spin("L5").getPosition()).isEqualTo(0);
        assertThat(dialManeger.spin("L2").getPosition()).isEqualTo(98);
    }

    @Test
    public void given_large_rotation_orders_should_wrap_position_correctly() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.spin("R500").getPosition()).isEqualTo(50);
        assertThat(dialManeger.spin("L550").getPosition()).isEqualTo(0);
        assertThat(dialManeger.spin("R1000").getPosition()).isEqualTo(0);
    }

    @Test
    public void given_multiple_rotation_string_should_wrap_position_correctly() {
        DialManeger dialManeger = DialManeger.create();
        assertThat(dialManeger.ordersOfSpins(orders).getPosition()).isEqualTo(32);
    }

    @Test
    public void should_calculate_final_position_from_spin_sequence_file() throws IOException, URISyntaxException {
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia1.txt").getPosition()).isEqualTo(15);
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia2.txt").getPosition()).isEqualTo(29);
        assertThat(DialManeger.create().ordersOfSpinsInFile("day1/a/secuencia3.txt").getPosition()).isEqualTo(0);
    }

    @Test
    public void should_validate_correct_password_with_R() throws IOException, URISyntaxException {
        assertThat(DialManeger.create().spin("R50").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("R55").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("R100").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("R150").getPassword()).isEqualTo(2);
        assertThat(DialManeger.create().spin("R250").getPassword()).isEqualTo(3);
        assertThat(DialManeger.create().spin("R1").getPassword()).isEqualTo(0);
    }
    @Test
    public void should_validate_correct_password_with_L() throws IOException, URISyntaxException {
        assertThat(DialManeger.create().spin("L50").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("L55").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("L100").getPassword()).isEqualTo(1);
        assertThat(DialManeger.create().spin("L150").getPassword()).isEqualTo(2);
        assertThat(DialManeger.create().spin("L155").getPassword()).isEqualTo(2);
        assertThat(DialManeger.create().spin("L250").getPassword()).isEqualTo(3);
        assertThat(DialManeger.create().spin("L1").getPassword()).isEqualTo(0);
    }
    @Test
    public void should_validate_correct_password_with_file() throws IOException, URISyntaxException {
        DialManeger dialManeger = DialManeger.create();
        dialManeger.ordersOfSpinsInFile("day1/b/secuencia1.txt");
        assertThat(dialManeger.getPassword()).isEqualTo(4);
    }

    @Test
    public void should_validate_correct_password_with_aoc_input() throws IOException, URISyntaxException {
        DialManeger dialManeger = DialManeger.create();
        dialManeger.ordersOfSpinsInFile("day1/b/input_test.txt");
        assertThat(dialManeger.getPassword()).isEqualTo(6);
    }




}

