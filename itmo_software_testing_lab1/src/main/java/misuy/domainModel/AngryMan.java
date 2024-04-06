package misuy.domainModel;

public class AngryMan extends Fighter {
    public AngryMan(String name) {
        super("Angry man (" + name + ")", 100f, 10f);
        this.addItem(new BlueRobe());
        this.addItem(new CruxwanUniversityBelt());
    }
}
