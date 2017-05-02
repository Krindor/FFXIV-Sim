package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by andre on 2017-05-02.
 */
public class NextAttack {
    private double nextAA;

    private double nextDoT;

    private double nextGCD;

    private double nextoGCD;

    private ArrayList<Double> timers;

    private NextAttack(){
        timers = new ArrayList<>(4);

        timers.add(nextAA);
        timers.add(nextDoT);
        timers.add(nextGCD);
        timers.add(nextoGCD);
    }

    public double getNextAA() {
        return nextAA;
    }

    public void setNextAA(double nextAA) {
        this.nextAA = nextAA;
    }

    public double getNextDoT() {
        return nextDoT;
    }

    public void setNextDoT(double nextDoT) {
        this.nextDoT = nextDoT;
    }

    public double getNextGCD() {
        return nextGCD;
    }

    public void setNextGCD(double nextGCD) {
        this.nextGCD = nextGCD;
    }

    public double getNextOGCD() {
        return nextoGCD;
    }

    public void setNextOGCD(double nextOGCD) {
        this.nextoGCD = nextOGCD;
    }

    public double getNextAttack(){
        timers.set(0, nextGCD);
        timers.set(1, nextoGCD);
        timers.set(2, nextAA);
        timers.set(3, nextDoT);

        return Collections.min(timers);
    }
}
