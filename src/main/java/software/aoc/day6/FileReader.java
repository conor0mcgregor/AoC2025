package software.aoc.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public interface FileReader {
    BufferedReader read(String fileName) throws URISyntaxException, IOException;
}
