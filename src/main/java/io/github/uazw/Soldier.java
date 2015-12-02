package io.github.uazw;

public class Soldier extends Player {

    private Armor armor = Armor.noArmor();

    public Soldier(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    @Override
    public String getRole() {
        return "soldier";
    }

    public void wearArmor(Armor armor) {
        this.armor = armor;
    }

    @Override
    protected int actualDamage(int damage) {
        int actualDamage = damage - armor.getDefend();
        return actualDamage > 0? actualDamage: 0;
    }
}
