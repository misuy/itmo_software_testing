package misuy.domainModel;

public class CruxwanUniversityBelt implements Item {
    public void amplifyAttack(Float damage) {
        damage *= 1.5f;
    }

    public void amplifyDefend(Float damage) {
        damage *= 0.75f;
    }

    @Override
    public String toString() {
        return "cruxwan university belt";
    }
}
