package practice.trees.avl;

import practice.trees.Node;

public class Main {
    public static void main(String[] args) {
        AvlTree tree = new AvlTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(0);
        tree.insert(7);
        tree.insert(6);
        for (Node node : tree.inorder()) {
            System.out.println(node.getKey());
        }
    }
}
