package software.aoc.day2.b;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class GiftShop {
    private final FileReader reader;

    private GiftShop(FileReader reader) {
        this.reader = reader;
    }

    public static GiftShop create() {
        return new GiftShop(new ResourceFileReader());
    }


    public long sumInvalidIdsFromFile(String fileName) throws URISyntaxException, IOException {
        String ranges = reader.read(fileName).readLine();
        return sumAllInvalidIds(ranges);
    }

    private long sumAllInvalidIds(String ranges) {
        IDVerifier idValidator = IDValidator.create();
        return Arrays.stream(ranges.split(","))
                .mapToLong(idValidator::sumInvalidIdInStrRange)
                .sum();
    }


}
