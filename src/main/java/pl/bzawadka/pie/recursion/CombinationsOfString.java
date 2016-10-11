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

    public void combineNoIf() {
        combineNoIf(0);
    }

    private void combineNoIf(int start) {
        for (int i = start; i < input.length() - 1; i++) {
            out.append(input.charAt(i));
            System.out.println(out);
            combineNoIf(i + 1);
            out.setLength(out.length() - 1);
        }

        out.append(input.charAt(input.length() - 1));
        System.out.println(out);
        out.setLength(out.length() - 1);
    }

    public static void main(String[] args) {
        new CombinationsOfString("wxyz").combine();
        System.out.println("---------------------------");
        new CombinationsOfString("wxyz").combineNoIf();
    }
}
