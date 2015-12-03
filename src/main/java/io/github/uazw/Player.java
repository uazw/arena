package io.github.uazw;

import io.github.uazw.debuff.DeBuff;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Player {

    protected final String name;
    private final int damage;
    protected int blood;
    private boolean stopAttack = false;
    private BuffStatus buffStatus = new BuffStatus();

    public Player(String name, int blood, int damage) {
        this.name = name;
        this.blood = blood;
        this.damage = damage;
    }

    public boolean isAlive() {
        return blood > 0;
    }

    private String preAttack() {
        return buffStatus.triggerAll(this);
    }

    public void cleanBuff(DeBuff buff) {
        buff.disActive(this);
        buffStatus.remove(buff);
    }

    public String attack(Player anotherPlayer) {
        String attackInfo = "";
        attackInfo += preAttack();
        if (!stopAttack) {
            attackInfo += String.format("%s %s attack %s %s, ", getRole(), name, anotherPlayer.getRole(), anotherPlayer.getName());
            attackInfo += anotherPlayer.beAttacked(damage);
        }
        afterAttack();
        return attackInfo;
    }

    private void afterAttack() {
        buffStatus.removeIfZero(this);
    }

    public String getRole() {
        return "normal people";
    }

    protected String beAttacked(int damage) {
        blood -= actualSufferedDamage(damage);
        return String.format("%s get damage at %d, the rest blood of %s is %d",
                name, actualSufferedDamage(damage), name, getBlood());
    }


    public void realDamage(int damage) {
        blood -= damage;
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


    public void sufferDeBuff(DeBuff debuff) {
        debuff.active(this);
        buffStatus.add(debuff);
    }

    public void notAllowAttack() {
        this.stopAttack = true;
    }

    public void allowAttack() {
        this.stopAttack = false;
    }

    public int restRoundOf(DeBuff deBuff) {
        return buffStatus.restRoundOf(deBuff);
    }

    class BuffStatus {

        private Map<String, Integer> deBuffs = new HashMap<>();
        private Map<String, DeBuff> deBuffMap = new HashMap<>();

        void add(DeBuff deBuff) {
            deBuffs.merge(deBuff.name(), deBuff.activeRound(), (x, y) -> x + y);
            deBuffMap.putIfAbsent(deBuff.name(), deBuff);
        }

        String triggerAll(Player player) {
            final StringBuilder preAttackInfo = new StringBuilder();

            deBuffMap.entrySet().forEach(x -> {
                preAttackInfo.append(x.getValue().trigger(player));
                preAttackInfo.append("\n");
                deBuffs.merge(x.getKey(), 1, (y, z) -> y - z);
            });

            return preAttackInfo.toString();
        }

         void removeIfZero(Player player) {
            deBuffs.entrySet()
                    .stream()
                    .filter(x -> x.getValue() <= 0)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList())
                    .stream().forEach(y -> {
                deBuffs.remove(y);
                player.cleanBuff(deBuffMap.get(y));
                deBuffMap.remove(y);
            });

        }

        void remove(DeBuff deBuff) {
            deBuffs.remove(deBuff);
            deBuffMap.remove(deBuff);
        }

        int restRoundOf(DeBuff deBuff) {
            return deBuffs.get(deBuff.name());
        }
    }
}
