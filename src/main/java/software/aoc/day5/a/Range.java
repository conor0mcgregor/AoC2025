package software.aoc.day5.a;

public record Range(long a, long b){
    public boolean isInside(long num) {
        return a <= num && b >= num;
    }
}
