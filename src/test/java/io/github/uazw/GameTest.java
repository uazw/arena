package io.github.uazw;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GameTest {

    @Test
    public void should_print_every_attack_in_order() {

    }

    @Test
    public void should_print_the_loser() {
        Player first = new Player("zhansan", 100, 10);
        Player second = new Player("lisi", 10000, 100);
        Game game = new Game(first, second);

        String result = game.fight();

        assertThat(result, is("zhansan is beated"));
    }
}
