package io.github.uazw.debuff;

import io.github.uazw.Player;

public class FrozenBuff extends CannotAttackBuff {


    public FrozenBuff(int activeRound) {
        super(3, activeRound);
    }

    @Override
    protected String triggerHelper(Player player) {
        return String.format("%s is frozen, cannot attack", player.getName());
    }

    @Override
    public String name() {
        return "frozen deBuff";
    }
}
