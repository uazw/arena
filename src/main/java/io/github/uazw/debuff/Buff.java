package io.github.uazw.debuff;

import io.github.uazw.Player;

public interface Buff {

    String trigger(Player player);

    int activeRound();

    void disActive(Player player);

    void active(Player player);

    String name();
}
