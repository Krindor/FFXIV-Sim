package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaCheckDelay {

    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack) {

    if(currentTime ==0&&attack.equals("Suiton"))

    {
        nextAttack.addNextAttack("GCD" ,0.7);
        nextAttack.addNextAttack("oGCD",0.7);
    } else if(nextAttack.getNextAttack("GCD") < 1.7&&specialType.equals("2-step"))

    {
        nextAttack.addNextAttack("GCD" ,1.7);
        nextAttack.addNextAttack("oGCD",1.7);
    } else if(nextAttack.getNextAttack("GCD") < 1.2&&specialType.equals("1-step"))

    {
        nextAttack.addNextAttack("GCD" ,1.2);
        nextAttack.addNextAttack("oGCD",1.2);
    } else if(nextAttack.getNextAttack("GCD") < 2.2&&specialType.equals("3-step"))

    {
        nextAttack.addNextAttack("GCD" ,2.2);
        nextAttack.addNextAttack("oGCD",2.2);
    } else

    {
        if (nextAttack.getNextAttack("GCD") < 0.5) {
            nextAttack.addNextAttack("GCD" ,0.7);
            nextAttack.addNextAttack("oGCD",0.7);
        } else nextAttack.addNextAttack("oGCD",0.7);

    }
    return nextAttack;
}
}
