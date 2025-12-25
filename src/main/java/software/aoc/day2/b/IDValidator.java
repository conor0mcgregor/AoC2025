package software.aoc.day2.b;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.LongStream;

public class IDValidator {

    private IDValidator() {}

    public static IDValidator create() {return new IDValidator();}

    public long sumInvalidIdsFromFile(String fileName) throws URISyntaxException, IOException {
        String content = Files.readString(stringToPath(fileName));
        return Arrays.stream(content.split(","))
                .mapToLong(this::sumInvalidIdInStrRange)
                .sum();
    }

    private long sumInvalidIdInStrRange(String range) {
        String[] intervalos = range.split("-");
        return sumInvalidIds(stringToLong(intervalos[0]), stringToLong(intervalos[1]));
    }

    private Long stringToLong(String str) {
        return Long.parseLong(str);
    }

    private Path stringToPath(String fileName) throws URISyntaxException {
        URL url = getClass().getResource("/" + fileName);
        if (url == null) {throw new IllegalArgumentException("Archivo no encontrado en resources: " + fileName);}
        return Paths.get(url.toURI());
    }


    public long sumInvalidIds(long start, long end) {
        return LongStream.rangeClosed(start, end)
                .filter(this::isInvalidId)
                .sum();
    }

    public boolean isInvalidId(long id) {
        return conteinPatron(id);
    }

    private boolean conteinPatron(long id) {
        String strId = Long.toString(id);
        int cifras = strId.length();

        return findPatron(cifras, strId);
    }

    private boolean findPatron(int cifras, String strId) {
        for (int patternLength = 1; patternLength <= cifras / 2; patternLength++) {
            if (cifras % patternLength != 0) continue;

            if (existPatronIn(strId, cifras, patternLength)) return true;
        }

        return false;
    }

    private boolean existPatronIn(String strId, int cifras, int patternLength) {
        String pattern = strId.substring(0, patternLength);
        int repetitions = cifras / patternLength;

        return pattern.repeat(repetitions).equals(strId);
    }


}
