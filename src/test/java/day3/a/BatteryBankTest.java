package day3.a;

import org.junit.Test;
import software.aoc.day3.a.BatteryBank;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BatteryBankTest {

    @Test
    public void should_return_joltage_when_bank_has_only_two_batteries() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("12")).isEqualTo(12);
    }

    @Test
    public void should_return_same_joltage_when_all_digits_are_equal() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("111111")).isEqualTo(11);
    }

    @Test
    public void should_return_highest_possible_pair_in_increasing_sequence() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("123456789")).isEqualTo(89);
    }

    @Test
    public void should_return_first_pair_in_decreasing_sequence() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("987654321")).isEqualTo(98);
    }

    @Test
    public void should_return_max_joltage_when_digits_are_not_consecutive() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("191")).isEqualTo(91);
    }

    @Test
    public void should_return_max_joltage_using_later_digits() {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("9123")).isEqualTo(93);
    }



    @Test
    public void should_return_max_joltage_in_bank(){
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.getMaxJoltage("987654321111111")).isEqualTo(98);
        assertThat(batteryBank.getMaxJoltage("811111111111119")).isEqualTo(89);
        assertThat(batteryBank.getMaxJoltage("234234234234278")).isEqualTo(78);
        assertThat(batteryBank.getMaxJoltage("818181911112111")).isEqualTo(92);
    }

    @Test
    public void should_sum_joltage_when_file_has_one_bank() throws URISyntaxException, IOException {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.sumAllMaxJoltageFromFile("day3/a/single_bank.txt"))
                .isEqualTo(98);
    }

    @Test
    public void should_sum_multiple_simple_banks() throws URISyntaxException, IOException {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.sumAllMaxJoltageFromFile("day3/a/simple_banks.txt"))
                .isEqualTo(12 + 34 + 56);
    }

    @Test
    public void should_sum_joltage_from_minimal_banks() throws URISyntaxException, IOException {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.sumAllMaxJoltageFromFile("day3/a/minimal_banks.txt"))
                .isEqualTo(11 + 22 + 33);
    }




    @Test
    public void should_sum_max_joltage_from_file() throws URISyntaxException, IOException {
        BatteryBank batteryBank = BatteryBank.create();
        assertThat(batteryBank.sumAllMaxJoltageFromFile("day3/a/file1.txt")).isEqualTo(357);
    }




}
