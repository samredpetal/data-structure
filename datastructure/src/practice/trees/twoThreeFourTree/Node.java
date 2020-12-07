package practice.trees.twoThreeFourTree;


public class Node {
    private int keys[];
    private int t = 4;
    private Node[] childs;
    private int n;
    private boolean leaf;

    public Node(boolean leaf) {
        keys = new int[2 * t - 1];
        childs = new Node[2 * t];
        n = 0;
        this.leaf = leaf;
    }

    public void insertNonFull(int key) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > key) {
                keys[i+1] = keys[i];
                i--;
            }

            keys[i+1] = key;
            n = n+1;
        }else {
            while (i >= 0 && keys[i] > key) {
                i--;
            }

            if (childs[i + 1].getN() == 2 * t - 1) {
                splitChild(i + 1, childs[i + 1]);

                if (keys[i + 1] < key) {
                    i++;
                }
            }
            childs[i+1].insertNonFull(key);
        }
    }

    public void splitChild(int i, Node y) {
        Node z = new Node(y.leaf);
        z.n = t - 1;

        for(int j = 0; j < t - 1; j++) {
            z.keys[j] = y.keys[j + t];
        }

        if (!y.leaf) {
            for(int j = 0; j < t; j++) {
                z.childs[j] = y.childs[j + t];
            }
        }

        y.n = t - 1;

        for(int j = n; j >= i+1; j--) {
            childs[j + 1] = childs[j];
        }

        childs[i+1] = z;

        for(int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }

        keys[i] = y.keys[t - 1];
        n++;
    }

    public void traverse() {
        int i;
        for(i = 0; i < n; i++) {
            if (!leaf) {
                childs[i].traverse();
            }
            System.out.print(" " + keys[i]);
        }

        if (!leaf) {
            childs[i].traverse();
        }
    }

    public Node search(int key) {
        int i = 0;
        while (i < n && key > keys[i]) {
            i++;
        }

        if (keys[i] == key) {
            return this;
        }

        if (leaf) {
            return null;
        }

        return childs[i].search(key);
    }

    public int[] getKeys() {
        return keys;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public Node[] getChilds() {
        return childs;
    }

    private int findkey(int key) {
        int idx = 0;
        while (idx < n && keys[idx] < key) {
            ++idx;
        }
        return idx;
    }

    public void remove(int key) {
        int idx = findkey(key);
        if (idx < n && keys[idx] == key) {
            if (leaf) {
                removeFromLeaf(idx);
            } else {
                removeFromNonLeaf(idx);
            }
        } else {
            if (leaf) {
                System.out.println("The key " + key + " does not exist");
                return;
            }

            boolean flag = (idx == n);

            if (childs[idx].n < t) {
                fill(idx);
            }

            if (flag && idx > n) {
                childs[idx - 1].remove(key);
            } else {
                childs[idx].remove(key);
            }
        }
    }

    private void removeFromLeaf(int idx) {
        for(int i = idx + 1; i < n; i++) {
            keys[i - 1] = keys[i];
        }
        n--;
    }

    private void removeFromNonLeaf(int idx) {
        int k = keys[idx];

        if (childs[idx].n >= t) {
            int pred = getPred(idx);
            keys[idx] = pred;
            childs[idx].remove(pred);
        } else if (childs[idx + 1].n >= t) {
            int succ = getSucc(idx);
            keys[idx] = succ;
            childs[idx + 1].remove(succ);
        } else {
            merge(idx);
            childs[idx].remove(k);
        }
    }

    private int getPred(int idx) {
        Node cur = childs[idx];
        while (!cur.leaf) {
            cur = cur.childs[cur.n];
        }
        return cur.keys[cur.n - 1];
    }

    private int getSucc(int idx) {
        Node cur = childs[idx + 1];
        while (!cur.leaf) {
            cur = cur.childs[0];
        }
        return cur.keys[0];
    }

    private void fill(int idx) {
        if (idx != n && childs[idx + 1].n >= t) {
            borrowFromPrev(idx);
        } else if (idx != n && childs[idx + 1].n >= t) {
            borrowFromNext(idx);
        } else {
            if (idx != n) {
                merge(idx);
            } else {
                merge(idx - 1);
            }
        }
    }

    private void borrowFromPrev(int idx) {
        Node child = childs[idx];
        Node sibling = childs[idx - 1];

        for(int i = child.n - 1; i >= 0; --i) {
            child.keys[i + 1] = child.keys[i];
        }

        if (!child.leaf) {
            for(int i = child.n; i >= 0; --i) {
                child.childs[i + 1] = child.childs[i];
            }
        }

        child.keys[0] = keys[idx - 1];

        if (!leaf) {
            child.childs[0] = sibling.childs[sibling.n];
        }

        keys[idx - 1] = sibling.keys[sibling.n - 1];
        child.n++;
        sibling.n--;
    }

    private void borrowFromNext(int idx) {
        Node child = childs[idx];
        Node sibling = childs[idx + 1];

        child.keys[child.n] = keys[idx];

        if (!child.leaf) {
            child.childs[child.n + 1] = sibling.childs[0];
        }

        keys[idx] = sibling.keys[0];

        for(int i = 1; i < sibling.n; ++i) {
            sibling.keys[i - 1] = sibling.keys[i];
        }

        if (!sibling.leaf) {
            for(int i = 1; i <= sibling.n; ++i) {
                sibling.childs[i - 1] = sibling.childs[i];
            }
        }

        child.n++;
        sibling.n--;
    }

    private void merge(int idx) {
        Node child = childs[idx];
        Node sibling = childs[idx + 1];

        child.keys[t - 1] = keys[idx];

        for(int i = 0; i < sibling.n; ++i) {
            child.keys[i + t] = sibling.keys[i];
        }

        if (!child.leaf) {
            for(int i = 0; i <= sibling.n; ++i) {
                child.childs[i + t] = sibling.childs[i];
            }
        }

        for(int i = idx+1; i < n; ++i) {
            keys[i - 1] = keys[i];
        }

        for(int i = idx+2; i <= n; ++i) {
            childs[i - 1] = childs[i];
        }

        child.n += sibling.n + 1;
        n--;
    }

    public boolean isLeaf() {
        return leaf;
    }
}
