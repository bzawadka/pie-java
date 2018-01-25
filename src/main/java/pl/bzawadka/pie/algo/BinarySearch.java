package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class BinarySearch {

    public static void main(String[] args) {
        // https://coderpad.io/sandboxs
        JUnitCore.main("pl.bzawadka.pie.algo.BinarySearch");
    }

    // https://www.topcoder.com/community/data-science/data-science-tutorials/binary-search/
    public static int binarySearch(int[] array, int target) {
        int mid;
        int low = 0, high = array.length - 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (array[mid] == target)
                return mid;
            if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    @Test
    public void testbBinarySearch() {
        int[] array = {2, 4, 13, 55, 75, 133};
        assertThat(binarySearch(array, 2), is(equalTo(0)));
        assertThat(binarySearch(array, 13), is(equalTo(2)));
        assertThat(binarySearch(array, 75), is(equalTo(4)));
        assertThat(binarySearch(array, 133), is(equalTo(5)));
    }
}
