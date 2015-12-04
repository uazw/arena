package io.github.uazw;

public interface WeaponEquipped {
    void equipWeapon(Weapon weapon);
    boolean isEquipped(Weapon weapon);
    boolean isSuitable(Weapon weapon);
}
