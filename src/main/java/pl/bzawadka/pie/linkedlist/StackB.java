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
public class StackB<T> implements Stack<T> {

    private Element<T> head = null;

    // bad implementation: head is not updated, push happens in the tail
    public void push(T value) {
        if (head == null) {
            head = new Element<T>(value);
        } else {
            Element<T> newElement = new Element<T>(value);
            //append to the end of list
            getTail().setNext(newElement);
        }
    }

    private Element<T> getTail() {
        Element<T> tail = head;
        while (tail.hasNext()) {
            tail = tail.next();
        }
        return tail;
    }

    // bad implementation: head is not updated, pop happens from the tail
    public T pop() {
        if (head == null) {
            return null;
        }
        return popTail();
    }

    private T popTail() {
        T tailValue = null;

        // regular case, traversing until to the last element of linked list
        if (head.hasNext()) {
            Element<T> element = head;
            while (element != null) {
                Element<T> next = element.next();
                if (!next.hasNext()) {
                    tailValue = next.value();
                    element.setNext(null);
                }
                element = element.next();
            }
        }
        // special case: one element in the list
        else {
            tailValue = head.value();
            head = null;
        }
        return tailValue;
    }
}
