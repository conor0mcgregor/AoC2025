package software.aoc.day1.b;

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

    public DialManeger ordersOfSpins(String orders) {
        Arrays.stream(orders.split("\n")).forEach(this::spin);
        return this;
    }

    public DialManeger ordersOfSpinsInFile(String fileName) throws IOException, URISyntaxException {
        BufferedReader br = reader.read(fileName);
        applySpinsFrom(br);
        return this;
    }

    private void applySpinsFrom(BufferedReader br) {
        br.lines().forEach(this::spin);
    }

    public DialManeger spin(String turn) {
        int rotation = Integer.parseInt(turn.substring(1));
        dial.rotate(isPlusRotation(turn)? rotation : rotation * -1);
        return this;
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
