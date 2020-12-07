package practice.trees.binaryTree;

import practice.trees.Node;

public class Main {
    public static void main(String[] args) {
        BinaryTreeNode node = new BinaryTreeNode(5);
        node.setLeft(new BinaryTreeNode(7));
        node.setRight(new BinaryTreeNode(2));

        node.getLeft().setLeft(new BinaryTreeNode(9));
        node.getRight().setLeft(new BinaryTreeNode(3));

        node.getRight().getLeft().setRight(new BinaryTreeNode(1));
        node.getRight().getLeft().getRight().setLeft(new BinaryTreeNode(6));
        node.getRight().getLeft().setLeft(new BinaryTreeNode(2));

        System.out.println("Amount of nodes:");
        System.out.println(node.amountOfNodes());

        System.out.println("Preorder:");
        for (Node node1 : node.preOrder()) {
            System.out.println(node1);
        }

        System.out.println();
        System.out.println("Inorder:");
        for (Node node1 : node.inOrder()) {
            System.out.println(node1);
        }

        System.out.println();
        System.out.println("Postorder:");
        for (Node node1 : node.postOrder()) {
            System.out.println(node1);
        }

        System.out.println();
        System.out.println("Amount of liefs:");
        System.out.println(node.amountOfLiefs());

        System.out.println();
        System.out.println("Height:");
        System.out.println(node.height());
    }
}
