package practice.trees.twoThreeFourTree;


public class TwoFourTree {
    private Node root;
    private int t = 4;

    public TwoFourTree() {
    }

    public void traverse() {
        if (root != null) {
            root.traverse();
        }
    }

    public Node search(int key) {
        return (root == null) ? null : root.search(key);
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(true);
            root.getKeys()[0] = key;
            root.setN(1);
        } else {
            if (root.getN() == 2 * t - 1) {
                Node s = new Node(false);
                s.getChilds()[0] = root;

                s.splitChild(0, root);
                int i = 0;
                if (s.getKeys()[0] < key) {
                    i++;
                }
                s.getChilds()[i].insertNonFull(key);
                root = s;
            } else {
                root.insertNonFull(key);
            }
        }

    }

    public void remove(int key) {
        if (root == null) {
            System.out.println("Tree empty");
            return;
        }

        root.remove(key);

        if (root.getN() == 0) {
            if (root.isLeaf()) {
                root = null;
            } else {
                root = root.getChilds()[0];
            }
        }
    }

}
