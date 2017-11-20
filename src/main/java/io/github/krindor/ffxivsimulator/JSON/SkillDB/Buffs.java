package io.github.krindor.ffxivsimulator.JSON.SkillDB;

import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;

/**
 * Created by andre on 2017-07-09.
 */
public class Buffs {
    private String name;
    private double duration;
    private String type;
    private double increase;
    private String target;
    private BuffBarNames targetEnum;
    private String type2;
    private TypeNames types;
    private TypeNames type2s;
    private int maxStacks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }


    public double getIncrease() {
        return increase;
    }

    public String getTarget() {
        return target;
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

    public void timeChange(double change) {
        duration = duration - change;
        if (duration < 0) {
            duration = 0;
        }
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void stringToEnum() {
        targetEnum = BuffBarNames.valueOf(target);
        types = TypeNames.valueOf(type);
        type2s = TypeNames.valueOf(type2);
    }

    public BuffBarNames getTargetEnum() {
        return targetEnum;
    }


}
