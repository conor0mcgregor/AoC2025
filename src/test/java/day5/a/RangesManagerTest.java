package day5.a;
import org.junit.jupiter.api.Test;
import software.aoc.day5.a.RangesManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RangesManagerTest {
    @Test
    void should_add_and_sort_ranges() {
        RangesManager manager = new RangesManager();
        manager.addRange(10, 20);
        manager.addRange(1, 5);
        manager.addRange(30, 40);

        List<RangesManager.Range> expected = List.of(
                new RangesManager.Range(1, 5),
                new RangesManager.Range(10, 20),
                new RangesManager.Range(30, 40)
        );

        assertThat(manager.getRanges()).isEqualTo(expected);
    }

    @Test
    void should_unify_overlapping_ranges() {
        RangesManager manager = new RangesManager();
        manager.addRange(1, 10);
        manager.addRange(5, 15);

        List<RangesManager.Range> expected = List.of(
                new RangesManager.Range(1, 15)
        );

        assertThat(manager.getRanges()).isEqualTo(expected);
    }

    @Test
    void should_unify_multiple_overlapping_ranges() {
        RangesManager manager = new RangesManager();
        manager.addRange(1, 5);
        manager.addRange(3, 8);
        manager.addRange(6, 12);

        List<RangesManager.Range> expected = List.of(
                new RangesManager.Range(1, 12)
        );

        assertThat(manager.getRanges()).isEqualTo(expected);
    }

    @Test
    void should_not_unify_non_overlapping_ranges() {
        RangesManager manager = new RangesManager();
        manager.addRange(1, 5);
        manager.addRange(6, 10);

        List<RangesManager.Range> expected = List.of(
                new RangesManager.Range(1, 5),
                new RangesManager.Range(6, 10)
        );

        assertThat(manager.getRanges()).isEqualTo(expected);
    }

    @Test
    void shoul_unify_multiple_ranges() {
        RangesManager manager = new RangesManager();
        manager.addRange(1, 5);
        manager.addRange(3, 8);
        manager.addRange(6, 10);
        manager.addRange(9, 23);
        manager.addRange(2, 5);
        manager.addRange(50, 100);
        manager.addRange(60, 65);
        manager.addRange(20, 52);
        List<RangesManager.Range> expected = List.of(
                new RangesManager.Range(1, 100)
        );
        assertThat(manager.getRanges()).isEqualTo(expected);

    }


}

