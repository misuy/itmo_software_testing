package misuy;

import misuy.btree.BTree;
import misuy.domainModel.AngryMan;
import misuy.domainModel.Fight;
import misuy.domainModel.Lackey;
import misuy.domainModel.Team;
import misuy.series.ArctgSeries;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hii!");
        BTree tree = new BTree(2);
        System.out.println(tree.toString());
        for (int i = 0; i < 7; i++)
            tree.insert(i);
        System.out.println(tree.toString());
        System.out.println(tree.toList());
        for (int i = 8; i >= 0; i--)
        {
            tree.delete(i);
        }
        tree.insert(11);
        tree.insert(-1);
        tree.insert(-99);

        /*
        Team red = new Team("angry people");
        red.addFighter(new AngryMan("Paul"));
        red.addFighter(new AngryMan("Ryan"));

        Team blue = new Team("lackeys");
        blue.addFighter(new Lackey("Jack"));
        blue.addFighter(new Lackey("Alan"));
        System.out.println(blue.toString());

        Fight fight = new Fight(red, blue);
        fight.simulate();
        */
    }
}