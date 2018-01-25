package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class CharacterCount {
    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    @Test
    public void testSolution() {
        assertThat(transformLetters("aaabbbbcc"), is(equalTo("a3b4c2")));
        assertThat(transformLetters("qqwwee"), is(equalTo("q2w2e2")));
        assertThat(transformLetters("qwe"), is(equalTo("q1w1e1")));
        assertThat(transformLetters("x"), is(equalTo("x1")));
    }

    private static String transformLetters(String src) {
        StringBuffer result = new StringBuffer();
        char previousChar = Character.MIN_VALUE;
        int counter = Integer.MIN_VALUE;

        for (int i = 0; i < src.length(); i++) {
            char currentChar = src.charAt(i);

            if (previousChar != currentChar) {
                if (counter >= 0) {
                    result.append(previousChar);
                    result.append(counter + 1);
                }

                previousChar = currentChar;
                counter = 0;
            } else /* previousChar == currentChar */ {
                counter++;
            }
        }
        result.append(previousChar);
        result.append(counter + 1);

        return result.toString();
    }
}


