package software.aoc.day2.a;

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
        String content = reader.read(fileName).readLine();
        return sumAllInvalidIds(content);
    }

    private long sumAllInvalidIds(String content) {
        IDVerifier idValidator = IDValidator.create();
        return Arrays.stream(content.split(","))
                .mapToLong(idValidator::sumInvalidIdInStrRange)
                .sum();
    }


}
