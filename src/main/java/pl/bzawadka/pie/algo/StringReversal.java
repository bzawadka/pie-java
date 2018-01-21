package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class StringReversal {

    public static void main(String[] args) {
        JUnitCore.main("pl.bzawadka.pie.algo.StringReversal");
    }

    @Test
    public void testReverseString() {
        assertThat(reverseString("a"), is(equalTo("a")));
        assertThat(reverseString("ab"), is(equalTo("ba")));
        assertThat(reverseString("abcd"), is(equalTo("dcba")));
        assertThat(reverseString("qwerty uio"), is(equalTo("oiu ytrewq")));

        assertThat(reverseStringManually("a"), is(equalTo("a")));
        assertThat(reverseStringManually("ab"), is(equalTo("ba")));
        assertThat(reverseStringManually("abcd"), is(equalTo("dcba")));
        assertThat(reverseStringManually("qwerty uio"), is(equalTo("oiu ytrewq")));
    }

    public static String reverseString(String src) {
        return new StringBuilder(src).reverse().toString();
    }

    public static String reverseStringManually(String src) {
        char[] arr = src.toCharArray();
        int size = arr.length;
        for (int i = 0; i < size / 2; i++) {
            char tmp = arr[size - 1 - i];
            arr[size - 1 - i] = arr[i];
            arr[i] = tmp;
        }
        return new String(arr);
    }
}
