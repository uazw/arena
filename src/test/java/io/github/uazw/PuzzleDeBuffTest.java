package io.github.uazw;

import io.github.uazw.debuff.PuzzleDeBuff;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class PuzzleDeBuffTest {

    @Test
    public void should_people_cannot_attack_when_suffer_Puzzle() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 100, 10);
        anotherPlayer = spy(anotherPlayer);
        player.sufferDeBuff(new PuzzleDeBuff());

        player.attack(anotherPlayer);
        player.attack(anotherPlayer);
        player.attack(anotherPlayer);

        verify(anotherPlayer, times(1)).beAttacked(anyInt());
    }

    @Test
    public void should_print_puzzle_special_info_when_player_be_puzzled() {
        Player player = new Player("zhansan", 100, 10);
        Player anotherPlayer = new Player("lisi", 100, 10);
        anotherPlayer = spy(anotherPlayer);
        player.sufferDeBuff(new PuzzleDeBuff());

        String info = player.attack(anotherPlayer);

        assertEquals("zhansan is puzzled, can't attack, left 1\n", info);
    }
}