package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.DamageBuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class DamageOverTime extends DamageBuffs{

    private int potency;

    private double damage;

    private Formulas formulas;

    private double time;

    private String name;

    public DamageOverTime(int potency, StatModel statModel, int jobmod, String name){
        formulas = new Formulas(statModel, jobmod);
        this.potency = potency;
        this.name = name;
    }

    public double getDamage(String job){
        formulas.setCritMultiplier(getCritBuff());


        if (job.equals("Ninja")) {

            damage = formulas.getMultiplier() * (potency / 100.0) * getBloodForBlood() * getHyperCharge() * getTrickAttack() * 1.2 * formulas.getCritMultiplier() * formulas.getSSModifier() * formulas.getPotionMultiplier(getPotion());


        }
        return damage;
    }

    public String getName() {
        return name + " DoT";
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void timeChange(double change){
        time = time - change;
    }
}
