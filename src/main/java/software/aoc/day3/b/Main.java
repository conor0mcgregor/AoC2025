package software.aoc.day3.b;



import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws IOException, URISyntaxException {
        BatteryBank batteryBank = BatteryBank.create();
        long sumAllJoltages = batteryBank.sumAllMaxJoltageFromFile("day3/a/input_day3.txt");
        System.out.println(sumAllJoltages);
    }

}
