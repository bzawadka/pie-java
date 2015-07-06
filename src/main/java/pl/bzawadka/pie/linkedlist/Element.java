package pl.bzawadka.pie.linkedlist;

public class Element<T> {

    private final T value;
    private Element<T> next;

    public Element(T value) {
        this.value = value;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Element<T> next() {
        return next;
    }

    public T value() {
        return value;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "{" + value +  '}';
    }
}
