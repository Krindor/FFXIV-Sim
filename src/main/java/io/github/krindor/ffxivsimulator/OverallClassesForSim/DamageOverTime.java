package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.BuffDebuffState.DamageBuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class DamageOverTime extends DamageBuffs{

    private int potency;

    private double damage;

    private Formulas formulas;

    public DamageOverTime(int potency, StatModel statModel, int jobmod){
        formulas = new Formulas(statModel, jobmod);
        this.potency = potency;
    }

    public double getDamage(){
        damage = (formulas.getMultiplier()*(potency/10)*getBloodForBlood()*getHyperCharge());
        return damage;
    }
}
