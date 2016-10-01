package pl.bzawadka.pie.linkedlist;

/**
 * Stateful list with head and tail pointers.
 * <p/>
 * PROBLEM: head and tail are global pointers to the first and last element, respectively,
 * of a singly linked list of integers. Implement functions for the following
 * prototypes:
 * <p/>
 * bool delete( Element *elem );
 * bool insertAfter( Element *elem, int data );
 * <p/>
 * The argument to delete is the element to be deleted. The two arguments to
 * insertAfter give the element after which the new element is to be inserted and
 * the data for the new element. It should be possible to insert at the beginning of the
 * list by calling insertAfter with NULL as the element argument. These functions
 * should return a boolean indicating success.
 * Your functions must keep the head and tail pointers current.
 */
public class LinkedList<T> {

    private Element<T> head;
    private Element<T> tail;

    private static boolean SUCCESS = true;
    private static boolean FAILURE = false;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public LinkedList(T value) {
        this(new Element<T>(value));
    }

    public LinkedList(Element<T> element) {
        head = element;
        tail = element;
    }

    /**
     * @param element element to be deleted
     * @return boolean indicating success
     */
    public boolean delete(Element<T> element) {
        if (isEmpty()) return false;
        if (hasOneElement()) {
            // first element with the same value will be deleted
            if (head.value() == element.value()) {
                head = null;
                tail = null;
                return SUCCESS;
            } else {
                return FAILURE;
            }
        } else {
            return false;
        }
    }

    /**
     * @param element the element after which the new element is to be inserted and
        the . It should be possible to insert at the beginning of the
        list by calling insertAfter with NULL as the element argument
     * @param data data for the new element
     * @return boolean indicating success
     */
    public boolean insertAfter(Element<T> element, T data) {
        Element<T> newElement = new Element<T>(data);

        //head is NULL -> insert at the beginning, as head
        if (element == null) {
            newElement.setNext(head);
            head = newElement;
            if (tail == null)
                tail = head;
            return SUCCESS;
        }

        //list is empty - insert as head and tail
        if (isEmpty()) {
            head = newElement;
            tail = newElement;
            return SUCCESS;
        } else if (hasOneElement()) {   //list has one item - insert at the end
            head.setNext(newElement);
            tail = newElement;
            return SUCCESS;
        } else if (hasTwoElements()) {  //list has two items - insert in the middle or as tail, depends on element
            if (head.value().equals(element.value())) {
                head.setNext(newElement);
                newElement.setNext(tail);
            } else {
                tail.setNext(newElement);
                tail = newElement;
            }
            return SUCCESS;
        } else {                    //list has more than two items
            /*
            1, 2, 3         element 3   new 4

1!=3

2!=3




             */

            Element<T> e = head;
            while(e.hasNext()) {
                if (e.value() == element.value()) {
                    Element<T> next = e.next();
                    e.setNext(element);
                    element.setNext(next);
                    return SUCCESS;
                }
                e = e.next();
            };

        }

        return FAILURE;
    }

    public boolean isEmpty() {
        assertStateConsistent();
        return head == null && tail == null;
    }

    public boolean hasOneElement() {
        assertStateConsistent();
        return head == tail;
    }

    public boolean hasTwoElements() {
        assertStateConsistent();
        return head.next() == tail;
    }

    private void assertStateConsistent() {
        if ((head == null && tail != null) || (head != null && tail == null))
            throw new IllegalStateException("both or none HEAD and TAIL must be null");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (head != null) {
            sb.append(head.value());
            Element<T> element = head;
            while (element.hasNext()) {
                element = element.next();
                sb.append("," + element.value());
            }
        }
        sb.append("]");
        return sb.toString();
    }
}


