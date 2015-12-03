package io.github.uazw.debuff;

import io.github.uazw.Player;

public class PuzzleDeBuff extends CannotAttackDeBuff {

    public PuzzleDeBuff() {
        super(1, 2);
    }

    @Override
    protected String triggerHelper(Player player) {

     return String.format("%s is puzzled, can't attack, left %d", player.getName(), player.restRoundOf(this) - 1);
    }


}
