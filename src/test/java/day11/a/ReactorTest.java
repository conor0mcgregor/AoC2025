package day11.a;

import org.junit.jupiter.api.Test;
import software.aoc.day11.a.Reactor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReactorTest {

    @Test
    void should_find_the_correct_number_of_paths() throws URISyntaxException, IOException {
        Reactor reactor = Reactor.create();
        long result = reactor.countPaths("day11/a/file1.txt");
        assertThat(result).isEqualTo(5);
    }

    @Test
    void should_return_only_one_paths(){
        Reactor reactor = Reactor.create();
        long result = reactor.countPaths(List.of(
                "you: out"
        ));
        assertThat(result).isEqualTo(1);
    }
    @Test
    void should_return_zero_paths(){
        Reactor reactor = Reactor.create();
        long result = reactor.countPaths(List.of(
                "you: aaa"
        ));
        assertThat(result).isEqualTo(0);
    }
    @Test
    void should_return_two_paths(){
        Reactor reactor = Reactor.create();
        long result = reactor.countPaths(List.of(
                "you: aaa out",
                "aaa: out"
        ));
        assertThat(result).isEqualTo(2);
    }
}
