package misuy.domainModel;

public class LackeySuit implements Item {
    public void amplifyAttack(Float damage) {
        damage *= 1.25f;
    }

    public void amplifyDefend(Float damage) {
        damage *= 0.5f;
    }

    @Override
    public String toString() {
        return "lackey suit";
    }
}
