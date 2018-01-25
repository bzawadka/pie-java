package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SecondBiggest {

    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    private static int findSecondBiggest(int[] array) {
        int biggest = Integer.MIN_VALUE, secondBiggest = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];

            if (currentElement > biggest) {
                secondBiggest = biggest;
                biggest = currentElement;

            } else if (currentElement > secondBiggest && currentElement != biggest) {
                secondBiggest = currentElement;
            }
        }
        return secondBiggest;
    }

    @Test
    public void testSecondSmallestElement() {
        int[] array = {7, 4, 8, 4, 6, 2, 1, 7};
        assertThat(findSecondBiggest(array), is(equalTo(7)));
        array = new int[]{245, 4, 5, 45, 1, 1123123, 3, 9, 99, 2};
        assertThat(findSecondBiggest(array), is(equalTo(245)));
        array = new int[]{100, 200, 300, 400, 500, 600};
        assertThat(findSecondBiggest(array), is(equalTo(500)));
        array = new int[]{7, 7, 7, 8, 7, 8, 7, 7};
        assertThat(findSecondBiggest(array), is(equalTo(7)));
    }
}
