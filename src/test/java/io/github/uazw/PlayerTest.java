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

        assertEquals("zhansan attack lisi, lisi get damage at 10, the rest blood of lisi is 90", info);
    }
}
