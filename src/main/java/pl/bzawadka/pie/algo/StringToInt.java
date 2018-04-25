package pl.bzawadka.pie.algo;

public class StringToInt {

    public long strToNumber(String str) {
        validate(str);
        long number = 0;
        for (char c : str.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            number = number * 10 + numericValue;
        }
        return number;
    }

    private void validate(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Empty String cannot be converted into a number");
        }

        if (str.subSequence(0, 1).equals("-")) {
            throw new IllegalArgumentException("Negative numbers are not supported");
        }
    }
}
