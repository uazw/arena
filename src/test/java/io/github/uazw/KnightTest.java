package io.github.uazw;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnightTest {

    @Test
    public void should_knight_can_equip_when_given_medium_large_weapon() {
        PlayerWIthWeaponAndArmor knight = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Knight);
        Weapon largeWeapon = new Weapon("puzzle hummer", 10, WeaponType.LARGE);
        Weapon mediumWeapon = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);

        knight.equipWeapon(largeWeapon);
        knight.equipWeapon(mediumWeapon);
    }

    @Test(expected = NotSuiteAbleException.class)
    public void should_knight_cannot_equip_when_given_small_weapon() {
        PlayerWIthWeaponAndArmor knight = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Knight);
        Weapon small = new Weapon("puzzle hummer", 10, WeaponType.SMALL);

        knight.equipWeapon(small);
    }


    @Test
    public void should_knight_can_active_weapon_effect_when_given_large() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor knight = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Knight);
        Weapon large = new Weapon("puzzle hummer", 10, WeaponType.LARGE);
        large.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        knight.equipWeapon(large);

        knight.attack(player);

        assertThat(player.getBlood(), is(100 - 60));
    }

    @Test
    public void should_knight_cannot_active_weapon_effect_when_given_medium() {
        Player player = new Player("lisi", 100, 10);
        PlayerWIthWeaponAndArmor knight = new PlayerWIthWeaponAndArmor("zhansan", 100, 10, Role.Knight);
        Weapon medium = new Weapon("puzzle hummer", 10, WeaponType.MEDIUM);
        medium.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());
        knight.equipWeapon(medium);

        knight.attack(player);

        assertThat(player.getBlood(), is(100 - 20));
    }
}
