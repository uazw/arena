package io.github.uazw;

import io.github.uazw.debuff.DamageBuff;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DamageBuffTest {

    @Test
    public void should_player_suffered_fire_beBuff_suffer_damage_when_attack_people() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageBuff(10, Element.FIRE, 1));

        player.attack(anotherPlayer);

        assertThat(90, is(player.getBlood()));
    }

    @Test
    public void should_player_stop_suffered_fire_beBuff_suffer_damage_when_count_over() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageBuff(10, Element.FIRE, 1));

        player.attack(anotherPlayer);
        player.attack(anotherPlayer);

        assertThat(90, is(player.getBlood()));
    }

    @Test
    public void should_acc_the_deBuff_count_given_the_same_deBuff() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageBuff(10, Element.FIRE, 1));
        player.sufferDeBuff(new DamageBuff(10, Element.FIRE, 1));

        player.attack(anotherPlayer);
        player.attack(anotherPlayer);
        player.attack(anotherPlayer);

        assertThat(80, is(player.getBlood()));
    }

    @Test
    public void should_return_deBuff_info_when_attack_others() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 1000, 10);
        player.sufferDeBuff(new DamageBuff(10, Element.FIRE, 1));

        String info = player.attack(anotherPlayer);

        assertThat(info, is("zhansan get fire damage at 10, the rest blood of zhansan is 90\n" +
                "normal people zhansan attack normal people lisi," +
                " lisi get damage at 10, the rest blood of lisi is 990"));
    }


}