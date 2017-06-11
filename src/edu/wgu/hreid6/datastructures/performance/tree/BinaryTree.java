package edu.wgu.hreid6.datastructures.performance.tree;

import edu.wgu.hreid6.datastructures.performance.StorableContacts;

/**
 * HR
 * A tree data structure from the ground up that does not use any other data structure
 * classes.
 * <p>
 * This class is not thread safe.
 * <p>
 * Created by hreid6 on 1/29/17.
 */
public class BinaryTree<K, E> implements StorableContacts<K, E> {

    private BinaryNode<K, E> root;
    private int size;

    @Override
    public boolean insert(K key, E entry) {
        if (key == null) {
            throw new IllegalArgumentException("key must contain a value");
        }

        if (this.find(key) == null) { // HR: TODO:  We could remove this and let the tree just work for better performance
            BinaryNode<K, E> newNode = new BinaryNode<K, E>(key, entry, null);
            if (root == null) {
                root = newNode;
            } else {
                BinaryNode<K, E> parent = this.doFindInsertionNode(root, key);
                newNode.setParent(parent);
                if (key instanceof Comparable && parent.getKey() instanceof Comparable) {
                    if (((Comparable<K>) key).compareTo(parent.getKey()) < 0) {
                        parent.setLeft(newNode);
                    } else {
                        parent.setRight(newNode);
                    }
                } else {
                    throw new IllegalArgumentException("Please use a key that implements java.lang.Comparable interface.");
                }
            }
        } else {
            return false;
        }
        size++;
        return true;
    }

    @Override
    public E delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        BinaryNode<K, E> node = null;
        if (root != null) {
            node = this.doBinarySearch(root, key);
            if (node != null) {
                // HR: time to detach from tree
                if (node.getLeft() == null && node.getRight() == null) { // HR: A leaf
                    if (!this.setParent(node, null)) {
                        root = null; // HR: Only root is a leaf with no children
                    }
                } else if (node.getLeft() != null && node.getRight() != null) { // HR: search right of all left descendants
                    BinaryNode<K, E> farRightFromLeftNode = node.getLeft();
                    while (farRightFromLeftNode.getRight() != null) {
                        farRightFromLeftNode = farRightFromLeftNode.getRight();
                    }
                    farRightFromLeftNode.getLeft().setParent(farRightFromLeftNode.getParent());
                    this.setParent(node, farRightFromLeftNode);

                } else { // HR: Single child
                    BinaryNode<K, E> child = null;
                    if (node.getLeft() != null) {
                        child = node.getLeft();
                        if (node.getParent() == null) {
                            root = child;
                        } else {
                            node.getParent().setLeft(child);
                        }
                    } else {
                        child = node.getRight();
                        if (node.getParent() == null) {
                            root = child;
                        } else {
                            node.getParent().setRight(child);
                        }
                    }
                }
            }
            assert this.size >= 0;
        }
        if (node != null) {
            this.size--;
            return node.getData();
        } else {
            return null;
        }
    }

    @Override
    public E find(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key must contain a value");
        }

        E result = null;
        if (root == null) {
            return null;
        } else {
            BinaryNode<K, E> binaryNode = this.doBinarySearch(root, key);
            if (binaryNode != null) {
                result = binaryNode.getData();
            }
        }
        return result;
    }


    @Override
    public int size() {
        return this.size;
    }

    protected BinaryNode<K, E> doFindInsertionNode(BinaryNode<K, E> node, K key) {
        BinaryNode<K, E> result = node;
        if (node != null && key != null) {
            BinaryNode<K, E> childNode = this.getChildNode(node, key);
            if (childNode != null) {
                result = this.doFindInsertionNode(childNode, key);
            }
        } else {
            throw new IllegalArgumentException("Received a null parameter");
        }
        return result;
    }

    protected BinaryNode<K, E> doBinarySearch(BinaryNode<K, E> node, K key) {
        BinaryNode<K, E> result = null;
        if (node != null && key != null) {
            if (node.getKey().equals(key)) {
                result = node;
            } else {
                BinaryNode<K, E> childNode = this.getChildNode(node, key);
                if (childNode != null) {
                    result = this.doBinarySearch(childNode, key);
                }
            }
        } else {
            throw new IllegalArgumentException("Received a null parameter");
        }
        return result;
    }

    private BinaryNode<K, E> getChildNode(BinaryNode<K, E> node, K key) {
        BinaryNode<K, E> result = null;
        if (node != null && key != null) {
            if (key instanceof Comparable && node.getKey() instanceof Comparable) {
                Comparable<K> compareKey = (Comparable<K>) key;
                int compareValue = compareKey.compareTo(node.getKey());
                if (compareValue < 0 && node.getLeft() != null) {
                    result = node.getLeft();
                } else if (node.getRight() != null) {
                    result = node.getRight();
                }
            }
        } else {
            throw new IllegalArgumentException("Received a null parameter");
        }
        return result;
    }

    private boolean setParent(BinaryNode<K, E> node, BinaryNode<K, E> newNode) {
        if (node.getParent() != null) {
            if (node.getParent().getLeft() != null && node.getParent().getLeft().equals(node)) {
                node.getParent().setLeft(newNode);
            } else if (node.getParent().getRight() != null && node.getParent().getRight().equals(node)) {
                node.getParent().setRight(newNode);
            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
