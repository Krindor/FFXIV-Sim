package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

import java.util.ArrayList;

/**
 * Created by andre on 2017-07-13.
 */
public class JobSkillUsed {
    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private BuffsDebuffs timers;
    private BuffsDebuffs state;
    private BuffBar buffBar;

    private ArrayList<DamageOverTime> dotsArray;

    public void skillUsed(String type, String specialType, int potency, String attack, String type2, BuffsDebuffs timers, BuffsDebuffs state, ArrayList<DamageOverTime> dotsArray)

    {
        this.attack = attack;
        this.dotsArray = dotsArray;
        this.potency = potency;

        this.specialType = specialType;
        this.state = state;
        this.timers = timers;
        this.type = type;
        this.type2 = type2;
        useSkill();
    }
}
