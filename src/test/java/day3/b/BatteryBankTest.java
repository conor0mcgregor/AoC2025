package day3.b;

import org.junit.Test;
import software.aoc.day3.b.BatteryBank;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BatteryBankTest {

    @Test
    public void should_return_max_joltage_in_bank(){
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("987654321111111")).isEqualTo(987654321111L);
        assertThat(batteryBank.getMaxJoltage("811111111111119")).isEqualTo(811111111119L);
        assertThat(batteryBank.getMaxJoltage("234234234234278")).isEqualTo(434234234278L);
        assertThat(batteryBank.getMaxJoltage("818181911112111")).isEqualTo(888911112111L);
    }

    @Test
    public void should_return_same_number_when_bank_has_exactly_12_batteries() {
        BatteryBank batteryBank = BatteryBank.create();

        assertThat(batteryBank.getMaxJoltage("123456789123"))
                .isEqualTo(123456789123L);
    }

    @Test
    public void should_handle_all_equal_digits() {
        BatteryBank batteryBank = BatteryBank.create();

        assertThat(batteryBank.getMaxJoltage("111111111111111"))
                .isEqualTo(111111111111L);
    }

    @Test
    public void should_remove_small_digits_from_the_front_when_possible() {
        BatteryBank batteryBank = BatteryBank.create();

        assertThat(batteryBank.getMaxJoltage("1234567899999"))
                .isEqualTo(234567899999L);
    }
    @Test
    public void should_remove_digits_from_the_end_when_sequence_is_decreasing() {
        BatteryBank batteryBank = BatteryBank.create();

        assertThat(batteryBank.getMaxJoltage("987654321987654"))
                .isEqualTo(987654987654L);
    }

    @Test
    public void should_handle_complex_mixed_sequence() {
        BatteryBank batteryBank = BatteryBank.create();

        assertThat(batteryBank.getMaxJoltage("919191919191919"))
                .isEqualTo(999919191919L);
    }





    @Test
    public void should_sum_max_joltage_from_file() throws URISyntaxException, IOException {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.sumAllMaxJoltageFromFile("day3/file1.txt")).isEqualTo(3121910778619L);
    }




}
