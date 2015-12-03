package io.github.uazw.debuff;

import io.github.uazw.Element;
import io.github.uazw.Player;

public class DamageDeBuff implements DeBuff {
    protected final int damage;
    private final Element element;
    private final int activeRound;

    public DamageDeBuff(int damage, Element element, int activeRound) {
        this.damage = damage;
        this.element = element;
        this.activeRound = activeRound;
    }

    @Override
    public String trigger(Player player) {
        player.realDamage(damage);
        return String.format("%s get %s at %d, the rest blood of %s is %d",
                player.getName(), element, damage, player.getName(), player.getBlood());
    }

    @Override
    public int activeRound() {
        return activeRound;
    }

    @Override
    public void disActive(Player player) {

    }

    @Override
    public void active(Player player) {

    }

    @Override
    public String name() {
        return element.toString() + "deBuff";
    }

}
