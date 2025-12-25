package software.aoc.day3.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BatteryBank {

    private BatteryBank(){}

    public static BatteryBank create() { return new BatteryBank(); }

    public int sumAllMaxJoltageFromFile(String fileName) throws URISyntaxException, IOException {
        BufferedReader br = fileToBufferedReader(fileName);

        return getSumJoltagesFrom(br);

    }

    private int getSumJoltagesFrom(BufferedReader br) throws IOException {
        int total = 0;

        String line;
        while ((line = br.readLine()) != null) { total += getMaxJoltage(line); }

        return total;
    }

    private BufferedReader fileToBufferedReader(String fileName) throws URISyntaxException, IOException {
        Path filePath = stringToPath(fileName);
        return Files.newBufferedReader(filePath);
    }


    private Path stringToPath(String fileName) throws URISyntaxException {
        URL url = getClass().getResource("/" + fileName);
        if (url == null) {throw new IllegalArgumentException("Archivo no encontrado en resources: " + fileName);}
        return Paths.get(url.toURI());
    }

    public int getMaxJoltage(String bank){
        int maxJoltage = 0;
        for(int i = 0; i <= bank.length() - 2; i++){
            for (int j = i+1; j <= bank.length() - 1; j++){
                int joltage = getJoltage(bank, i, j);
                maxJoltage = Math.max(joltage, maxJoltage);
            }
        }
        return maxJoltage;
    }

    private int getJoltage(String bank, int index1, int index2) {
        return charsToInt(bank.charAt(index1), bank.charAt(index2));
    }

    private int charsToInt(char decena, char unidad) {
        return Integer.parseInt(String.valueOf(decena)) * 10 + Integer.parseInt(String.valueOf(unidad));
    }


}
