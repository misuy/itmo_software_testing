package misuy.btree;

import java.util.ArrayList;
import java.util.List;

class BTreeNode {
    private BTreeNode parent;
    private int n;
    private boolean isLeaf;
    private List<Integer> keys;
    private List<BTreeNode> children;

    
    BTreeNode(int n, boolean isLeaf, BTreeNode parent) {
        this.n = n;
        this.isLeaf = isLeaf;
        this.parent = parent;
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
    }

    BTreeNode(int n, boolean isLeaf, BTreeNode parent, List<Integer> keys, List<BTreeNode> children) {
        this.n = n;
        this.isLeaf = isLeaf;
        this.parent = parent;
        this.keys = keys;
        this.children = children;
    }


    public Integer getKey(int idx) {
        return this.keys.get(idx);
    }

    public void setKey(int idx, Integer key) {
        if (idx >= this.n)
        {
            this.n++;
            this.keys.add(idx, key);
            this.children.add(idx + 1, null);
        }
        else
            this.keys.set(idx, key);
    }

    public void removeKey(int idx) {
        this.keys.remove(idx);
        this.n--;
    }


    public BTreeNode getChild(int idx) {
        return this.children.get(idx);
    }

    public void setChild(int idx, BTreeNode child) {
        this.children.set(idx, child);
    }

    public void removeChild(int idx) {
        this.children.remove(idx);
    }


    public int getN() {
        return this.n;
    }


    private void setParent(BTreeNode parent) {
        this.parent = parent;
    }


    public void insertKey(Integer key, BTree tree) {
        if (this.n >= (tree.t * 2 - 1)) {
            this.splitNode(key, tree).insertKey(key, tree);
            return;
        }

        int idx;
        for (idx = 0; idx < this.keys.size(); idx++)
            if (key < this.keys.get(idx))
                break;

        if (this.isLeaf)
        {
            this.keys.add(idx, key);
            this.children.add(idx, null);
            if (this.n == 0)
                this.children.add(idx, null);
            this.n++;
        }
        else
            this.children.get(idx).insertKey(key, tree);
    }

    
    private void addKeyToNotFullNode(Integer key, BTreeNode leftChild, BTreeNode rightChild) {
        int idx;
        for (idx = 0; idx < this.n; idx++)
            if (key < this.keys.get(idx))
                break;
                
        this.keys.add(idx, key);
        if (this.n != 0)
            this.children.remove(idx);
        this.children.add(idx, rightChild); 
        this.children.add(idx, leftChild);
        this.n++;
    }


    private BTreeNode splitNode(Integer key, BTree tree) {
        BTreeNode parent = (this.parent == null) ? new BTreeNode(0, false, null) : this.parent;

        BTreeNode leftNode = new BTreeNode(tree.t-1, this.isLeaf, parent, new ArrayList<>(this.keys.subList(0, tree.t-1)), new ArrayList<>(this.children.subList(0, tree.t)));
        for (BTreeNode node: this.children.subList(0, tree.t))
            if (node != null)
                node.setParent(leftNode);

        BTreeNode rightNode = new BTreeNode(tree.t-1, this.isLeaf, parent, new ArrayList<>(this.keys.subList(tree.t, 2*tree.t-1)), new ArrayList<>(this.children.subList(tree.t, 2*tree.t)));
        for (BTreeNode node: this.children.subList(tree.t, 2*tree.t))
            if (node != null)
                node.setParent(rightNode);

        parent.addKeyToNotFullNode(this.keys.get(tree.t-1), leftNode, rightNode);

        if (this.parent == null)
            tree.setRoot(parent);

        if (key < leftNode.getKey(leftNode.getN()-1))
            return leftNode;
        else
            return rightNode;
    }


