package edu.wgu.hreid6.datastructures.performance.hashtable;

/**
 * HR
 * Implementation of a bag using Linked List
 *
 * Class is not thread safe.
 *
 * Created by hreid6 on 1/28/17.
 */
public class LinkedBag<E> {

    private Node head;

    /**
     * HR
     * Adds an element to the bag
     *
     * @param element
     * @return
     */
    public boolean add(E element) {
        Node aNode = new Node(element);
        aNode.next = head;
        this.head = aNode;
        return true;
    }

    /**
     * HR
     * Removes the element from the bag.
     *
     * @return
     */
    public E remove() {
        if (this.head != null) {
            Node removedNode = this.head;
            this.head = this.head.next;
            return removedNode.data;
        }
        return null;
    }

    /**
     * HR
     * Removes the specified element from the bag.
     *
     * @param element
     * @return
     */
    public boolean remove(E element) {
        Node aNode = this.findNode(element);
        if(aNode != null) {
            aNode.data = head.data; // Swap the data
            remove(); // and remove head
            return true;
        }
        return false;
    }

    /**
     * HR
     * Checks to see if element is in the bag.
     *
     * @param anEntry
     * @return
     */
    public boolean contains(E anEntry) {
        return this.findEntry(anEntry) != null;
    }

    /**
     * HR
     * Finds the element in the bag without removing it.
     *
     * @param anEntry
     * @return
     */
    public E findEntry(E anEntry) {
        Node aNode = this.findNode(anEntry);
        if (aNode != null) {
            return aNode.data;
        } else {
            return null;
        }
    }

    private Node findNode(E anEntry) {
        Node aNode = head;
        if (aNode != null) {
            do {
                if (aNode.data.equals(anEntry)) {
                    break;
                }
            } while((aNode = aNode.next) != null); // O(n) arrrg!
        }
        return aNode;
    }

    /**
     * HR
     * Created by hreid6 on 1/28/17.
     */
    private class Node {
        private E data;
        private Node next;

        private Node(E data, Node nextNode) {
            this.data = data;
            this.next = nextNode;
        }

        public Node(E data) {
            this(data, null);
        }
    }
}
