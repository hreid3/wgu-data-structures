package edu.wgu.hreid6.datastructures.performance;

/**
 * HR
 * Data structures must implememnt this interface based on the requirements in performance assessment.
 *
 * Created by hreid6 on 1/29/17.
 */
public interface StorableContacts<K, E> {

    /**
     * HR
     * Inserts a new entry into the data structure.
     *
     * @param key
     * @param entry
     * @return true if operation succeeds. False otherwise
     */
    boolean insert(K key, E entry);

    /**
     * HR
     * Deletes entry by specified key.
     *
     * @param key
     * @return the removed entry.  Null if no entry was found.
     */
    E delete(K key);

    /**
     * HR
     * Finds the entry by specified key.
     *
     * @param key
     * @return returns the entry or null if not found.
     */
    E find(K key);

    /**
     * HR
     * returns the number of elements in data structure.
     *
     * @return
     */
    int size();
}
