package io.github.uazw;

public class Player {

    protected final String name;
    private final int damage;
    protected int blood;
    private DeBuff deBuff;
    private int restDeBuffCount = 0;

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
    }

    public boolean isAlive() {
        return blood > 0;
    }

    private String preAttack() {
        String preAttackInfo = "";
        if (restDeBuffCount > 0) {
             preAttackInfo = deBuff.trigger(this);
            restDeBuffCount -= 1;
        }
        return preAttackInfo;
    }

    public String attack(Player anotherPlayer) {
        return preAttack() + String.format("%s %s attack %s %s, ", getRole(), name,
                anotherPlayer.getRole(), anotherPlayer.getName()) +
                anotherPlayer.beAttacked(damage);
    }

    public String getRole() {
        return "normal people";
    }

    protected String beAttacked(int damage) {
        blood -= actualSufferedDamage(damage);
        return String.format("%s get damage at %d, the rest blood of %s is %d",
                name, actualSufferedDamage(damage), name, getBlood());
    }

    protected int actualSufferedDamage(int damage) {
        return damage;
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


    public void sufferDeBuff(DeBuff debuff, int count) {
        this.deBuff = debuff;
        this.restDeBuffCount += count;
    }
}
