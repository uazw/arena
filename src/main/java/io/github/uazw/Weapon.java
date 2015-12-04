package io.github.uazw;

import io.github.uazw.debuff.DamageBuff;
import io.github.uazw.debuff.FrozenBuff;
import io.github.uazw.debuff.PuzzleBuff;

import java.util.Random;

public class Weapon {

    private final int damage;
    private final String name;
    private int percent = 100;
    private WeaponEffect weaponEffect = WeaponEffect.NONE;
    private Random random;

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

    public void setWeaponEffect(WeaponEffect weaponEffect, int percent, Random random) {
        this.weaponEffect = weaponEffect;
        this.percent = percent;
        this.random = random;
    }

    public SpecialDamage beUsed(int base) {
        if (weaponEffect == WeaponEffect.NONE) {
            return getSpecialDamage(base + damage, WeaponEffect.NONE);
        } else if (isEffectActive()) {
            return getSpecialDamage(base + damage, weaponEffect);
        } else {
            return getSpecialDamage(base + damage, WeaponEffect.NONE);

        }
    }

    private SpecialDamage getSpecialDamage(int base, WeaponEffect weaponEffect) {
        SpecialDamage specialDamage;
        switch (weaponEffect) {
            case FULL_FORCE:
                specialDamage = new SpecialDamage(base * 3, null);
                specialDamage.setInfo("%s trigger full force, ");
                break;
            case FIRE:
                specialDamage = new SpecialDamage(base,
                        new DamageBuff(random.nextInt(damage - 1) + 1, Element.FIRE, random.nextInt(9) + 1));
                break;
            case FROZEN:
                specialDamage = new SpecialDamage((base),
                        new FrozenBuff(random.nextInt(9) + 1));
                break;
            case POISON:
                specialDamage = new SpecialDamage((base),
                        new DamageBuff(random.nextInt(damage - 1) + 1, Element.POISON, random.nextInt(9) + 1));
                break;
            case PUZZLE:
                specialDamage = new SpecialDamage((base),
                        new PuzzleBuff());
                break;
            default:
                specialDamage =
                        new SpecialDamage(base, null);
        }
        return specialDamage;
    }


    private boolean isEffectActive() {
        random = new Random();
        int randomNumber = random.nextInt(100);
        if (this.percent > randomNumber) {
            return true;
        } else {
            return false;
        }
    }
}
