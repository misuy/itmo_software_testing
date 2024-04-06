package misuy.domainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Team {
    private String name;
    private List<Fighter> fighters;

    public Team(String name) {
        this.name = name;
        this.fighters = new ArrayList<>();
    }

    public Team(String name, List<Fighter> fighters) {
        this.name = name;
        this.fighters = fighters;
    }

    public void addFighter(Fighter fighter) {
        this.fighters.add(fighter);
    }

    public void attack(Team enemy) {
        if (enemy == null)
            return;

        System.out.println(String.format("team %s attacks team %s", this.name, enemy.name));
        this.fighters.forEach((fighter) -> fighter.attack(enemy.randomVictim()));
    }

    public boolean isDead() {
        return this.fighters.stream().filter((fighter) -> !fighter.isDead()).count() == 0;
    }

    public Fighter randomVictim() {
        Random rnd = new Random(47);
        List<Fighter> victims = this.fighters.stream().filter((fighter) -> !fighter.isDead()).toList();
        if (victims.isEmpty())
            return null;
        return victims.get(rnd.nextInt(victims.size()));
    }

    @Override
    public String toString() {
        return String.format("Team (name: %s, fighters: %s)", this.name, this.fighters.toString());
    }
}
