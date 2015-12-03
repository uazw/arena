package io.github.uazw;

public class Weapon {

    private final int damage;
    private final String name;
    private int percent;
    private WeaponEffect weaponEffect;

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

    public void setWeaponEffect(WeaponEffect weaponEffect, int percent) {
        this.weaponEffect = weaponEffect;
        this.percent = percent;
    }

}
