package pl.bzawadka.pie.algo;

public class StringToInt {

    public long strToNumber(String str) {
        validateInput(str);
        long number = 0;
        for (char c : str.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            validateNumericValue(numericValue);
            number = number * 10 + numericValue;
        }
        return number;
    }

    private void validateInput(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Empty String cannot be converted into a number");
        }

        if (str.subSequence(0, 1).equals("-")) {
            throw new IllegalArgumentException("Negative numbers are not supported");
        }
    }

    private void validateNumericValue(int numericValue) {
        if (numericValue < Character.getNumericValue('0') || numericValue > Character.getNumericValue('9')) {
            throw new IllegalArgumentException("A letter cannot be converted into a digit");
        }
    }

}
