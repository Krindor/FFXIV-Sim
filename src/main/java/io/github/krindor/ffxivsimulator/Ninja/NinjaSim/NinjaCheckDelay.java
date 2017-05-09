package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaCheckDelay {

    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack) {

    if(currentTime ==0&&attack.equals("Suiton"))

    {
        nextAttack.setNextGCD(0.7);
        nextAttack.setNextOGCD(0.7);
    } else if(nextAttack.getNextGCD() < 1.7&&specialType.equals("2-step"))

    {
        nextAttack.setNextGCD(1.7);
        nextAttack.setNextOGCD(1.7);
    } else if(nextAttack.getNextGCD() < 1.2&&specialType.equals("1-step"))

    {
        nextAttack.setNextGCD(1.2);
        nextAttack.setNextOGCD(1.2);
    } else if(nextAttack.getNextGCD() < 2.2&&specialType.equals("3-step"))

    {
        nextAttack.setNextGCD(2.2);
        nextAttack.setNextOGCD(2.2);
    } else

    {
        if (nextAttack.getNextGCD() < 0.5) {
            nextAttack.setNextGCD(0.7);
            nextAttack.setNextOGCD(0.7);
        } else nextAttack.setNextOGCD(0.7);

    }
    return nextAttack;
}
}
