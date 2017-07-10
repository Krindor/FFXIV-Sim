package io.github.krindor.ffxivsimulator.Dragoon.DragoonSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

/**
 * Created by andre on 2017-05-09.
 */
public class DragoonCheckDelay {

    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack) {


        if (nextAttack.getNextGCD() < 0.5) {
            nextAttack.setNextGCD(0.7);
            nextAttack.setNextOGCD(0.7);
        } else nextAttack.setNextOGCD(0.7);


    return nextAttack;
}
}
