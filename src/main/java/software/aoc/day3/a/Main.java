package software.aoc.day3.a;



import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws IOException, URISyntaxException {
        BatteryBank batteryBank = BatteryBank.create();
        int sumAllJoltages = batteryBank.sumAllMaxJoltageFromFile("day3/a/input_day3.txt");
        System.out.println(sumAllJoltages);
    }

}
