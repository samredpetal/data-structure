package practice.trees.binarySearchTree;

import practice.trees.Node;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.add(30);
        tree.add(20);
        tree.add(40);
        tree.add(50);
        tree.add(70);
        tree.add(60);
        tree.add(80);

        tree.removeElement(20);
        tree.removeElement(30);
        tree.removeElement(50);
        System.out.println("inorder");
        for (Node node1 : tree.inOrder()) {
            System.out.println(node1);
        }


    }
}
