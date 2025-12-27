package software.aoc.day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public interface FileReader {
    BufferedReader read(String fileName) throws URISyntaxException, IOException;
}
