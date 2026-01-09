package software.aoc.day2.b;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.LongStream;

public class IDValidator {
    private final FileReader reader;
    private IDValidator(FileReader reader) {this.reader = reader;}

    public static IDValidator create() {return new IDValidator(new ResourceFileReader());}

    public long sumInvalidIdsFromFile(String fileName) throws URISyntaxException, IOException {
        String ranges = reader.read(fileName).readLine();
        return sumAllInvalidIds(ranges);
    }

    private long sumAllInvalidIds(String ranges) {
        return Arrays.stream(ranges.split(","))
                .mapToLong(this::sumInvalidIdInStrRange)
                .sum();
    }

    private long sumInvalidIdInStrRange(String rangeStr) {
        Range range = Range.with(rangeStr);
        return sumInvalidIds(range.a(), range.b());
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
