package practice.trees.avl;


import practice.trees.Node;

import java.util.LinkedList;

public class AvlTree {
    private Node root;

    public AvlTree() {
    }

    public Node getRoot() {
        return root;
    }

    public int height(Node node) {
        return 0;
    }

    private Node rotateWithLeft(Node c) {
        Node p;

        p = c.getLeft();
        c.setLeft(p.getRight());
        p.setRight(c);

        c.setHeight(Math.max(height(c.getLeft()), height(c.getRight())) + 1);
        p.setHeight(Math.max(height(p.getLeft()), height(p.getRight())) + 1);
        return p;
    }

    private Node rotateWithRight(Node node) {
        return null;
    }

    private Node doubleRotateWithLeft(Node c) {
        Node tmp;

        c.setLeft(rotateWithRight(c.getLeft()));
        tmp = rotateWithLeft(c);
        return tmp;
    }

    private Node doubleRotateWithRight(Node node) {
        return null;
    }

    private Node avlInsert(Node newNode, Node par) {
        Node newpar = par;

        if (newNode.getKey() < par.getKey()) {
            if (par.getLeft() == null) {
                par.setLeft(newNode);
            } else {
                par.setLeft(avlInsert(newNode, par.getLeft()));
                if ((height(par.getLeft()) - height(par.getRight())) == 2) {
                    if (newNode.getKey() < par.getLeft().getKey()) {
                        newpar = rotateWithLeft(par);
                    } else {
                        newpar = doubleRotateWithLeft(par);
                    }
                }
            }
        } else if (newNode.getKey() > par.getKey()) {
            if (par.getRight() == null) {
                par.setRight(newNode);
            } else {
                par.setRight(avlInsert(newNode, par.getRight()));
                if ((height(par.getRight()) - height(par.getLeft())) == 2) {
                    if (newNode.getKey() > par.getRight().getKey()) {
                        newpar = rotateWithRight(par);
                    } else {
                        newpar = doubleRotateWithRight(par);
                    }
                }
            }
        } else {
            System.out.println("Duplicated element");
        }

        if ((par.getLeft() == null) && (par.getRight() != null)) {
            par.setHeight(par.getHeight() + 1);
        } else if ((par.getRight() == null) && (par.getLeft() != null)) {
            par.setHeight(par.getLeft().getHeight() + 1);
        } else {
            par.setHeight(Math.max(height(par.getLeft()), height(par.getRight()) + 1));
        }
        return newpar;
    }

    public void insert(int data) {
        Node node = new Node(data);

        if (root == null) {
            root = node;
        } else {
            root = avlInsert(node, root);
        }
    }

    public LinkedList<Node> inorder() {
        return root.inOrder();
    }
}
