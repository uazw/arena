package io.github.uazw;

import io.github.uazw.debuff.Buff;

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

    public void cleanBuff(Buff buff) {
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

    protected String beAttacked(SpecialDamage damage) {
        blood -= actualSufferedDamage(damage.value());
        return String.format("%s get damage at %d,%s the rest blood of %s is %d",
                name, actualSufferedDamage(damage.value()), sufferDeBuff(damage.deBuff()), name, getBlood());

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


    public String sufferDeBuff(Buff deBuff) {
        if (deBuff != null) {
            deBuff.active(this);
            buffStatus.add(deBuff);
            return String.format(" %s suffer %s,", name, deBuff.name());
        }
        return "";
    }

    public void notAllowAttack() {
        this.stopAttack = true;
    }

    public void allowAttack() {
        this.stopAttack = false;
    }

    public int restRoundOf(Buff buff) {
        return buffStatus.restRoundOf(buff);
    }

    class BuffStatus {

        private Map<String, Integer> deBuffs = new HashMap<>();
        private Map<String, Buff> deBuffMap = new HashMap<>();

        void add(Buff buff) {
            deBuffs.merge(buff.name(), buff.activeRound(), (x, y) -> x + y);
            deBuffMap.putIfAbsent(buff.name(), buff);
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

        void remove(Buff buff) {
            deBuffs.remove(buff);
            deBuffMap.remove(buff);
        }

        int restRoundOf(Buff buff) {
            return deBuffs.get(buff.name());
        }
    }
}
