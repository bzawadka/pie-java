package pl.bzawadka.pie.recursion;

public class PermutationsOfString {
    private boolean[] used;
    private StringBuilder out = new StringBuilder();
    private final String input;

    public PermutationsOfString(String input) {
        this.input = input;
        used = new boolean[input.length()];
    }

    public void permute() {
        if (input.length() == out.length()) {
            System.out.println(out);
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            if (used[i])
                continue;
            out.append(input.charAt(i));
            used[i] = true;
            permute();
            used[i] = false;
            out.setLength(out.length() - 1);
        }
    }

    public static void main(String[] args) {
        new PermutationsOfString("ab").permute();
        System.out.println("***");
        new PermutationsOfString("hat").permute();
        System.out.println("***");
        new PermutationsOfString("bart").permute();
    }
}
