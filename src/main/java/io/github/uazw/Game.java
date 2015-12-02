package io.github.uazw;

import java.io.PrintStream;

public class Game {

    private final PrintStream output;

    public Game(PrintStream output) {
        this.output = output;
    }

    public void fight(Player first, Player second) {
        while (second.isAlive() && first.isAlive()) {
            output.println(first.attack(second));
            if (!second.isAlive()) break;
            output.println(second.attack(first));
        }

        output.println(String.format("%s is beated", first.isAlive()? second.getName(): first.getName()));
    }
}
