package pl.bzawadka.pie.algo;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringToIntTest {

    private StringToInt underTest;

    @Before
    public void setUp() {
        underTest = new StringToInt();
    }

    @Test
    public void stringShouldBeConvertedToANumber() {
        assertThat(underTest.strToNumber("0")).isEqualTo(0);
        assertThat(underTest.strToNumber("123")).isEqualTo(123);
        assertThat(underTest.strToNumber("2147483647")).isEqualTo(2147483647);
        assertThat(underTest.strToNumber("2147483648")).isEqualTo(2147483648L);
        assertThat(underTest.strToNumber("2147483648123123")).isEqualTo(2147483648123123L);
    }
}