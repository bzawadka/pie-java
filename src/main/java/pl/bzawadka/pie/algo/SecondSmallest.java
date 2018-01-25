package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class SecondSmallest {

    public static void main(String[] args) {
        JUnitCore.main("Solution");
    }

    private static int findSecondSmallest(int[] array) {
        int smallest = Integer.MAX_VALUE, secondSmallest = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {
            int currentElement = array[i];

            if (currentElement < smallest) {
                secondSmallest = smallest;
                smallest = currentElement;

                /* If currentElement element is in between the smallest and second then update second */
            } else if (currentElement < secondSmallest && currentElement != smallest) {
                secondSmallest = currentElement;
            }
        }
        return secondSmallest;
    }

    /*
        {7, 4, 8, 4, 6, 2, 1, 7}
        current     smallest        secondSmallest
                    MAX_VALUE       MAX_VALUE
        7           7               MAX_VALUE
        4           4               7
        8           4               7
        4           4               7
        6           4               6
        2           2               4
        1           1               2
        7           1               2

        {7, 7, 7, 8, 7, 8}
        current     smallest        secondsmallest
                    MAX_VALUE       MAX_VALUE
        7           7               MAX_VALUE
        7           7               MAX_VALUE
        7           7               MAX_VALUE
        8           7               8
        7           7               8
    */
    @Test
    public void testSecondSmallestElement() {
        int[] array = {7, 4, 8, 4, 6, 2, 1, 7};
        assertThat(findSecondSmallest(array), is(equalTo(2)));

        array = new int[]{245, 4, 5, 45, 1, 1123123, 3, 9, 99, 2};
        assertThat(findSecondSmallest(array), is(equalTo(2)));

        array = new int[]{100, 200, 300, 400, 500, 600};
        assertThat(findSecondSmallest(array), is(equalTo(200)));

        array = new int[]{7, 7, 7, 8, 7, 8, 7, 7};
        assertThat(findSecondSmallest(array), is(equalTo(8)));
    }

}


