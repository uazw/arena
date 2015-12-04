package io.github.uazw;

import io.github.uazw.debuff.FrozenBuff;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class FrozenBuffTest {

    private Player player;
    private Player another;

    @Before
    public void setUp() throws Exception {
        player = new Player("lisi", 1, 1);
        another = new Player("zhansan", 1, 1);
        player.sufferDeBuff(new FrozenBuff(6));
    }

    @Test
    public void should_player_cannot_attack_when_frozen_active() {
        another = spy(another);

        player.attack(another);
        player.attack(another);
        player.attack(another);
        player.attack(another);

        verify(another, times(2)).beAttacked(anyInt());
    }
}
