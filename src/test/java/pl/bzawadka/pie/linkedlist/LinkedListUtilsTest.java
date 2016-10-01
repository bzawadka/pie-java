package pl.bzawadka.pie.linkedlist;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

public class LinkedListUtilsTest {

    @Test
    public void testFind_valueNotFound_returnsNull() {
        Element<Integer> head = betterCreateList(3, 6);
        Element<Integer> element = LinkedListUtils.find(head, 99);
        assertThat(element, is(nullValue()));
    }

    private Element<Integer> betterCreateList(Integer... values) {
        Element<Integer> head = new Element<Integer>(9);
        head = LinkedListUtils.insertInFront(head, 6);
        head = LinkedListUtils.insertInFront(head, 3);
        return head;
    }

    @Test
    public void testFind_valueFound() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        Element<Integer> element = LinkedListUtils.find(head, 9);
        assertThat(element, is(notNullValue()));
        assertThat(element.value(), is(9));

        element = LinkedListUtils.find(head, 3);
        assertThat(element, is(notNullValue()));
        assertThat(element.value(), is(3));
    }

    @Test
    public void testAllElementsToString() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        String listStringRepresentation = LinkedListUtils.allElementsToString(head);
        assertThat(listStringRepresentation, is("[3,6,9]"));
    }

    @Test
    public void testDelete_firstElement() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        Element<Integer> newHead = LinkedListUtils.delete(head, 3);
        assertThat(newHead.value(), is(6));
        assertThat(newHead.next().value(), is(9));
    }

    @Test
    public void testDelete_lastElement() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        Element<Integer> newHead = LinkedListUtils.delete(head, 9);
        assertThat(newHead.value(), is(3));
        assertThat(newHead.next().value(), is(6));
    }

    @Test
    public void testDelete_fromMiddle() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        Element<Integer> newHead = LinkedListUtils.delete(head, 6);
        assertThat(newHead.value(), is(3));
        assertThat(newHead.next().value(), is(9));
    }

    @Test
    public void testDelete_nothingToDelete() {
        Element<Integer> head = createList(9, 6, 3); //3,6,9
        Element<Integer> newHead = LinkedListUtils.delete(head, 99);
        assertThat(newHead.value(), is(3));
        assertThat(newHead.next().value(), is(6));
        assertThat(newHead.next().next().value(), is(9));
    }

    private Element<Integer> createList(Integer... values) {
        if (values.length == 0)
            return null;

        Element<Integer> head = new Element<Integer>(values[0]);

        if (values.length > 1) {
            for (int i = 1; i < values.length; i++) {
                Element<Integer> element = new Element<Integer>(values[i]);
                element.setNext(head);
                head = element;
            }
        }
        return head;
    }
}