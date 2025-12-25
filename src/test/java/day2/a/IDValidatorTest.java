package day2.a;

import org.junit.Test;
import software.aoc.day2.a.IDValidator;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IDValidatorTest {

    @Test
    public void should_return_false_with_wrong_ID() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.isValidId(1)).isEqualTo(false);
        assertThat(idValidator.isValidId(5)).isEqualTo(false);
        assertThat(idValidator.isValidId(10)).isEqualTo(false);
        assertThat(idValidator.isValidId(1050)).isEqualTo(false);
        assertThat(idValidator.isValidId(1256)).isEqualTo(false);
        assertThat(idValidator.isValidId(1001)).isEqualTo(false);
        assertThat(idValidator.isValidId(101)).isEqualTo(false);
        assertThat(idValidator.isValidId(555)).isEqualTo(false);
    }
    @Test
    public void should_return_true_with_correct_ID() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.isValidId(11)).isEqualTo(true);
        assertThat(idValidator.isValidId(1010)).isEqualTo(true);
        assertThat(idValidator.isValidId(55)).isEqualTo(true);
        assertThat(idValidator.isValidId(5959)).isEqualTo(true);
        assertThat(idValidator.isValidId(123123)).isEqualTo(true);
        assertThat(idValidator.isValidId(2121)).isEqualTo(true);
        assertThat(idValidator.isValidId(88)).isEqualTo(true);
        assertThat(idValidator.isValidId(12891289)).isEqualTo(true);
    }

    @Test
    public void given_range_should_sum_invalids_ids() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.sumInvalidIds(11,22)).isEqualTo(33);
        assertThat(idValidator.sumInvalidIds(95,115)).isEqualTo(99);
        assertThat(idValidator.sumInvalidIds(998,1012)).isEqualTo(1010);
        assertThat(idValidator.sumInvalidIds(1188511880,1188511890)).isEqualTo(1188511885);
    }

    @Test
    public void should_sum_all_invalid_id_from_file() throws URISyntaxException, IOException {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.sumInvalidIdsFromFile("day2/a/input_aoc.txt")).isEqualTo(1227775554);
    }

}
