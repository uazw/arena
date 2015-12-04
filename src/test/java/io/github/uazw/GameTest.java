package io.github.uazw;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import java.io.PrintStream;
import java.util.Random;

import static org.mockito.Mockito.*;

public class GameTest {

    InOrder inOrder;
    private Game game;
    private PrintStream output;

    @Before
    public void setUp() throws Exception {
        output = mock(System.out.getClass());
        game = new Game(output);
        inOrder = inOrder(output);
    }

    @Test
    public void should_print_the_loser() {
        Player first = new Player("zhansan", 100, 10);
        Player second = new Player("lisi", 10000, 100);

        game.fight(first, second);

        verify(output, times(1)).println("zhansan is beated");
    }

    @Test
    public void should_print_attack_info_in_order() {
        Player first = new Player("zhansan", 10, 8);
        Player second = new Player("lisi", 20, 9);

        game.fight(first, second);

        inOrder.verify(output, times(1)).println("normal people zhansan attack normal people lisi," +
                " lisi get damage at 8, the rest blood of lisi is 12");
        inOrder.verify(output, times(1)).println("normal people lisi attack normal people zhansan," +
                " zhansan get damage at 9, the rest blood of zhansan is 1");
        inOrder.verify(output, times(1)).println("normal people zhansan attack normal people lisi," +
                " lisi get damage at 8, the rest blood of lisi is 4");
        inOrder.verify(output, times(1)).println("normal people lisi attack normal people zhansan," +
                " zhansan get damage at 9, the rest blood of zhansan is -8");
        inOrder.verify(output, times(1)).println("zhansan is beated");
    }

    @Test
    public void should_soldier_killed_player_when_use_full_force_weapon() {
        Soldier soldier = new Soldier("zhansan", 100, 4);
        Player player = new Player("lisi", 20, 9);
        Weapon weapon = new Weapon("the killed sword", 4);
        weapon.setWeaponEffect(WeaponEffect.FULL_FORCE, 100, new Random());

        soldier.equipWeapon(weapon);

        game.fight(soldier, player);

        inOrder.verify(output, times(1)).println("soldier zhansan attack normal people lisi using the killed sword," +
                " zhansan trigger full force, lisi get damage at 24, the rest blood of lisi is -4");
    }

}
