package day5.b;

import org.junit.jupiter.api.Test;
import software.aoc.day5.b.CounterFreshId;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CounterFreshIdTest {
    @Test
    void should_count_fresh_ids_from_file() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.countFreshId("day5/file1.txt");
        assertThat(result).isEqualTo(3);
    }

    @Test
    void should_return_zero_if_no_fresh_ids() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.countFreshId("day5/file2.txt");
        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_handle_empty_ranges_section() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.countFreshId("day5/file3.txt"); // Assuming file3.txt has an empty ranges section
        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_handle_empty_ids_section() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.countFreshId("day5/file4.txt"); // Assuming file4.txt has an empty IDs section
        assertThat(result).isEqualTo(0);
    }

    @Test
    void should_count_all_fresh_ids_from_file() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.countFreshId("day5/file5.txt"); // Assuming file4.txt has an empty IDs section
        assertThat(result).isEqualTo(21);
    }

    @Test
    void should_return_correct_available_ids() throws URISyntaxException, IOException {
        CounterFreshId counter = CounterFreshId.create();
        long result = counter.getAvailableIDs("day5/file1.txt");
        assertThat(result).isEqualTo(14);
    }
}
