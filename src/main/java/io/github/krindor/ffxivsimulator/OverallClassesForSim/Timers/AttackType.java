package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-08-09.
 */
public class AttackType {
    private String type;
    private double time;

    public AttackType(String type, double time){
        this.time = time;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
