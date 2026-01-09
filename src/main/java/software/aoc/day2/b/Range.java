package software.aoc.day2.b;

public record Range(long a, long b) {
    public static Range with(String string){
        String[] interval = string.split("-");
        return new Range(stringToLong(interval[0]), stringToLong(interval[1]));
    }
    private static Long stringToLong(String str) {
        return Long.parseLong(str);
    }
}
