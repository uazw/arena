package io.github.uazw;

public class DamageDeBuff implements DeBuff {
    protected final int damage;
    private final Element element;

    public DamageDeBuff(int damage, Element element) {
        this.damage = damage;
        this.element = element;
    }

    @Override
    public String trigger(Player player) {
        player.blood -= damage;
        return String.format("%s get %s at %d, the rest blood of %s is %d",
                player.getName(), element, damage, player.getName(), player.getBlood());
    }
}
