package practice.trees;


import java.util.LinkedList;

public class Node{
    private int key;
    private Node left;
    private Node right;
    private Node parent;
    private int height;
    private String data;

    public Node(int key) {
        this.key = key;
        left = right = null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node search(int key){
        for (Node node : this.getRoot().inOrder()) {
            if (node.getKey() == key) {
                return node;
            }
        }
        return null;
    }

    public void removeElement(int key) {
        Node node = this.search(key);
        if (node.getParent() != null) {
            Node parent = node.getParent();
            if (parent.getRight().equals(node)) {
                parent.setRight(null);
            } else if (parent.getLeft().equals(node)) {
                parent.setLeft(null);
            }
        }
    }

    public LinkedList<Node> preOrder() {
        LinkedList<Node> list = new LinkedList<>();
        list.add(this);
        if (this.getLeft() != null) {
            list.addAll(this.getLeft().preOrder());
        }
        if (this.getRight() != null) {
            list.addAll(this.getRight().preOrder());
        }
        return list;
    }

    public LinkedList<Node> inOrder() {
        LinkedList<Node> list = new LinkedList<>();
        if (this.getLeft() != null) {
            list.addAll(this.getLeft().inOrder());
        }
        list.add(this);
        if (this.getRight() != null) {
            list.addAll(this.getRight().inOrder());
        }
        return list;
    }

    public LinkedList<Node> postOrder() {
        LinkedList<Node> list = new LinkedList<>();
        if (this.getLeft() != null) {
            list.addAll(this.getLeft().postOrder());
        }
        if (this.getRight() != null) {
            list.addAll(this.getRight().postOrder());
        }
        list.add(this);
        return list;
    }

    public Node getRoot() {
        Node root;
        root = this;
        while (root.parent != null) {
            root = root.parent;
        }
        return root;
    }



    public int amountOfNodes() {
        int amount = 0;
        amount++;
        if (this.getLeft() != null) {
            amount += this.getLeft().amountOfNodes();
        }
        if (this.getRight() != null) {
            amount += this.getRight().amountOfNodes();
        }
        return amount;
    }

    public int amountOfChilds() {
        int amount = 0;
        if (this.getLeft() != null) {
            amount++;
            amount += this.getLeft().amountOfNodes();
        }
        if (this.getRight() != null) {
            amount++;
            amount += this.getRight().amountOfNodes();
        }
        return amount;
    }

    public int amountOfLiefs() {
        int amount = 0;
        if (this.getLeft() == null && this.getRight() == null) {
            amount++;
        }else{
            if (this.getLeft() != null) {
                amount += this.getLeft().amountOfLiefs();
            }
            if (this.getRight() != null) {
                amount += this.getRight().amountOfLiefs();
            }
        }
        return amount;
    }

    public int height() {
        int height;
        int leftHeight = 0;
        int rightHeight = 0;
        if (this.getLeft() != null) {
            leftHeight += this.getLeft().height();
        }
        if (this.getRight() != null) {
            rightHeight += this.getRight().height();
        }
        height = leftHeight >= rightHeight ? leftHeight : rightHeight;
        height++;
        return height;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return this.getLeft() == null && this.getRight() == null;
    }

    public boolean hasOneChild() {
        if (this.getLeft() != null && this.getRight() == null) {
            return true;
        } else if (this.getRight() != null && this.getLeft() == null) {
            return true;
        }
        return false;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                '}';
    }
}
