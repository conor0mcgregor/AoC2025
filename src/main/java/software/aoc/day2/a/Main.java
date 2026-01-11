package software.aoc.day2.a;


import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    static void main() throws URISyntaxException, IOException {
        GiftShop giftShop = GiftShop.create();
        long sumIvalidId = giftShop.sumInvalidIdsFromFile("day2/input_day2.txt");
        System.out.println(sumIvalidId);
    }
}
