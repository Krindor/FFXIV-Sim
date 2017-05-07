package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-07.
 */
public class BuffsDebuffs extends Attacks{
    public BuffsDebuffs(){}

    public BuffsDebuffs(int timer){
        resistanceTimer();
        damageTimer();
    }

    public void timeChange(double change){
        timeChangeAttacks(change);
        timeChangeOther(change);
        timeChangeResistance(change);
        timeChangeStances(change);
        timeChangeDamage(change);

    }
}
