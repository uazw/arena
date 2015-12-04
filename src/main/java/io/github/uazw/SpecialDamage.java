package io.github.uazw;

import io.github.uazw.debuff.Buff;

public class SpecialDamage {

    private final int value;
    private final Buff buff;
    private String info = "";

    public SpecialDamage(int value, Buff buff) {
        this.value = value;
        this.buff = buff;
    }

    public int value() {
        return value;
    }

    public Buff deBuff() {
        return buff;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

