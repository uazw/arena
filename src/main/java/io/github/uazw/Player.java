package io.github.uazw;

public class Player {

    private final String name;
    private int blood;
    private final int damage;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
    }

    public boolean isAlive() {
        return blood > 0;
    }

    public void attack(Player anotherPlayer) {
        anotherPlayer.beAttacked(this);
    }

    public void beAttacked(Player player) {
        blood -= player.getDamage();
    }

    public int getBlood() {
        return this.blood;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }
}
