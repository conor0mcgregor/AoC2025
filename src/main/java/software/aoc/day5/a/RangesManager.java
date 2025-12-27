package software.aoc.day5.a;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class RangesManager {
    private final List<Range> ranges;

    public RangesManager() {
        this.ranges = new ArrayList<>();
    }

    public void addRange(long a, long b){
        Range range = new Range(a, b);
        ranges.add(range);
        if(ranges.size() > 1) upDateRanges();
    }

    private void upDateRanges() {
        ranges.sort(Comparator.comparing(Range::a));
        for(int i =0; i < ranges.size() - 1; i++){
            examine(ranges.get(i), ranges.get(i+1), i);
        }
    }

    private void examine(Range range1, Range range2, int index) {
        if (isUnifiable(range1, range2)) {
            unify(range1, range2, index);
        }
    }

    private boolean isUnifiable(Range range1, Range range2) {
        return range1.b >= range2.a;
    }

    private void unify(Range range1, Range range2, int index) {
        Range range = new Range(min(range1.a, range2.a), max(range1.b, range2.b));
        ranges.set(index, range);
        ranges.remove(index+1);
        upDateRanges();
    }

    public boolean isInside(long num){
        for (Range range : ranges) { if (range.isInside(num)) return true; }
        return false;
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public record Range(long a, long b){
        public boolean isInside(long num) {
            return a <= num && b >= num;
        }
    }
}
