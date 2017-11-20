package io.github.krindor.ffxivsimulator.Damage;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;
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

    public double getDamage(int potency, TypeNames damageTypes, TypeNames resistanceType, AllBuffs buffs) {
        TypeNames damageType;

        double generalMultiplier;
        damageType = damageTypes;


        if (damageType.equals(TypeNames.AutoAttack)) {
            generalMultiplier = formulas.getAaMultiplier();
        } else {
            generalMultiplier = (potency / 100.0) * formulas.getMultiplier() * formulas.getPotionMultiplier(buffs.getMultiplier(TypeNames.All, TypeNames.Potion));
        }
        formulas.setCritMultiplier(buffs.getMultiplier(TypeNames.All, TypeNames.Crit));
        double critMultiplier = formulas.getCritMultiplier();

        double damage = generalMultiplier * buffs.getMultiplier(damageType, resistanceType) * critMultiplier;

        return damage;
    }


}
