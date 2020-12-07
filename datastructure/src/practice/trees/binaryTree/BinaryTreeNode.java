package practice.trees.binaryTree;

import practice.trees.Node;

public class BinaryTreeNode extends Node{

    public BinaryTreeNode(int key) {
        super(key);
    }
    public BinaryTreeNode(int key, String data) {
        super(key);
        setData(data);
    }
}
