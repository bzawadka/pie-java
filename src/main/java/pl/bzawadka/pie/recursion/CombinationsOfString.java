package pl.bzawadka.pie.recursion;

public class CombinationsOfString {

    private StringBuilder out = new StringBuilder();
    private final String input;

    public CombinationsOfString(String input) {
        this.input = input;
    }

    public void combine() {
        combine(0);
    }

    private void combine(int start) {
        for (int i = start; i < input.length(); i++) {
            out.append(input.charAt(i));
            System.out.println(out);
            if (i < input.length()) {
                combine(i + 1);
            }
            out.setLength(out.length() - 1);
        }
    }

    public static void main(String[] args) {
        new CombinationsOfString("wxyz").combine();
    }
}
