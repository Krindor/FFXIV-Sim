package io.github.krindor.ffxivsimulator.Monk.MonkSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

/**
 * Created by andre on 2017-05-09.
 */
public class MonkCheckDelay {

    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack) {


        if (nextAttack.getNextGCD() < 0.5) {
            nextAttack.setNextGCD(0.7);
            nextAttack.setNextOGCD(0.7);
        } else nextAttack.setNextOGCD(0.7);


    return nextAttack;
}
}
