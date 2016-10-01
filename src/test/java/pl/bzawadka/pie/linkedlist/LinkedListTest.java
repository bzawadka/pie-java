package pl.bzawadka.pie.linkedlist;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LinkedListTest {
    private LinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new LinkedList<Integer>();
    }

    @Test
    public void testDelete_onEmptyList_returnsFalse() throws Exception {
        assertThat(list.delete(element(1)), is(false));
    }

    @Test
    public void testDelete_onOneElementList_works() throws Exception {
        list = new LinkedList<Integer>(0);
        assertThat(list.toString(), is("[0]"));
        assertThat(list.delete(element(1)), is(false));
        assertThat(list.toString(), is("[0]"));
        assertThat(list.delete(element(0)), is(true));
        assertThat(list.toString(), is("[]"));
    }

    @Test
    public void testDelete_twoElementList_works() {
    }

    @Test
    @Ignore("unfinished")
    public void testInsertAfter() throws Exception {
        list = new LinkedList<Integer>();
        assertThat(list.insertAfter(null, 1), is(true));
        assertThat(list.toString(), is("[1]"));

        assertThat(list.insertAfter(element(1), 2), is(true));
        assertThat(list.toString(), is("[1,2]"));

        assertThat(list.insertAfter(element(2), 3), is(true));
        assertThat(list.toString(), is("[1,2,3]"));

        assertThat(list.insertAfter(element(3), 4), is(true));
        assertThat(list.toString(), is("[1,2,3,4]"));

  //      assertThat(list.insertAfter(element(9), 3), is(false));
    //    assertThat(list.toString(), is("[0,1,2]"));
    }

    public static Element<Integer> element(int value) {
        return new Element<Integer>(value);
    }
}