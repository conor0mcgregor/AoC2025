package software.aoc.day1.a;

import software.aoc.FileReader;
import software.aoc.ResourceFileReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class DialManeger {
    private final Dial dial;
    private final FileReader reader;

    private DialManeger(FileReader reader){
        this.dial = Dial.create();
        this.reader = reader;
    }

    public static DialManeger create() {
        return new DialManeger(new ResourceFileReader());
    }

    public int ordersOfSpins(String orders) {
        Arrays.stream(orders.split("\n")).forEach(this::spin);
        return getPosition();
    }

    public int ordersOfSpinsInFile(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = reader.read(fileName);
        applySpinsFrom(br);

        return getPosition();
    }

    private void applySpinsFrom(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            spin(line);
        }
    }

    public int spin(String turn) {
        int rotation = Integer.parseInt(turn.substring(1));
        dial.rotate(isPlusRotation(turn)? rotation : rotation * -1);
        return getPosition();
    }

    public int getPosition(){
        return dial.getPosition();
    }

    private boolean isPlusRotation(String turn) {
        return turn.charAt(0) == 'R';
    }

    public int getPassword() {
        return dial.getPassword();
    }
}
