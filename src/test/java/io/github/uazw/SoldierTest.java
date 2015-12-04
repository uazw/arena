package io.github.uazw;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class SoldierTest {

    @Test
    public void should_damage_of_soldier_is_base_plus_weapon() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("dragon sword", 10);
        soldier.equipWeapon(weapon);

        soldier.attack(player);

        assertThat(80, is(player.getBlood()));
    }

    @Test
    public void should_return_soldier_using_weapon_and_damage_another_suffered() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("dragon sword", 10);
        soldier.equipWeapon(weapon);

        String result = soldier.attack(player);

        assertThat(result, is("soldier zhansan attack normal people lisi using dragon sword, " +
                "lisi get damage at 20, the rest blood of lisi is 80"));
    }


    @Test
    public void should_case_3_time_damage_when_soldier_equip_100_percent_weapon() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("sin dragon sword", 10);
        weapon.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        soldier.equipWeapon(weapon);

        soldier.attack(player);

        assertThat(40, is(player.getBlood()));
    }

    @Test
    public void should_return_3_time_info_when_soldier_equip_100_percent_weapon() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("sin dragon sword", 10);
        weapon.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        soldier.equipWeapon(weapon);

        String info = soldier.attack(player);

        assertEquals(info, "soldier zhansan attack normal people lisi using sin dragon sword," +
                " zhansan trigger full force, lisi get damage at 60, the rest blood of lisi is 40");
    }

    @Test
    public void should_return_buff_info_when_player_suffer_damage_and_buff() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("fire hummer", 10);
        weapon.setWeaponEffect(WeaponEffect.FIRE, 100, new Random());
        soldier.equipWeapon(weapon);

        String info = soldier.attack(player);

        assertEquals("soldier zhansan attack normal people lisi using fire hummer," +
                " lisi get damage at 20, lisi suffer fire damage deBuff, the rest blood of lisi is 80", info);

    }

    @Test
    public void should_return_info_when_soldier_use_puzzle_hummer_attack_player() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("puzzle hummer", 10);
        weapon.setWeaponEffect(WeaponEffect.PUZZLE, 100, new Random());
        soldier.equipWeapon(weapon);

        String info = soldier.attack(player);

        assertEquals("soldier zhansan attack normal people lisi using puzzle hummer," +
                " lisi get damage at 20, lisi suffer puzzle deBuff, the rest blood of lisi is 80", info);

    }

    @Test
    public void should_soldier_can_equip_medium_weapon() {
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);

        soldier.equipWeapon(weapon);
    }

    @Test(expected = NotSuiteAbleException.class)
    public void should_soldier_cannot_equip_large_weapon() {
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("puzzle hummer", 10, WeaponType.LARGE);

        soldier.equipWeapon(weapon);
    }


    @Test(expected = NotSuiteAbleException.class)
    public void should_soldier_cannot_equip_small_weapon() {
        PlayerWIthWeaponAndArmor soldier = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon weapon = new Weapon("puzzle hummer", 10, WeaponType.LARGE);

        soldier.equipWeapon(weapon);
    }

    @Test
    public void should_knight_active_weapon_effect_when_given_medium() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor knight = new PlayerWIthWeaponAndArmor("zhansan", 100, 10);
        Weapon small = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);
        small.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        knight.equipWeapon(small);

        knight.attack(player);

        assertThat(player.getBlood(), is(100 - 60));
    }

}