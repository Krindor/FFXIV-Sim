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


        generalMultiplier = Math.floor((potency) * formulas.getMultiplier()) * formulas.getPotionMultiplier(buffs.getMultiplier(TypeNames.All, TypeNames.Potion));
        formulas.setDirectHitMultiplier(buffs.getMultiplier(TypeNames.All, TypeNames.DirectHit));
        formulas.setCritMultiplier(buffs.getMultiplier(TypeNames.All, TypeNames.Crit));
        double dhCritMultiplier = formulas.getDhCritMultiplier();

        double damage = generalMultiplier * buffs.getMultiplier(damageType, resistanceType) * dhCritMultiplier;

        return damage;
    }


}
