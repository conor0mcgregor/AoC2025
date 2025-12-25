package software.aoc.day2.a;

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
                .filter(this::isValidId)
                .sum();
    }

    public boolean isValidId(long id) {
        String strId = String.valueOf(id);
        return strId.length() % 2 == 0 && isMirror(strId);
    }

    private boolean isMirror(String strId) {
        int length = strId.length();
        return strId.substring(0, length /2).equals(strId.substring(length /2));
    }

}
