package misuy;

import misuy.btree.BTree;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hii!");
        BTree tree = new BTree(2);
        System.out.println(tree.toString());
        for (int i = 0; i < 10; i++)
            tree.insert(i);
        System.out.println(tree.toString());
        for (int i = 9; i >= 0; i--)
        {
            tree.delete(i);
        }
        /*
        tree.insert(11);
        tree.insert(-1);
        tree.insert(-99);
        */
        System.out.println(tree.toString());
    }
}