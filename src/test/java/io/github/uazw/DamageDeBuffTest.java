package io.github.uazw;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DamageDeBuffTest {

    @Test
    public void should_player_suffered_fire_beBuff_suffer_damage_when_attack_people() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageDeBuff(10, Element.FIRE), 1);

        player.attack(anotherPlayer);

        assertThat(90, is(player.getBlood()));
    }

    @Test
    public void should_player_stop_suffered_fire_beBuff_suffer_damage_when_count_over() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageDeBuff(10, Element.FIRE), 1);

        player.attack(anotherPlayer);
        player.attack(anotherPlayer);

        assertThat(90, is(player.getBlood()));
    }

    @Test
    public void should_acc_the_deBuff_count_given_the_same_deBuff() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageDeBuff(10, Element.FIRE), 1);
        player.sufferDeBuff(new DamageDeBuff(10, Element.FIRE), 1);

        player.attack(anotherPlayer);
        player.attack(anotherPlayer);
        player.attack(anotherPlayer);

        assertThat(80, is(player.getBlood()));
    }

    @Test
    public void should_return_deBuff_info_when_attack_others() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageDeBuff(10, Element.FIRE), 1);

        String info = player.attack(anotherPlayer);

        assertTrue(info.startsWith("zhansan get fire damage at 10, the rest blood of zhansan is 90"));
    }


}