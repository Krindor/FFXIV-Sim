package io.github.krindor.ffxivsimulator.JSON.SkillDB;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;

/**
 * Created by andre on 2017-07-13.
 */
public class Skills {
    private String name;
    private int potency;
    private String type;
    private String type2;
    private TypeNames types;
    private TypeNames type2s;
    private double delayOffset;
    private String buff;
    private int buffTime;
    private int dotPotency;
    private int dotTime;
    private boolean hasBuff;
    private boolean hasDoT;

    public boolean hasBuff() {
        return hasBuff;
    }

    public boolean hasDoT() {
        return hasDoT;
    }

    public String getName() {
        return name;
    }

    public int getPotency() {
        return potency;
    }

    public TypeNames getTypes() {
        return types;
    }

    public TypeNames getType2s() {
        return type2s;
    }

    public boolean isHasBuff() {
        return hasBuff;
    }

    public boolean isHasDoT() {
        return hasDoT;
    }

    public String getBuff() {
        return buff;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public int getDotPotency() {
        return dotPotency;
    }

    public int getDotTime() {
        return dotTime;
    }

    public void stringToEnum() {
        types = TypeNames.valueOf(type);
        type2s = TypeNames.valueOf(type2);
    }

    public double getDelayOffset() {
        return delayOffset;
    }
}
