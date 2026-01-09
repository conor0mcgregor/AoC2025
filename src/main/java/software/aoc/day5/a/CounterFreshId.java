package software.aoc.day5.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class CounterFreshId implements RangesIdParser {

    private final FileReader reader;
    private final RangesManager rangesManager;

    private CounterFreshId() {
        this.reader = new ResourceFileReader();
        this.rangesManager = new RangesManager();
    }

    public static CounterFreshId create() {
        return new CounterFreshId();
    }

    @Override
    public void saveRanges(BufferedReader br) {
        br.lines()
                .takeWhile(line -> !line.isEmpty())
                .forEach(this::saveRange);
    }

    private void saveRange(String line) {
        String[] parts = line.split("-");
        long a = Long.parseLong(parts[0]);
        long b = Long.parseLong(parts[1]);
        rangesManager.addRange(a, b);
    }

    @Override
    public long countFreshId(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        saveRanges(br);
        return verifyIds(br);
    }

    private long verifyIds(BufferedReader br) {
        return br.lines()
                .mapToLong(Long::parseLong)
                .filter(rangesManager::isInside)
                .count();
    }
}
