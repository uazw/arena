package io.github.uazw;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AssassinTest {

    @Test
    public void should_assassin_can_equip_when_given_medium_small_weapon() {
        PlayerWIthWeaponAndArmor assassin = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Assassin);
        Weapon smallWeapon = new Weapon("puzzle hummer", 10, WeaponType.SMALL);
        Weapon mediumWeapon = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);

        assassin.equipWeapon(smallWeapon);
        assassin.equipWeapon(mediumWeapon);
    }

    @Test(expected = NotSuiteAbleException.class)
    public void should_assassin_cannot_equip_when_given_large_weapon() {
        PlayerWIthWeaponAndArmor assassin = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Assassin);
        Weapon small = new Weapon("puzzle hummer", 10, WeaponType.LARGE);

        assassin.equipWeapon(small);
    }


    @Test
    public void should_assassin_can_active_weapon_effect_when_given_small() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor assassin = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Assassin);
        Weapon small = new Weapon("puzzle hummer", 10, WeaponType.SMALL);
        small.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        assassin.equipWeapon(small);

        assassin.attack(player);

        assertThat(player.getBlood(), is(100 - 60));
    }

    @Test
    public void should_assassin_cannot_active_weapon_effect_when_given_medium() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor assassin = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Assassin);
        Weapon medium = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);
        medium.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        assassin.equipWeapon(medium);

        assassin.attack(player);

        assertThat(player.getBlood(), is(100 - 20));
    }
}
