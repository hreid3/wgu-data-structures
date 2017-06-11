package edu.wgu.hreid6.datastructures.performance.tree;

/**
 * HR
 *
 * Holds node data.
 * Created by hreid6 on 1/29/17.
 */
public class BinaryNode<K, E> {

    private BinaryNode<K, E> parent;
    private BinaryNode<K, E> left;
    private BinaryNode<K, E> right;
    private E data;
    private K key;

    public BinaryNode(K key, E data, BinaryNode<K, E> parent, BinaryNode<K, E> left, BinaryNode<K, E> right) {
        this.setKey(key);
        this.setData(data);
        this.setParent(parent);
        this.setLeft(left);
        this.setRight(left);
    }

    public BinaryNode(K key, E data, BinaryNode<K, E> parent) {
        this(key, data, parent, null, null);
    }

    public BinaryNode<K, E> getParent() {
        return this.parent;
    }

    public void setParent(BinaryNode<K, E> parent) {
        this.parent = parent;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public BinaryNode<K, E> getLeft() {
        return this.left;
    }

    public void setLeft(BinaryNode<K, E> left) {
        this.left = left;
    }

    public BinaryNode<K, E> getRight() {
        return this.right;
    }

    public void setRight(BinaryNode<K, E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getKey().equals(((BinaryNode<K, E>)obj).getKey());
    }

    @Override
    public int hashCode() {
        return this.getKey().hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + (this.getData() != null ? "::" + getData().toString() : "");
    }
}
