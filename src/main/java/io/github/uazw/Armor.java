package io.github.uazw;

public class Armor {

    private final int defend;
    private final String name;

    public Armor(String name, int defend) {
        this.name = name;
        this.defend = defend;
    }

    public int getDefend() {
        return defend;
    }

    public static Armor noArmor() {
        return new Armor("undefined", 0);
    }

}
