package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * O(1) HashMap implementation
 */
public class HashMap {

    public static void main(String[] args) {
        // https://coderpad.io/sandboxs
        JUnitCore.main("pl.bzawadka.pie.algo.HashMap");
    }

    @Test
    public void testMyHashMapCorrectness() {
        MyHashMap map = new MyHashMap();
        map.put(10, "cat");
        map.put(26, "blackcat");
        map.put(42, "tiger");
        map.put(44, "dog");
        // [10:cat]->[26:blackcat]->[42:tiger]
        // [44:dog]

        assertThat(map.get(10), is(equalTo("cat")));
        assertThat(map.get(26), is(equalTo("blackcat")));
        assertThat(map.get(44), is(equalTo("dog")));
        assertThat(map.get(999), is(equalTo(null))); // 999 was never added
        map.remove(123123); // should have no effect

        map.remove(44);
        // [10:cat]->[26:blackcat]->[42:tiger]
        assertThat(map.get(44), is(equalTo(null)));

        map.remove(26);
        // [10:cat]->[42:tiger]
        assertThat(map.get(10), is(equalTo("cat")));
        assertThat(map.get(42), is(equalTo("tiger")));
        assertThat(map.get(26), is(equalTo(null)));

        map.remove(42);
        // [10:cat]
        assertThat(map.get(10), is(equalTo("cat")));
        assertThat(map.get(42), is(equalTo(null)));
    }

    public static class MyHashMap {
        private int mapSize = 16;
        private Entry[] entries = new Entry[mapSize];

        public static class Entry {
            public Integer key;
            public String value;
            public Entry next;

            public Entry(Integer key, String value) {
                this.key = key;
                this.value = value;
            }

            public boolean hasNext() {
                return next != null;
            }

            public Entry next() {
                return next;
            }

            public void setNext(Entry entry) {
                this.next = entry;
            }

            @Override
            public String toString() {
                return "[" + key + ":" + value + "]";
            }
        }

        public void put(Integer key, String value) {
            int bucketIndex = key.hashCode() % mapSize;
            Entry firstEntry = entries[bucketIndex];
            if (firstEntry == null) {
                entries[bucketIndex] = new Entry(key, value);
            } else {
                Entry entry = firstEntry;
                while (entry.hasNext()) {
                    entry = entry.next();
                }
                entry.setNext(new Entry(key, value));
            }
        }

        public String get(Integer key) {
            int bucketIndex = key.hashCode() % mapSize;
            Entry firstEntry = entries[bucketIndex];
            if (firstEntry == null) {
                return null;
            }
            Entry entry = firstEntry;
            while (entry.key != key && entry.hasNext()) {
                entry = entry.next();
            }
            return entry.key.equals(key) ? entry.value : null;
        }

        public void remove(Integer key) {
            int bucketIndex = key.hashCode() % mapSize;
            Entry firstEntry = entries[bucketIndex];
            if (firstEntry == null) {
                return;
            }
            entries[bucketIndex] = removeFromLinkedList(firstEntry, key);
        }

        // cases: remove head, remove middle, remove last
        private Entry removeFromLinkedList(Entry head, Integer keyToRemove) {
            if (head.key.equals(keyToRemove)) {
                return head.next();
            }

            Entry entry = head;
            do {
                Entry next = entry.next();
                if (next.key.equals(keyToRemove)) {
                    entry.setNext(next.next());
                    return head;
                }
                entry = entry.next();
            } while (entry.hasNext());

            return head;
        }
    }
}
