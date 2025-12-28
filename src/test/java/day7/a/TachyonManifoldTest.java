package day7.a;


import org.junit.jupiter.api.Test;
import software.aoc.day7.a.TachyonManifold;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class TachyonManifoldTest {
    @Test
    void shouldCountRaysInExample() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countRays("day7/file1.txt"))
                .isEqualTo(21);
    }

    @Test
    void shouldReturnZeroWhenNoSplitters() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countRays("day7/file2.txt"))
                .isEqualTo(0);
    }

    @Test
    void shouldSplitOnceWhenBeamHitsSingleSplitter() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countRays("day7/file3.txt"))
                .isEqualTo(1);

    }

    @Test
    void shouldCountMultipleIndependentSplits() throws URISyntaxException, IOException {
        TachyonManifold tachyonManifold = TachyonManifold.create();
        assertThat(tachyonManifold.countRays("day7/file4.txt"))
                .isEqualTo(6);
    }




}
