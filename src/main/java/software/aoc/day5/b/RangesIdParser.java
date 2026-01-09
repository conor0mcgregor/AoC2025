package software.aoc.day5.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public interface RangesIdParser {
    void saveRanges(BufferedReader br) throws IOException;
    long countFreshId(String fileName) throws URISyntaxException, IOException;
    long getAvailableIDs(String fileName) throws IOException, URISyntaxException;
}
