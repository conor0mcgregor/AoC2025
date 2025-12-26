package software.aoc.day3.b;

public class MaxJoltageParser implements BatteryParser {
    private final int targetDigits;

    public MaxJoltageParser(int targetDigits) {
        if (targetDigits <= 0) {
            throw new IllegalArgumentException("Target digits must be positive");
        }
        this.targetDigits = targetDigits;
    }

    @Override
    public long parse(String batteryBank) {
        if (batteryBank == null || batteryBank.isEmpty()) {
            return 0;
        }

        int[] maxDigits = extractMaxDigits(batteryBank);
        return convertToLong(maxDigits);
    }

    private int[] extractMaxDigits(String bank) {
        int[] result = new int[targetDigits];
        int startIndex = 0;

        for (int position = 0; position < targetDigits; position++) {
            int digitsNeeded = targetDigits - position;
            int searchLimit = bank.length() - digitsNeeded + 1;
            
            MaxDigitResult maxResult = findMaxDigitInRange(bank, startIndex, searchLimit);
            
            result[position] = maxResult.value;
            startIndex = maxResult.index + 1;
        }

        return result;
    }

    private MaxDigitResult findMaxDigitInRange(String bank, int startIndex, int endIndex) {
        int maxValue = 0;
        int maxIndex = startIndex;

        for (int i = startIndex; i < endIndex; i++) {
            int digit = Character.getNumericValue(bank.charAt(i));
            
            if (digit > maxValue) {
                maxValue = digit;
                maxIndex = i;
            }
        }

        return new MaxDigitResult(maxValue, maxIndex);
    }

    private long convertToLong(int[] digits) {
        long result = 0;
        
        for (int digit : digits) {
            result = result * 10 + digit;
        }
        
        return result;
    }

    private static class MaxDigitResult {
        final int value;
        final int index;

        MaxDigitResult(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}