package day2.a;

import org.junit.Test;
import software.aoc.day2.a.IDValidator;
import software.aoc.day2.b.GiftShop;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IDValidatorTest {

    @Test
    public void should_return_false_with_wrong_ID() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.isInvalidId(1)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(5)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(10)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(1050)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(1256)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(1001)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(101)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(555)).isEqualTo(false);
    }
    @Test
    public void should_return_true_with_correct_ID() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.isInvalidId(11)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(1010)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(55)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(5959)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(123123)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(2121)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(88)).isEqualTo(true);
        assertThat(idValidator.isInvalidId(12891289)).isEqualTo(true);
    }

    @Test
    public void given_range_should_sum_invalids_ids() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.sumInvalidIdInStrRange("11-22")).isEqualTo(33);
        assertThat(idValidator.sumInvalidIdInStrRange("95-115")).isEqualTo(99);
        assertThat(idValidator.sumInvalidIdInStrRange("998-1012")).isEqualTo(1010);
        assertThat(idValidator.sumInvalidIdInStrRange("1188511880-1188511890")).isEqualTo(1188511885);
    }

    @Test
    public void should_sum_all_invalid_id_from_file() throws URISyntaxException, IOException {
        GiftShop giftShop = GiftShop.create();
        assertThat(giftShop.sumInvalidIdsFromFile("day2/a/input_aoc.txt")).isEqualTo(1227775554);
    }

}
