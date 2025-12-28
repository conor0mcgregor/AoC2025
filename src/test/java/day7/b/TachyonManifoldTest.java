package day7.b;


import org.junit.jupiter.api.Test;
import software.aoc.day7.b.TachyonManifold;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class TachyonManifoldTest {
    @Test
    void shouldCountRaysInExample() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countTimeLine("day7/file1.txt"))
                .isEqualTo(40);
    }

    @Test
    void shouldReturnZeroWhenNoSplitters() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countTimeLine("day7/file2.txt"))
                .isEqualTo(1);
    }

    @Test
    void shouldSplitOnceWhenBeamHitsSingleSplitter() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countTimeLine("day7/file3.txt"))
                .isEqualTo(2);

    }

    @Test
    void shouldCountMultipleIndependentSplits() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countTimeLine("day7/file4.txt"))
                .isEqualTo(8);
    }




}
