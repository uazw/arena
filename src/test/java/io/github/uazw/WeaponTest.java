package io.github.uazw;

import io.github.uazw.debuff.FrozenBuff;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class WeaponTest {

    private Weapon weapon;

    @Before
    public void setUp() throws Exception {
        weapon = new Weapon("knife", 10);

    }

    @Test
    public void should_return_base_damage_given_none_weapon_effect() {

        SpecialDamage damage = weapon.beUsed(10);

        assertEquals(20, damage.value());
        assertNull(damage.deBuff());
    }

    @Test
    public void should_three_times_damage_given_full_force_weapon_effect() {
        weapon.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());

        SpecialDamage damage = weapon.beUsed(10);

        assertThat(60, is(damage.value()));
        assertNull(damage.deBuff());
    }

    @Test
    public void should_not_damage_plus_given_puzzle_weapon_effect() {
        weapon.setWeaponEffect(WeaponEffect.FROZEN, 100, new Random());

        SpecialDamage damage = weapon.beUsed(10);

        assertThat(20, is(damage.value()));
        assertTrue(damage.deBuff().activeRound() > 0);
        assertThat(damage.deBuff(), is(FrozenBuff.class));
    }

    @Test
    public void should_not_trigger_effect_when_percent_is_zero() {
        weapon.setWeaponEffect(WeaponEffect.FROZEN, 0, new Random());

        SpecialDamage damage = weapon.beUsed(10);

        assertThat(20, is(damage.value()));
        assertNull(damage.deBuff());

    }

    @Test
    public void should_not_trigger_effect_when_not_luck() {
        Random mock = mock(Random.class);
        when(mock.nextInt(10)).thenReturn(5);
        when(mock.nextInt(100)).thenReturn(50);

        weapon.setWeaponEffect(WeaponEffect.FIRE, 51, new Random());

        SpecialDamage damage = weapon.beUsed(10);

        assertNull(damage.deBuff());
    }
}