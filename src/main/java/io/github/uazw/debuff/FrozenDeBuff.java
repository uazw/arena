package io.github.uazw.debuff;

import io.github.uazw.Player;
import io.github.uazw.debuff.CannotAttackDeBuff;

public class FrozenDeBuff extends CannotAttackDeBuff {


    public FrozenDeBuff(int activeRound) {
        super(3, activeRound);
    }

    @Override
    protected String triggerHelper(Player player) {
        return String.format("%s is frozen, cannot attack", player.getName());
    }

}
