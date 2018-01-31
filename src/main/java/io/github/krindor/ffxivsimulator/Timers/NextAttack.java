package io.github.krindor.ffxivsimulator.Timers;

import io.github.krindor.ffxivsimulator.Enums.TimerNames;

import java.util.LinkedList;

/**
 * Created by andre on 2017-05-02.
 */
public class NextAttack {


    private LinkedList<AttackType> timers;

    public NextAttack() {
        timers = new LinkedList<>();
    }


    public void addNextAttack(TimerNames type, double time) {
        AttackType attackType = new AttackType(type, time);
        for (AttackType i : timers) {
            if (i.getType().equals(type)) {
                timers.remove(i);
            }
        }
        timers.add(attackType);
        if (type.equals(TimerNames.Cast))
        for (AttackType i: timers){
            switch(i.getType()){
                case AutoAttack: case GCD: case oGCD: case Opener:
                    if (i.getTime() <= time) {
                        i.setTime(time);
                    }
                    break;
            }

        }
    }

    public void timeChange(double change) {
        for (AttackType attackType : timers) {
            attackType.setTime(attackType.getTime() - change);
        }
    }

    public AttackType getNextAttack() {

        return timers.removeFirst();
    }

    public double getNextAttack(TimerNames type) {

        for (AttackType i : timers) {
            if (i.getType().equals(type)) {
                return i.getTime();
            }
        }
        return -1;
    }
}
