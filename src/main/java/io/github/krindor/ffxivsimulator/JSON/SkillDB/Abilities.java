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
    private String type;
    private String type2;
    private TypeNames types;
    private TypeNames type2s;
    private int specialValue;
    private boolean hasBuffs;
    private double delayOffset;
    private double castTime;

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

    public TypeNames getTypes() {
        return types;
    }

    public void setTypes(TypeNames types) {
        this.types = types;
    }

    public TypeNames getType2s() {
        return type2s;
    }

    public void setType2s(TypeNames type2s) {
        this.type2s = type2s;
    }

    public void stringToEnum() {
        types = TypeNames.valueOf(type);
        type2s = TypeNames.valueOf(type2);
    }

    public int getSpecialValue() {
        return specialValue;
    }

    public double getDelayOffset() {
        return delayOffset;
    }
}
