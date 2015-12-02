package io.github.uazw;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SoldierTest {

    @Test
    public void should_damage_of_soldier_is_base_plus_weapon() {
        Soldier soldier = new Soldier("zhansan", 100, 10);
        Weapon weapon = new Weapon("dragon sword", 10);
        Player player = new Player("lisi", 100, 10);
        soldier.equipWeapon(weapon);

        String info = soldier.attack(player);

        assertThat(info, is("soldier zhansan attack normal people lisi using dragon sword," +
                " lisi get damage at 20, the rest blood of lisi is 80"));
    }


}