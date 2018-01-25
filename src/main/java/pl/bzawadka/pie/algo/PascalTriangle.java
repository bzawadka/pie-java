package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PascalTriangle {
    public static void main(String[] args) {
        int[][] triangle = pascalTriangle(7);
        printTriangle(triangle);
        prettyPrintTriangle(triangle);
        JUnitCore.main("pl.bzawadka.pie.algo.PascalTriangle");
    }

    /* indices                        values
          0        row: 0                1
         0 1       row: 1               1 1
        0 1 2      row: 2              1 2 1
       0 1 2 3     row: 3             1 3 3 1
      0 1 2 3 4    row: 4            1 4 6 4 1
    */
    @Test
    public void testSolution() {
        assertThat(triangleValue(0, 0), is(equalTo(1)));
        assertThat(triangleValue(2, 1), is(equalTo(2)));
        assertThat(triangleValue(4, 1), is(equalTo(4)));
        assertThat(triangleValue(4, 2), is(equalTo(6)));
        assertThat(triangleValue(6, 3), is(equalTo(20)));
        assertThat(triangleValue(7, 6), is(equalTo(7)));
    }

    private static int triangleValue(int row, int column) {
        if (row == 0 && column == 0)
            return 1;
        if (column == 0 || column == row)
            return 1;
        if (column < 0 || column > row)
            return 0;

        return triangleValue(row - 1, column - 1) + triangleValue(row - 1, column);
    }

    private static int[][] pascalTriangle(int depth) {
        int[][] triangle = new int[depth][depth];
        for (int row = 0; row < depth; row++) {
            for (int col = 0; col <= row; col++) {
                triangle[row][col] = triangleValue(row, col);
            }
        }
        return triangle;
    }

    private static void printTriangle(int[][] triangle) {
        System.out.println("I AM THE TRIANGLE");
        for (int row = 0; row < triangle.length; row++) {
            for (int col = 0; col <= row; col++) {
                System.out.print(triangle[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void prettyPrintTriangle(int[][] triangle) {
        System.out.println("I AM PRETTY TRIANGLE");
        for (int row = 0; row < triangle.length; row++) {
          /*
            first row  print 4      5 - 1 = 5 - 0 - 1 = 5 - row_index - 1
            second row print 3      5 - 2 = 5 - 1 - 1
            second last = print 1   5 - 4
            last row = print 0      5 - 0
          */
            for (int i = 0; i < triangle.length - row - 1; i++) {
                System.out.print(" ");
            }

            for (int col = 0; col <= row; col++) {
                System.out.print(triangle[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}


