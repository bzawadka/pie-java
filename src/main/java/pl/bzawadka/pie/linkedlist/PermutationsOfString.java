package pl.bzawadka.pie.linkedlist;

public class PermutationsOfString {
    private boolean[] used;
    private StringBuilder out = new StringBuilder();
    private final String in;

    public PermutationsOfString(String str) {
        in = str;
        used = new boolean[str.length()];
    }

    public void permute() {
        if (in.length() == out.length()) {
            System.out.println(out);
            return;
        }
        for (int i = 0; i < in.length(); i++) {
            if (used[i])
                continue;
            out.append(in.charAt(i));
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
