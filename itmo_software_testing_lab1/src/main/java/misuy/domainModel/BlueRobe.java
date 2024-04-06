package misuy.domainModel;

public class BlueRobe implements Item {
    public void amplifyAttack(Float damage) {
    }

    public void amplifyDefend(Float damage) {
        damage *= 0.75f;
    }

    @Override
    public String toString() {
        return "blue robe";
    }
}
