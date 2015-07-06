package pl.bzawadka.pie.linkedlist;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class StackATest {

    private StackA<String> stack;

    @Before
    public void setUp() {
        stack = new StackA<String>();
    }

    @Test
    public void popOnEmptyStackReturnsNull() {
        assertThat(stack.pop(), is(nullValue()));
    }

    @Test
    public void pushAndPopSingleElementWorks() {
        stack.push("A");
        assertThat(stack.pop(), is("A"));
        assertThat(stack.pop(), is(nullValue()));
    }

    @Test
    public void pushAndPopMultipleElementsReturnsThemInOrder() {
        stack.push("A");
        stack.push("B");
        stack.push("C");
        assertThat(stack.pop(), is("C"));
        assertThat(stack.pop(), is("B"));
        assertThat(stack.pop(), is("A"));
        assertThat(stack.pop(), is(nullValue()));
        assertThat(stack.pop(), is(nullValue()));
        stack.push("D");
        stack.push("E");
        assertThat(stack.pop(), is("E"));
        stack.push("F");
        assertThat(stack.pop(), is("F"));
        assertThat(stack.pop(), is("D"));
    }
}