package misuy.domainModel;

public class Lackey extends Fighter {
    public Lackey(String name) {
        super(name, 60f, 5f);
        this.addItem(new LackeySuit());
    }
}
