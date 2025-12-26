package software.aoc.day3.a;

public class MaxJoltageParser implements BatteryParser {
    private final int targetDigits;

    public MaxJoltageParser(int targetDigits) {
        if (targetDigits <= 0) {
            throw new IllegalArgumentException("Target digits must be positive");
        }
        this.targetDigits = targetDigits;
    }

    @Override
    public int parse(String batteryBank) {
        if (batteryBank == null || batteryBank.isEmpty()) {
            return 0;
        }

        return extractMaxDigits(batteryBank);
    }

    private int extractMaxDigits(String bank) {
        int maxJoltage = 0;
        for(int i = 0; i <= bank.length() - 2; i++){
            maxJoltage = getMaxDigit(bank, i, maxJoltage);
        }
        return maxJoltage;
    }

    private int getMaxDigit(String bank, int i, int maxJoltage) {
        for (int j = i +1; j <= bank.length() - 1; j++){
            int joltage = getJoltage(bank, i, j);
            maxJoltage = Math.max(joltage, maxJoltage);
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