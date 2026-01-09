package software.aoc.day2.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.stream.LongStream;

public class IDValidator {
    private FileReader reader;

    private IDValidator(FileReader reader) {
        this.reader = reader;
    }

    public static IDValidator create() {return new IDValidator(new ResourceFileReader());}

    public long sumInvalidIdsFromFile(String fileName) throws URISyntaxException, IOException {
        String content = reader.read(fileName).readLine();
        return sumAllInvalidIds(content);
    }

    private long sumAllInvalidIds(String content) {
        return Arrays.stream(content.split(","))
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
        String strId = String.valueOf(id);
        return strId.length() % 2 == 0 && isMirror(strId);
    }

    private boolean isMirror(String strId) {
        int length = strId.length();
        return strId.substring(0, length /2).equals(strId.substring(length /2));
    }

}
