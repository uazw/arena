package io.github.uazw;

public class Soldier extends Player {

    private Armor armor = Armor.noArmor();
    private Weapon weapon = Weapon.noWeapon();

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
    protected int actualSufferedDamage(int damage) {
        int actualDamage = damage - armor.getDefend();
        return actualDamage > 0? actualDamage: 0;
    }

    @Override
    public String attack(Player anotherPlayer) {
        return String.format("%s %s attack %s %s using %s, ", getRole(), name,
                anotherPlayer.getRole(), anotherPlayer.getName(), weapon.getName()) +
                anotherPlayer.beAttacked(getDamage() + weapon.getDamage());
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}
