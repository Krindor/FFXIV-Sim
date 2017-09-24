package io.github.krindor.ffxivsimulator.Damage;

import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

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

    public double getDamage(int potency, String damageTypes, String resistanceType, AllBuffs buffs) {
        String damageType;

        double generalMultiplier;
        damageType = damageTypes;


        if (damageType.equals("Auto-Attack")) {
            generalMultiplier = formulas.getAaMultiplier();
        } else {
            generalMultiplier = (potency / 100.0) * formulas.getMultiplier() * formulas.getPotionMultiplier(buffs.getMultiplier("All", "Potion"));
        }
        formulas.setCritMultiplier(buffs.getMultiplier("All", "Crit"));
        double critMultiplier = formulas.getCritMultiplier();

        double damage = generalMultiplier * buffs.getMultiplier(damageType, resistanceType) * critMultiplier;

        return damage;
    }


}
