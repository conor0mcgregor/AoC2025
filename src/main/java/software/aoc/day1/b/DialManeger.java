package software.aoc.day1.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DialManeger {
    private final Dial dial;
    private DialManeger(){
        this.dial = Dial.create();
    }

    public static DialManeger create() {
        return new DialManeger();
    }

    public DialManeger ordersOfSpins(String orders) {
        Arrays.stream(orders.split("\n")).forEach(this::spin);
        return this;
    }

    public DialManeger ordersOfSpinsInFile(String fileName) throws IOException, URISyntaxException {
        Path filePath = stringToPath(fileName);

        BufferedReader br = Files.newBufferedReader(filePath);
        applySpinsFrom(br);

        return this;
    }

    private void applySpinsFrom(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            spin(line);
        }
    }

    private Path stringToPath(String fileName) throws URISyntaxException {
        URL url = getClass().getResource("/" + fileName);
        if (url == null) {
            throw new IllegalArgumentException("Archivo no encontrado en resources: " + fileName);
        }

        return Paths.get(url.toURI());
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
