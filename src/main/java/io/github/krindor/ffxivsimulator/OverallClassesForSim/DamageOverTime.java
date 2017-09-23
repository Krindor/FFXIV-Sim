package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class DamageOverTime {

    private int potency;

    private double damage;

    private Formulas formulas;

    private double time;

    private String name;

    private AllBuffs allBuffs;

    public DamageOverTime(int potency, StatModel statModel, int jobmod, String name, AllBuffs allBuffs) {
        formulas = new Formulas(statModel, jobmod);
        this.potency = potency;
        this.name = name;
        this.allBuffs = allBuffs;
    }

    public double getDamage(String job) {
        /*Type1 = All Type2 = Crit */
        formulas.setCritMultiplier(allBuffs.getMultiplier("All", "Crit"));


        damage = formulas.getMultiplier() * (potency / 100.0) * allBuffs.getMultiplier("Physical", "All") * formulas.getCritMultiplier() * formulas.getSSModifier() * formulas.getPotionMultiplier(allBuffs.getMultiplier("All", "Potion"));


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

    public void timeChange(double change) {
        time = time - change;
    }


}
