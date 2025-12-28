package day8.a;

import org.junit.jupiter.api.Test;
import software.aoc.day7.b.TachyonManifold;
import software.aoc.day8.a.PlaygroundCircuits;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaygroundCircuitsTest {
    @Test
    void shouldCountRaysInExample() throws URISyntaxException, IOException {
        PlaygroundCircuits playgroundCircuits = PlaygroundCircuits.create();
        assertThat(playgroundCircuits.parserPoints("day8/file1.txt"))
                .isEqualTo(40);
    }
}
