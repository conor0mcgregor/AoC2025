package software.aoc.day5.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public interface RangesIdParser {
    void saveRanges(BufferedReader br, RangesManager rangesManager) throws IOException;
    long countFreshId(String fileName) throws URISyntaxException, IOException;
}
