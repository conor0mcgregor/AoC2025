package software.aoc.day2.a;

import java.util.stream.LongStream;

public class IDValidator implements IDVerifier{

    private IDValidator(){}

    public static IDValidator create() {return new IDValidator();}

    @Override
    public long sumInvalidIdInStrRange(String rangeStr) {
        Range range = Range.with(rangeStr);
        return sumInvalidIds(range.a(), range.b());
    }

    private long sumInvalidIds(long start, long end) {
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
