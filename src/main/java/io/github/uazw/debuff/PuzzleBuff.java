package io.github.uazw.debuff;

import io.github.uazw.Player;

public class PuzzleBuff extends CannotAttackBuff {

    public PuzzleBuff() {
        super(1, 2);
    }

    @Override
    protected String triggerHelper(Player player) {

     return String.format("%s is puzzled, can't attack, left %d", player.getName(), player.restRoundOf(this) - 1);
    }

    @Override
    public String name() {
        return "puzzle deBuff";
    }
}
