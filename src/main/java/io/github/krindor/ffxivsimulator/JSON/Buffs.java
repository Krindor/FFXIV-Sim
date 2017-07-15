package io.github.krindor.ffxivsimulator.JSON;

/**
 * Created by andre on 2017-07-09.
 */
public class Buffs {
    String name;
    double duration;
    String type;
    double increase;
    String target;
    String type2;
    int maxStacks;

    public String getName() {
        return name;
    }

    public double getDuration() {
        return duration;
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

    public void timeChange(double change){
        duration = duration - change;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getMaxStacks() {
        return maxStacks;
    }

}
