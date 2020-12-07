package practice.trees.binarySearchTree;

import practice.trees.Node;

import java.util.LinkedList;

public class BinarySearchTree{
    Node root;

    public BinarySearchTree() {
        root = null;
    }

    public void add(int key) {
        root = addRec(root, key);
    }

    private Node addRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.getKey()) {
            root.setLeft(addRec(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(addRec(root.getRight(), key));
        }
        return root;
    }

    public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node node, int key) {
        if (node.getKey() == key) {
            return node;
        }

        if (node.getKey() > key) {
            if (node.getLeft() != null) {
                return search(node.getLeft(), key);
            }
        }
        if (node.getRight() != null) {
            return search(node.getRight(), key);
        }
        return null;
    }


    public void removeElement(int key) {
        root = removeRec(root, key);
    }

    private Node removeRec(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.getKey()) {
            root.setLeft(removeRec(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(removeRec(root.getRight(), key));
        }
        else{
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setKey(minValue(root.getRight()));

            root.setRight(removeRec(root.getRight(), root.getKey()));
        }
        return root;
    }

    private int minValue(Node root) {
        int minv = root.getKey();
        while (root.getLeft() != null) {
            minv = root.getLeft().getKey();
            root = root.getLeft();
        }
        return minv;
    }

    public LinkedList<Node> inOrder() {
        return root.inOrder();
    }

    public LinkedList<Node> postOrder() {
        return root.postOrder();
    }

    public LinkedList<Node> preOrder() {
        return root.preOrder();
    }

}
