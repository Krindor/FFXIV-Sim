package io.github.krindor.ffxivsimulator.Timers;

import io.github.krindor.ffxivsimulator.Enums.TimerNames;

/**
 * Created by andre on 2017-08-09.
 */
public class AttackType {
    private TimerNames type;
    private double time;

    public AttackType(TimerNames type, double time) {
        this.time = time;
        this.type = type;
    }

    public TimerNames getType() {
        return type;
    }

    public void setType(TimerNames type) {
        this.type = type;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
