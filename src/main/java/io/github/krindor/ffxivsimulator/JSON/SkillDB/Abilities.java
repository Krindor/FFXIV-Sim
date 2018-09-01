package io.github.krindor.ffxivsimulator.JSON.SkillDB;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;

/**
 * Created by andre on 2017-07-14.
 */
public class Abilities {
    private String name;
    private int cooldown;
    private int potency;
    private String buff;
    private TypeNames type;
    private TypeNames type2;
    private int specialValue;
    private boolean hasBuffs;
    private double delayOffset;
    private double castTime;
    private int dotPotency;
    private int dotTime;

    public double getCastTime() {
        return castTime;
    }

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

    public TypeNames getType() {
        return type;
    }

    public void setType(TypeNames type) {
        this.type = type;
    }

    public TypeNames getType2() {
        return type2;
    }

    public void setType2(TypeNames type2) {
        this.type2 = type2;
    }

    public int getDotPotency() {
        return dotPotency;
    }

    public int getDotTime() {
        return dotTime;
    }

    public int getSpecialValue() {
        return specialValue;
    }

    public double getDelayOffset() {
        return delayOffset;
    }
}
