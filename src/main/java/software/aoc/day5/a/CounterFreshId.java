package software.aoc.day5.a;

import software.aoc.day5.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class CounterFreshId implements RangesIdParser{

    private final ResourceFileReader reader;

    private CounterFreshId() {
        this.reader = new ResourceFileReader();
    }

    public static CounterFreshId create() {
        return new CounterFreshId();
    }



    @Override
    public void saveRanges(BufferedReader br, RangesManager rangesManager) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if(line.isEmpty()) return;
            String[] parts = line.split("-");
            long a = Long.parseLong(parts[0]);
            long b = Long.parseLong(parts[1]);
            rangesManager.addRange(a, b);
        }
    }

    @Override
    public long countFreshId(String fileName) throws URISyntaxException, IOException {
        RangesManager rangesManager = new RangesManager();
        BufferedReader br = reader.read(fileName);
        saveRanges(br, rangesManager);
        return verifyIds(rangesManager, br);
    }

    private long verifyIds(RangesManager rangesManager, BufferedReader br) throws IOException {
        String line;
        long counter = 0;
        while ((line = br.readLine()) != null){
            if(rangesManager.isInside(Long.parseLong(line))) counter++;
        }
        return counter;
    }
}
