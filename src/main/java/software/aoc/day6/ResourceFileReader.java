package software.aoc.day6;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceFileReader implements FileReader {
    
    @Override
    public BufferedReader read(String fileName) throws URISyntaxException, IOException {
        Path filePath = resolveResourcePath(fileName);
        return Files.newBufferedReader(filePath);
    }

    private Path resolveResourcePath(String fileName) throws URISyntaxException {
        URL url = getClass().getResource("/" + fileName);
        
        if (url == null) {
            throw new IllegalArgumentException("File not found in resources: " + fileName);
        }
        
        return Paths.get(url.toURI());
    }
}