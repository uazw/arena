package io.github.uazw.debuff;

import io.github.uazw.Player;

public abstract class CannotAttackDeBuff implements DeBuff {

    private final int step;
    private final int activeRound;
    private int passRound;

    public CannotAttackDeBuff(int step, int activeRounds) {
        this.step = step;
        this.activeRound = activeRounds;
    }

    @Override
    public String trigger(Player player) {
        String info = "";
        if (passRound % step == 0) {
            player.notAllowAttack();
            info = triggerHelper(player);
        } else {
            player.allowAttack();
        }
        passRound++;
        return info;
    }

    protected abstract String triggerHelper(Player player);

    @Override
    public int activeRound() {
        return activeRound;
    }

    @Override
    public void disActive(Player player) {
        player.allowAttack();
    }

    @Override
    public void active(Player player) {
        player.notAllowAttack();
    }

    @Override
    public String name() {
        return getClass().getSimpleName();
    }
}
