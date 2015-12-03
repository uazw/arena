package io.github.uazw;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SoldierTest {

    @Test
    public void should_damage_of_soldier_is_base_plus_weapon() {
        Player player = new Player("lisi", 100, 10);
        Soldier soldier = new Soldier("zhansan", 100, 10);
        Weapon weapon = new Weapon("dragon sword", 10);
        soldier.equipWeapon(weapon);

        soldier.attack(player);

        assertThat(80, is(player.getBlood()));
    }

    @Test
    public void should_return_soldier_using_weapon_and_damage_another_suffered() {
        Player player = new Player("lisi", 100, 10);
        Soldier soldier = new Soldier("zhansan", 100, 10);
        Weapon weapon = new Weapon("dragon sword", 10);
        soldier.equipWeapon(weapon);

        String result = soldier.attack(player);

        assertThat(result, is("soldier zhansan attack normal people lisi using dragon sword, " +
                        "lisi get damage at 20, the rest blood of lisi is 80"));
    }



    @Test
    @Ignore
    public void should_case_3_time_damage_when_soldier_equip_100_percent_weapon() {
        Player player = new Player("lisi", 100, 10);
        Soldier soldier = new Soldier("zhansan", 100, 10);
        Weapon weapon = new Weapon("sin dragon sword", 10);
        weapon.setWeaponEffect(WeaponEffect.FULL_FORCE, 100);
        soldier.equipWeapon(weapon);

        soldier.attack(player);

        assertThat(40, is(player.getBlood()));
    }
}