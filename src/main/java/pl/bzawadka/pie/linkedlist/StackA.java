package pl.bzawadka.pie.linkedlist;

/**
 * PROBLEM Discuss the stack data structure. Implement a stack in C using either
 * a linked list or a dynamic array, and justify your decision. Design the interface to
 * your stack to be complete, consistent, and easy to use.
 * <p/>
 * Dynamic array (an array that changes size as needed when elements are added):
 * + offer random access to every element (not needed in stack contract though)
 * - must be resized occasionally (which can be a time-consuming operation as elements are copied from the old array to the new array.)
 * + probably faster
 * <p/>
 * Linked list (allocate memory dynamically for each element):
 * + easier implementation
 * - probably slower, since allocating memory with each operation
 */
public class StackA<T> implements Stack<T> {

    private Element<T> head = null;

    public void push(T value) {
        if (head == null) {
            head = new Element<T>(value);
        } else {
            Element<T> element = new Element<T>(value);
            element.setNext(head);
            head = element;
        }
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        T value = head.value();
        head = head.next();
        return value;
    }
}
