package io.github.uazw;

public class Game {

    private final Player second;
    private final Player first;

    public Game(Player first, Player second) {
        this.first = first;
        this.second = second;
    }

    public String fight() {
        while (second.isAlive() && first.isAlive()) {
            first.attack(second);
            if (!second.isAlive()) break;
            second.attack(first);
            if (!first.isAlive()) break;
        }

        return String.format("%s is beated", first.isAlive()? second.getName(): first.getName());
    }
}
