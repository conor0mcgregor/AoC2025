package software.aoc.day5.b;

public record Range(long a, long b){
    public boolean isInside(long num) {
        return a <= num && b >= num;
    }
    public long getSize(){ return b - a + 1; }
}
