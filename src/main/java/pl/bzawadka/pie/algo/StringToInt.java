package pl.bzawadka.pie.algo;

public class StringToInt {

    public int strToInt(String str) {
        int result = 0;
        for (char c : str.toCharArray()) {
            int numericValue = Character.getNumericValue(c);
            result = result * 10 + numericValue;
        }
        return result;
    }
}
