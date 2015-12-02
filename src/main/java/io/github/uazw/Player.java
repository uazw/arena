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

    public String attack(Player anotherPlayer) {
        return String.format("%s attack %s, ", name, anotherPlayer.getName()) + anotherPlayer.beAttacked(this);
    }

    public String beAttacked(Player player) {
        blood -= player.getDamage();
        return String.format("%s get damage at %d, the rest blood of %s is %d",
                name, player.getDamage(), name, getBlood());
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
