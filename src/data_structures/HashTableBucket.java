package data_structures;

import java.util.LinkedList;

/**
 * A HashTable can be used to implement a set or map with the access average of O(1) in time complexity.
 * A key can be transformed into a table index by using an index calculation based on a hashing mechanism.
 * When generating hashcodes it is important to get a random distribution over the table to avoid collisions.
 * If too many collisions occur the search will end up being O(n).
 *
 * This implementation uses linked-lists as internal structures for each table index. If a collision occurs the entry
 * will be added to the end of the list. It also uses javas hashCode algorithm for index calculation.
 *
 * @author mIngemarsson
 */
public class HashTableBucket<K, V> {

    private static class Entry<K, V> {

        public K key;
        public V value;

        public Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    private LinkedList<Entry<K, V>>[] table;
    private int nrOfEntries;

    /**
     * constructor method, creates an empty hash table
     * @param initialSize to avoid collisions best practice is to use a prime number as initial size.
     */
    @SuppressWarnings("unchecked")
    public HashTableBucket(int initialSize) {
        table = new LinkedList[initialSize];
        nrOfEntries=0;
    }

    /**
     * This method returns the value of a given key with an average of O(1).
     * @param key of given entry
     * @return value of given entry or null if unsuccessful.
     */
    public V get(K key) {
        int index = key.hashCode() % table.length;
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            return null;
        }
        for (Entry<K, V> e : table[index]) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * This method inserts a entry to the table. will rehash if size exceeds 75% of the table.
     * @param key of new entry.
     * @param value of new entry.
     * @return value of inserted entry or null if unsuccessful.
     */
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        // some unusual cases will result in negative indexing.
        if (index < 0) {
            index += table.length;
        }
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        } else {
            V oldValue;
            for (Entry<K, V> e : table[index]) {
                if (e.key.equals(key)) {
                    oldValue = e.value;
                    e.value = value;
                    return oldValue;
                }
            }
        }
        table[index].addFirst(new Entry<>(key, value));
        nrOfEntries++;
        if(((double)nrOfEntries/table.length) >= 0.75)
            rehash();
        return null;
    }

    /**
     * This method will remove a given entry from the table.
     * @param key of entry to be removed.
     * @return value of removed entry.
     */
    public V remove(K key){
        V removedItem;
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        if (table[index] == null) return null;

        for (Entry<K, V> e : table[index]) {
            if (e.key.equals(key)) {
                removedItem = e.value;
                table[index].remove(e);
                if(table[index].isEmpty())
                    table[index] = null;
                return removedItem;
            }
        }
        return null;
    }

    // will double the size and make sure it is prime
    private void rehash(){
        LinkedList<Entry<K,V>>[] oldTable = table;
        int newLength = nextPrime(table.length*2);
        nrOfEntries = 0;
        table = new LinkedList[newLength];
        for(LinkedList<Entry<K, V>> idx: oldTable) {
            if(idx!=null) {
                for (Entry<K, V> e : idx) {
                    put(e.key, e.value);
                }
            }
        }
    }


    // Function to return the smallest prime number greater than n
    private int nextPrime(int n) {
        if (n <= 1)
            return 2;
        int prime = n;
        boolean found = false;
        while (!found) {
            prime++;
            if (isPrime(prime))
                found = true;
        }
        return prime;
    }

    private boolean isPrime(int n) {
        // Corner cases
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;

        // loop until prime found
        for (int i = 5; i * i <= n; i = i + 6)
            if (n % i == 0 || n % (i + 2) == 0)
                return false;
        return true;
    }



    @Override
    public String toString() {
        String info="";
        info+="length: " + table.length + "\n";
        for(LinkedList<Entry<K, V>> idx: table) {
            if(idx==null){
                info+="[" + idx;
            }else {
                info +="[";
                for(Entry<K, V> e : idx) {
                    info+= e.key + ":" + e.value + ", ";
                }
                info = info.substring(0,info.length()-2);
            }
            info+="]\n";
        }
        return info;
    }
}
