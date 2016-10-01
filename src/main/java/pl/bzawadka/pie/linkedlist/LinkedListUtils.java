package pl.bzawadka.pie.linkedlist;

/**
 * Stateless Linked List
 * Collection of methods, where head is being passed as an argument
 * @param <T>
 */
public class LinkedListUtils<T> {

    public static Element<Integer> insertInFront(Element<Integer> head, int value) {
        Element<Integer> newHead = new Element<Integer>(value);
        newHead.setNext(head);
        return newHead;
    }

    public static Element<Integer> delete(Element<Integer> head, int value) {
        if (head.value().equals(value)) {
            return head.next();
        }
        Element<Integer> element = head;
        while (element.hasNext()) {
            Element<Integer> next = element.next();
            if (next.value().equals(value)) {
                element.setNext(next.next());
                return head;
            }
            element = element.next();
        }
        return head;
    }

    public static String allElementsToString(Element<Integer> head) {
        StringBuilder sb = new StringBuilder("[" + head.value());
        Element<Integer> element = head;
        while (element.hasNext()) {
            element = element.next();
            sb.append("," + element.value());
        }
        sb.append("]");
        return sb.toString();
    }

    public static Element<Integer> find2(Element<Integer> head, int value) {
        if (value == head.value())
            return head;

        Element<Integer> element = head;
        while (element.hasNext()) {
            element = element.next();
            if (value == element.value()) {
                return element;
            }
        }

        return null;
    }

    public static Element<Integer> find(Element<Integer> head, int value) {
        Element<Integer> element = head;
        while (element != null && element.value() != value) {
            element = element.next(); //if not found, next() is equal to null
        }
        return element;
    }
}


