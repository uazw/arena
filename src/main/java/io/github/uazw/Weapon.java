package io.github.uazw;

public class Weapon {

    private final int damage;
    private final String name;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public static Weapon noWeapon() {
        return new Weapon("undefined", 0);
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}
