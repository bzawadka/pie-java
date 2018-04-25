package pl.bzawadka.pie.algo;

public class StringToInt {

    public long strToNumber(String str) {
        long number = 0;
        for (char c : str.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            number = number * 10 + numericValue;
        }
        return number;
    }
}