    public void deleteKey(Integer key, BTree tree) {
        int idx;
        for (idx = 0; idx < this.n; idx++) {
            if (this.keys.get(idx) >= key)
                break;
        }

        BTreeNode child = this.children.get(idx);

        if (idx >= this.n) {
            if (!this.isLeaf)
                child.deleteKey(key, tree);
        }
        else if (this.keys.get(idx) == key) {
            if (this.isLeaf) {
                this.keys.remove(idx);
                this.children.remove(idx);
                this.n--;
                return;
            }
            else {
                if (idx < this.n) {
                    child = this.children.get(idx + 1);
                    Integer subtreeMin = child.findMinInSubtree();
                    this.keys.set(idx, subtreeMin);
                    child.deleteKey(subtreeMin, tree);
                }
                else if (idx > 0) {
                    child = this.children.get(idx);
                    Integer subtreeMax = child.findMaxInSubtree();
                    this.keys.set(idx, subtreeMax);
                    child.deleteKey(subtreeMax, tree);
                }
            }
        }
        else {
            if (!this.isLeaf)
                child.deleteKey(key, tree);
        }

        if (this.isLeaf)
            return;

        if (child.getN() < (tree.t - 1))
        {
            boolean flag = false;
            if (idx < this.n)
            {
                BTreeNode rightChild = this.children.get(idx+1);
                if (rightChild.getN() > (tree.t - 1)) {
                    BTreeNode rightChildChild = rightChild.getChild(0);
                    rightChild.removeChild(0);
                    if (rightChildChild != null)
                        rightChildChild.setParent(child);

                    Integer savedKey = this.keys.get(idx);
                    this.keys.set(idx, rightChild.getKey(0));
                    rightChild.removeKey(0);


                    child.setKey(child.getN(), savedKey);
                    child.setChild(child.getN(), rightChildChild);

                    flag = true;
                }
            }
            if ((idx > 0) & !flag) {
                BTreeNode leftChild = this.children.get(idx-1);
                if (leftChild.getN() > (tree.t - 1)) {
                    BTreeNode leftChildChild = leftChild.getChild(leftChild.getN());
                    leftChild.removeChild(leftChild.getN());
                    if (leftChildChild != null)
                        leftChildChild.setParent(child);

                    Integer savedKey = this.keys.get(idx - 1);
                    this.keys.set(idx - 1, leftChild.getKey(leftChild.getN() - 1));
                    leftChild.removeKey(leftChild.getN() - 1);

                    child.setKey(child.getN(), savedKey);
                    child.setChild(child.getN(), leftChildChild);

                    flag = true;
                }
            }
            if (!flag & (idx < this.n)) {
                BTreeNode rightChild = this.children.get(idx + 1);

                child.setKey(child.getN(), this.keys.get(idx));
                this.keys.remove(idx);
                this.children.remove(idx + 1);
                this.n--;
                if ((this == tree.getRoot()) & (this.n == 0))
                    tree.setRoot(child);

                child.setChild(child.getN(), rightChild.getChild(0));
                if (child.getChild(child.getN()) != null)
                    child.getChild(child.getN()).setParent(child);

                for (int i = 0; i < rightChild.getN(); i++) {
                    child.setKey(child.getN(), rightChild.getKey(i));
                    child.setChild(child.getN(), rightChild.getChild(i + 1));
                    if (child.getChild(child.getN()) != null)
                        child.getChild(child.getN()).setParent(child);
                }
                flag = true;
            }
            if (!flag & (idx > 0)) {
                BTreeNode leftChild = this.children.get(idx - 1);

                leftChild.setKey(leftChild.getN(), this.keys.get(idx - 1));
                this.keys.remove(idx - 1);
                this.children.remove(idx);
                this.n--;
                if ((this == tree.getRoot()) & (this.n == 0))
                    tree.setRoot(leftChild);
                
                leftChild.setChild(leftChild.getN(), child.getChild(0));
                if (leftChild.getChild(leftChild.getN()) != null)
                    leftChild.getChild(leftChild.getN()).setParent(leftChild);
                
                for (int i = 0; i < child.getN(); i++) {
                    leftChild.setKey(leftChild.getN(), child.getKey(i));
                    leftChild.setChild(leftChild.getN(), child.getChild(i + 1));
                    if (leftChild.getChild(leftChild.getN()) != null)
                        leftChild.getChild(leftChild.getN()).setParent(leftChild);
                }
                flag = true;
            }
        }
    }


    private Integer findMinInSubtree() {
        if (this.isLeaf)
            return this.keys.get(0);
        return this.children.get(0).findMinInSubtree();
    }

    private Integer findMaxInSubtree() {
        if (this.isLeaf)
            return this.keys.get(this.n - 1);
        return this.children.get(this.n).findMaxInSubtree();
    }


    public void collectKeys(List<Integer> list) {
        if (this.isLeaf) {
            for (int idx = 0; idx < this.n; idx++)
                list.add(this.keys.get(idx));
            return;
        }

        for (int idx = 0; idx < (this.n + 1); idx++) {
            this.children.get(idx).collectKeys(list);
            if (idx < this.n)
                list.add(this.keys.get(idx));
        }
    }


    public String toString(int offset) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < offset; i++)
            builder.append(' ');
        builder.append(String.format("BTreeNode (n=%d, keys=%s, isLeaf=%b, parent=%s, ptr=%s)\n", this.n, this.keys.toString(), this.isLeaf, this.parent, this));
        if (!this.isLeaf) {
            for (int idx = 0; idx < this.n+1; idx++)
            {
                builder.append(this.children.get(idx).toString(offset + 3));
            }
        }
        return builder.toString();
    }
}

public class BTree {
    public final int t;
    private BTreeNode root;

    public BTree(int t) {
        this.t = t;
        this.root = new BTreeNode(0, true, null);
    }

    public void setRoot(BTreeNode root) {
        this.root = root;
    }

    public BTreeNode getRoot() {
        return this.root;
    }

    public void insert(Integer key) {
        this.root.insertKey(key, this);
    }

    public void insert(List<Integer> keys) {
        keys.forEach((key) -> this.insert(key));
    }

    public void delete(Integer key) {
        this.root.deleteKey(key, this);
    }

    public void delete(List<Integer> keys) {
        keys.forEach((key) -> this.delete(key));
    }

    public void clear() {
        this.root = new BTreeNode(0, true, null);
    }

    public List<Integer> toList() {
        List<Integer> list = new ArrayList<>();
        this.root.collectKeys(list);
        return list;
    }

    @Override
    public String toString() {
        return String.format("BTree (t=%d, root=%s)\n", this.t, this.root) + this.root.toString(0);
    }
}
