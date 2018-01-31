package io.github.krindor.ffxivsimulator.Damage;

import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class Formulas {
    private final StatModel statModel;
    private double multiplier;
    private double critMultiplier;
    private double ssModifier;
    private double potionMultiplier;
    private double critDamage;
    private double directHitChance;
    private double directHitMultiplier;



    public Formulas(StatModel statModel) {
        this.statModel = statModel;

        multiplier = (statModel.getWeaponDamage() + Math.floor(292 * statModel.getJobMod() / 1000)) * (100 + Math.floor((statModel.getMainStat() - 292) * 1000 / 2336)) / 100;

        multiplier = Math.floor(multiplier * (1000 + Math.floor(130 * (statModel.getDetermination() - 292) / 2170)));

        ssModifier = (((statModel.getSkillSpeed() + statModel.getSpellSpeed() - (2 * 354)) / 6800) + 1);

        potionMultiplier = (statModel.getMainStat() + 154.0) / statModel.getMainStat();

        critDamage = (1000 + Math.floor(200 * (statModel.getCriticalHitRating() - 364) / 2170 + 400)) / 1000;

        directHitChance = Math.floor(550 * (statModel.getDirectHit() - 364) / 2170) / 1000;
    }

    public double getPotionMultiplier(double potion) {
        if (potion == 0) {
            return potionMultiplier;
        }
        return 1;
    }

    public double getAaRecast(AllBuffs buffs) {
        double speedChange = buffs.getMultiplier(TypeNames.Haste, TypeNames.All);
        return Math.floor((2.56 * speedChange) * 100) / 100;
    }

    public double getRecast(double recastTime, AllBuffs buffs) {
        double speedChange = buffs.getMultiplier(TypeNames.Haste, TypeNames.All);
        return Math.floor((Math.floor(recastTime * Math.floor(1000 - (((statModel.getSkillSpeed() - 364) * 130)/2170)))/10000) * speedChange)/100;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setCritMultiplier(double critBuff) {
        if (critBuff >= 1) {
            critMultiplier = 1 + critDamage;
        } else {
            critMultiplier = 1 + (((statModel.getCriticalHitRating() - 354.0) / (5 * 858) + 0.05 + critBuff) * critDamage);
        }
    }

    public void setDirectHitMultiplier(double dhBuff) {
        if (dhBuff >= 1) {
            directHitMultiplier = 1 + 0.25;
        } else {
            directHitMultiplier = 1 + ((directHitChance + dhBuff) * 0.25);
        }
    }

    public double getDhCritMultiplier() {
        return directHitMultiplier * critMultiplier;
    }


    public double getSSModifier() {
        return ssModifier;
    }


}
