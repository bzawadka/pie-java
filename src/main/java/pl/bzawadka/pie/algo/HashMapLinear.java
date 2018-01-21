package pl.bzawadka.pie.algo;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * O(n) primitive HashMap implementation
 */
public class HashMapLinear {

    public static void main(String[] args) {
        // https://coderpad.io/sandboxs
        JUnitCore.main("pl.bzawadka.pie.algo.HashMapLinear");
    }

    @Test
    public void testHashMapCorrectness() {
        LinearHashMap<Integer, String> map = new LinearHashMap();
        map.put(33, "cat");
        map.put(44, "dog");
        map.put(44, "underdog"); // memory waste - element will be added internally to the map, but never retrieved
        assertThat(map.get(33), is(equalTo("cat")));
        assertThat(map.get(44), is(equalTo("dog")));
        assertThat(map.get(999), is(equalTo(null))); // 999 was never added
        map.remove(123123); // should have no effect
        map.remove(33);
        assertThat(map.get(33), is(equalTo(null)));
    }

    public static class LinearHashMap<K,V> {
        static class Entry<K,V> {
            K key;
            V value;

            public Entry(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }

        private List<Entry<K,V>> entries = new ArrayList<>();

        public void put(K key, V value) {
            entries.add(new Entry(key, value));
        }

        public V get(K key) {
            Entry<K,V> entry = entries
                    .stream()
                    .filter(e -> e.key.equals(key))
                    .findFirst()
                    .orElse(new Entry(-1, null));
            return entry.value;
        }

        public void remove(K key) {
            Optional<Entry<K,V>> entry = entries
                    .stream()
                    .filter(e -> e.key.equals(key))
                    .findFirst();

            if (entry.isPresent()) {
                entries.remove(entry.get());
            }
        }
    }
}
