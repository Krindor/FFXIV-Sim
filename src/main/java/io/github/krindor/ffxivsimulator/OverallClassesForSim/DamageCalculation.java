package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-07.
 */
public class DamageCalculation {

    private String job;
    private Formulas formulas;


    public DamageCalculation(String job, Formulas formulas) {

        this.job = job;
        this.formulas = formulas;
    }

    public double getDamage(int potency, String damageTypes, String resistanceType, BuffBar buffBar) {
        String damageType;

        double generalMultiplier;
        damageType = damageTypes;



        if (damageType.equals("Auto-Attack")) {
            generalMultiplier = formulas.getAaMultiplier();
        } else {
            generalMultiplier = (potency / 100.0) * formulas.getMultiplier() * formulas.getPotionMultiplier(buffBar.getMultiplier("All", "Potion"));
        }
        formulas.setCritMultiplier(buffBar.getMultiplier("All", "Crit"));
        double critMultiplier = formulas.getCritMultiplier();

        double damage = generalMultiplier * buffBar.getMultiplier(damageType, resistanceType) * critMultiplier;

        return damage;
    }


}
