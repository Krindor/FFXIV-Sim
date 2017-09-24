package io.github.krindor.ffxivsimulator.JSON.SkillDB;

import io.github.krindor.ffxivsimulator.Timers.BuffBarNames;

/**
 * Created by andre on 2017-07-09.
 */
public class Buffs {
    String name;
    double duration;
    String type;
    double increase;
    String target;
    BuffBarNames targetEnum;
    String type2;
    int maxStacks;

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public double getIncrease() {
        return increase;
    }

    public String getTarget() {
        return target;
    }

    public String getType2() {
        return type2;
    }

    public void timeChange(double change) {
        duration = duration - change;
        if (duration < 0){
            duration = 0;
        }
    }

    public int getMaxStacks() {
        return maxStacks;
    }

    public void setTargetEnum(){
        targetEnum = BuffBarNames.valueOf(target);
    }

    public BuffBarNames getTargetEnum() {
        return targetEnum;
    }

    public void setName(String name) {
        this.name = name;
    }


}
