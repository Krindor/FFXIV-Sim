package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-07.
 */
public class DamageCalculation {
    private BuffsDebuffs buffsDebuffs;
    private String job;
    private Formulas formulas;
    private double classMultiplier;
    private double jobTypeMultiplier;
    private String damageType;

    public DamageCalculation(String job, StatModel statModel) {

        this.job = job;
        formulas = new Formulas(statModel, 100);
    }

    public double getDamage(int potency, String damageType, String resistanceType, BuffsDebuffs buffsDebuffs) {

        this.buffsDebuffs = buffsDebuffs;
        this.damageType = damageType;
        double damage = 0;
        classMultiplier();
        double generalMultiplier = (potency / 100.0) * formulas.getMultiplier() * formulas.getPotionMultiplier(buffsDebuffs.getPotion()) * buffsDebuffs.getTrickAttack() * jobTypeMultiplier * classMultiplier;

        formulas.setCritMultiplier(buffsDebuffs.getCritBuff());
        double critMultiplier = formulas.getCritMultiplier();

        if (damageType.equals("Physical")) {
            double generalPhysical = generalMultiplier * buffsDebuffs.getHyperCharge();

            if (resistanceType.equals("Slashing")) {
                double slashingMultiplier = generalPhysical * buffsDebuffs.getDancingEdge();
                if (job.equals("Ninja") && buffsDebuffs.isDuality()) {
                    damage = slashingMultiplier * 2;
                } else damage = slashingMultiplier * critMultiplier;
            }
        }

        if (damageType.equals("Magical")) {
            double generalMagical = generalMultiplier;
            if (buffsDebuffs.isKassatsu()) {
                damage = generalMagical * formulas.getCritDamage();
            }
        }


        return damage;
    }

    private void classMultiplier() {
        jobTypeMultiplier = 1;
        if (job.equals("Ninja") || job.equals("Monk")) {
            jobTypeMultiplier = buffsDebuffs.getBloodForBlood();
        }

        if (job.equals("Ninja") && damageType.equals("Physical")) {
            classMultiplier = 1.2;
        }


    }
}
