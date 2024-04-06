package misuy.domainModel;

import java.util.ArrayList;
import java.util.List;

public abstract class Fighter {
    private String name;
    private Float hp;
    private Float damage;
    private List<Item> items;

    public Fighter(String name, Float hp, Float damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.items = new ArrayList<>();
    }

    public Fighter(String name, Float hp, Float damage, List<Item> items) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
        this.items = items;
    }

    public String getName() {
        return this.name;
    }

    public Float getHp() {
        return this.hp;
    }

    public Float getDamage() {
        return this.damage;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public boolean isDead() {
        return this.hp <= 0;
    }

    public void attack(Fighter enemy) {
        if (this.isDead() | (enemy == null))
            return;
        
        Float damageCopy = Float.valueOf(this.damage);
        System.out.println(String.format("%s attacks %s with %f damage", this.name, enemy.getName(), damageCopy));
        this.items.stream().forEach((item) -> item.amplifyAttack(damageCopy));
        enemy.defend(damageCopy);
    }

    public void defend(Float damage) {
        Float damageCopy = Float.valueOf(damage);
        this.items.stream().forEach((item) -> item.amplifyDefend(damageCopy));
        this.hp -= damageCopy;
        if (this.hp < 0)
            this.hp = 0f;
        
        System.out.println(String.format("%s got %f, current hp: %f", this.name, damageCopy, this.hp));
        if (this.isDead())
            System.out.println(String.format("%s is dead", this.name));
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        return String.format("Fighter (name: %s, damage: %f, hp: %f, items: %s)", this.name, this.damage, this.hp, this.items.toString());
    }
}
