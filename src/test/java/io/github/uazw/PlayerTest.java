package io.github.uazw;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class PlayerTest {

    @Test
    public void should_player_not_alive_when_blood_less_equal_zero() {
        Player player = new Player("zhanSan", -2, 1);

        boolean alive = player.isAlive();

        assertThat(alive, is(false));
    }

    @Test
    public void should_player_not_alive_when_blood_equal_zero() {
        Player player = new Player("lisi", 0, 1);

        boolean alive = player.isAlive();

        assertThat(alive, is(false));

    }

    @Test
    public void should_player_alive_when_blood_greater_than_zero() {
        Player player = new Player("zhanSan", 100, 1);

        boolean alive = player.isAlive();

        assertThat(alive, is(true));
    }

    @Test
    public void should_blood_become_less_when_someone_attack() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 100, 10);
        player.attack(anotherPlayer);

        int restBlood = anotherPlayer.getBlood();

        assertThat(restBlood, is(100 - player.getDamage()));
    }

    @Test
    public void should_return_attack_info_when_one_attack_another() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 100, 10);

        String info = player.attack(anotherPlayer);

        assertEquals("normal people zhansan attack normal people lisi," +
                " lisi get damage at 10, the rest blood of lisi is 90", info);
    }

    @Test
    public void should_return_person_attack_soldier_info() {
        Player player = new Player("zhansan", 100, 10);
        Soldier soldier = new Soldier("lisi", 100, 10);

        String info = player.attack(soldier);

        assertEquals("normal people zhansan attack soldier lisi," +
                " lisi get damage at 10, the rest blood of lisi is 90", info);

    }

    @Test
    public void should_soldier_suffer_weaken_damage_when_person_attack_soldier_who_wear_armor() {
        Player player = new Player("zhansan", 100, 10);
        Soldier soldier = new Soldier("lisi", 100, 10);
        Armor armor = new Armor("e...", 5);
        soldier.wearArmor(armor);

        String info = player.attack(soldier);

        assertEquals("normal people zhansan attack soldier lisi," +
                " lisi get damage at 5, the rest blood of lisi is 95", info);
    }

    @Test
    public void should_soldier_suffer_zero_damage_when_the_attack_less_than_soldier_defend() {
        Player player = new Player("zhansan", 100, 10);
        Soldier soldier = new Soldier("lisi", 100, 10);
        Armor armor = new Armor("e...", 11);
        soldier.wearArmor(armor);

        String info = player.attack(soldier);

        assertEquals("normal people zhansan attack soldier lisi," +
                " lisi get damage at 0, the rest blood of lisi is 100", info);

    }
}
