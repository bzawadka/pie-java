package pl.bzawadka.pie.algo;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AverageTest {
    private Average underTest;

    @Before
    public void setUp() {
        underTest = new Average();
        underTest.addNumber(10);
        underTest.addNumber(20);
        underTest.addNumber(30);
        underTest.addNumber(50);
    }

    @Test
    public void testAverage() {
        assertThat(underTest.average()).isEqualTo(27.5);
    }

    @Test
    public void testAverage2() {
        assertThat(underTest.average2()).isEqualTo(27.5);
    }

    @Test
    public void testAverage3() {
        assertThat(underTest.average3()).isEqualTo(27.5);
    }

    @Test
    public void testAverage4() {
        assertThat(underTest.average4()).isEqualTo(27.5);
    }

    @Test
    public void testAverage5() {
        assertThat(underTest.average5()).isEqualTo(27.5);
    }

    @Test
    public void testAverage6() {
        underTest.threadSafeAddNumber(100);
        assertThat(underTest.threadSaveAverage()).isEqualTo(42);
    }
}
