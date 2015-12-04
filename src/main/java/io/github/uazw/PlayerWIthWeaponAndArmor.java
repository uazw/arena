package io.github.uazw;

public class PlayerWIthWeaponAndArmor extends Player implements WeaponEquipped, ArmorWearable {

    private Armor armor = Armor.noArmor();
    private Weapon weapon = Weapon.noWeapon();
    private Role role = Role.SOLDIER;

    public PlayerWIthWeaponAndArmor(String name, int blood, int damage) {
        super(name, blood, damage);
    }

    public PlayerWIthWeaponAndArmor(String name, int blood, int damage, Role role) {
        super(name, blood, damage);
        this.role = role;
    }

    @Override
    public String getRole() {
        return role.name().toLowerCase();
    }

    @Override
    protected int actualSufferedDamage(int damage) {
        int actualDamage = damage - armor.getDefend();
        return actualDamage > 0 ? actualDamage : 0;
    }

    @Override
    public String attack(Player anotherPlayer) {
        String info = String.format("%s %s attack %s %s using %s, ", getRole(), name,
                anotherPlayer.getRole(), anotherPlayer.getName(), weapon.getName());
        if (isSuitable(weapon)) {
            SpecialDamage damage = weapon.beUsed(getDamage());
            info += String.format(damage.getInfo(), name);
            info += anotherPlayer.beAttacked(damage);
        } else {
            info += anotherPlayer.beAttacked(getDamage() + weapon.getDamage());

        }

        return info;
    }

    public void equipWeapon(Weapon weapon) {
        if (isEquipped(weapon)) {
            this.weapon = weapon;
        } else {
            throw new NotSuiteAbleException();
        }
    }

    @Override
    public boolean isEquipped(Weapon weapon) {
        return role.isEquipped(weapon);
    }

    @Override
    public boolean isSuitable(Weapon weapon) {
        return role.isSuitable(weapon);
    }

    @Override
    public void wearArmor(Armor armor) {
        this.armor = armor;
    }

}
