package software.aoc.day1.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class DialManeger {
    private Dial dial;
    private int password;
    private DialManeger(){
        this.dial = Dial.create();
        this.password = 0;
    }

    public static DialManeger create() {
        return new DialManeger();
    }

    public int ordersOfSpins(String orders) {
        Arrays.stream(orders.split("\n")).forEach(this::spin);
        return getPosition();
    }

    public int ordersOfSpinsInFile(String fileName) throws IOException, URISyntaxException {
        Path filePath = stringToPath(fileName);

        try (BufferedReader br = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = br.readLine()) != null) {
                spin(line);
            }
        }

        return getPosition();
    }

    private Path stringToPath(String fileName) throws URISyntaxException {
        URL url = getClass().getResource("/" + fileName);
        if (url == null) {
            throw new IllegalArgumentException("Archivo no encontrado en resources: " + fileName);
        }

        return Paths.get(url.toURI());
    }

    public int spin(String turn) {
        int rotation = Integer.parseInt(turn.substring(1));
        dial.rotate(isPlusRotation(turn)? rotation : rotation * -1);
        checkPassword();
        return getPosition();
    }

    private void checkPassword() {
        password = getPosition() == 0 ? password+1 : password;
    }

    public int getPosition(){
        return dial.getPosition();
    }

    private boolean isPlusRotation(String turn) {
        return turn.charAt(0) == 'R';
    }

    public int getPassword() {
        return password;
    }
}
