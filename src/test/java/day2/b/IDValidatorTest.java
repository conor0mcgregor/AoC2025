package day2.b;

import org.junit.Test;
import software.aoc.day2.b.GiftShop;
import software.aoc.day2.b.IDValidator;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class IDValidatorTest {

    @Test
    public void should_return_false_with_wrong_ID() {
        IDValidator idValidator = IDValidator.create();
        assertThat(idValidator.isInvalidId(1)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(113)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(1134)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(101020)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(11141)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(5665)).isEqualTo(false);
        assertThat(idValidator.isInvalidId(572226)).isEqualTo(false);
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
        assertThat(idValidator.sumInvalidIds(11,22)).isEqualTo(33);
        assertThat(idValidator.sumInvalidIds(95,115)).isEqualTo(210);
        assertThat(idValidator.sumInvalidIds(998,1012)).isEqualTo(2009);
        assertThat(idValidator.sumInvalidIds(1188511880,1188511890)).isEqualTo(1188511885);
    }

    @Test
    public void should_sum_all_invalid_id_from_file() throws URISyntaxException, IOException {
        GiftShop giftShop = GiftShop.create();
        assertThat(giftShop.sumInvalidIdsFromFile("day2/a/input_aoc.txt")).isEqualTo(4174379265L);
    }

}
