package io.github.krindor.ffxivsimulator.JSON.SkillDB;

/**
 * Created by andre on 2017-07-14.
 */
public class Abilities {
    String name;
    int cooldown;
    int potency;
    String buff;
    String type;
    String type2;
    int specialValue;
    boolean hasBuffs;

    public boolean hasBuffs() {
        return hasBuffs;
    }

    public String getName() {
        return name;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getPotency() {
        return potency;
    }

    public String getBuff() {
        return buff;
    }

    public String getType() {
        return type;
    }

    public String getType2() {
        return type2;
    }

    public int getSpecialValue() {
        return specialValue;
    }
}
