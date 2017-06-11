package edu.wgu.hreid6.datastructures.performance.hashtable;

import edu.wgu.hreid6.datastructures.performance.StorableContacts;

/**
 * HR
 * A hash table data structure from the ground up that does not use any other data structure
 * classes.
 *
 * This class is not thread safe.
 *
 * Created by hreid6 on 1/29/17.
 */
public class Hashtable<K, E> implements StorableContacts<K, E> {

    public static final int DEFAULT_BUCKET_SIZE = 13;

    private int bucketSize;
    private int count;

    private LinkedBag<TableEntry<K, E>>[] entries; // HR: Ha, no probing to avoid collisions

    /**
     * HR
     * Constructs a new Hashtable with specific bucket size.
     *
     * @param bucketSize
     */
    public Hashtable(int bucketSize) {
        this.bucketSize = bucketSize;
        this.entries = new LinkedBag[this.getBucketSize()];
        for(int i = 0; i < this.getBucketSize(); i++) { // TODO: Hmm, we could lazy load...
            this.entries[i] = new LinkedBag<TableEntry<K, E>>();
        }
    }

    /**
     * HR
     * Constructs a new Hashtable with the default size.
     *
     */
    public Hashtable() {
        this(DEFAULT_BUCKET_SIZE);
    }

    /**
     * HR
     * Inserts a new entry into this hashtable.
     *
     * @param key
     * @param entry nulls are allowed
     * @return true if operation succeeds, false if entry exists.
     */
    @Override
    public boolean insert(K key, E entry) {
        // Does element already exist,
        if (key == null) {
            throw new IllegalArgumentException("key must contain a value");
        }
        if (this.find(key) == null) {
            int bucketKey = this.calculateBucketKey(key);
            TableEntry<K, E> tableEntry = new TableEntry<>(key, entry);
            this.entries[bucketKey].add(tableEntry);
            this.count++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * HR
     * Deletes entry by specified key.
     *
     * @param key
     * @return the removed entry.  Null if no entry was found.
     */
    @Override
    public E delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must contain a value");
        }
        E element = this.find(key); // HR: TODO: reuse or call contains on bucket implementation.
        if (element != null) {
            int bucketKey = this.calculateBucketKey(key);
            TableEntry<K, E> tableEntry = new TableEntry<>(key);
            this.entries[bucketKey].remove(tableEntry);
            this.count--;
            assert this.count >= 0;
        }
        return element;
    }

    /**
     * HR
     * Finds the entry by specified key.
     *
     * @param key
     * @return returns the entry or null if not found.
     */
    @Override
    public E find(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must contain a value");
        }
        E result = null;
        int bucketKey = this.calculateBucketKey(key);
        TableEntry<K, E> tableEntry = new TableEntry<K, E>(key);
        tableEntry = this.entries[bucketKey].findEntry(tableEntry);
        if (tableEntry != null) {
            result = tableEntry.getElement();
        }
        return result;
    }

    /**
     * HR
     * returns the number of elements in hashtable.
     *
     * @return
     */
    @Override
    public int size() {
        return this.count;
    }

    /**
     * HR
     * Calculates the bucket index based on hashcode % bucketSize
     *
     * @param key
     * @return
     */
    protected int calculateBucketKey(K key) {
        int keyVal = key.hashCode() % this.getBucketSize();
        if (keyVal < 0) {
            keyVal += this.getBucketSize();
        }
        assert keyVal >= 0 && keyVal <= this.getBucketSize();
        return keyVal;
    }

    /**
     *HR
     * @return the size of the buckets.
     */
    protected int getBucketSize() { return this.bucketSize; }

    /**
     *  TableEntry instance used in Hashtable.
     * @param <K>
     * @param <E>
     */
    public static class TableEntry<K, E> {
        private K key;
        private E element;

        public TableEntry(K key, E element) {
            this.key = key;
            this.element = element;
        }

        public TableEntry(K key) {
            this(key, null);
        }

        public K getKey() {
            return this.key;
        }

        public E getElement() {
            return this.element;
        }

        @Override
        public boolean equals(Object obj) {
            return this.getKey().equals(((TableEntry<K, E>)obj).getKey());
        }

        @Override
        public int hashCode() {
            return this.getKey().hashCode();
        }
    }
}
