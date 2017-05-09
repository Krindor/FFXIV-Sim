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

    public DamageCalculation(String job, Formulas formulas) {

        this.job = job;
        this.formulas = formulas;
    }

    public double getDamage(int potency, String damageType, String resistanceType, BuffsDebuffs buffsDebuffs) {
        double generalMultiplier;
        this.buffsDebuffs = buffsDebuffs;
        this.damageType = damageType;
        double damage = 0;
        classMultiplier();
        if (damageType.equals("Auto-Attack")) {
            generalMultiplier = formulas.getAaMultiplier() * jobTypeMultiplier * classMultiplier;
        }
        else {generalMultiplier = (potency / 100.0) * formulas.getMultiplier() * formulas.getPotionMultiplier(buffsDebuffs.getPotion()) * buffsDebuffs.getTrickAttack() * jobTypeMultiplier * classMultiplier;}

        formulas.setCritMultiplier(buffsDebuffs.getCritBuff());
        double critMultiplier = formulas.getCritMultiplier();

        if (damageType.equals("Physical") || damageType.equals("Auto-Attack")) {
            double generalPhysical = generalMultiplier * buffsDebuffs.getHyperCharge();

            if (resistanceType.equals("Slashing")) {
                double slashingMultiplier = generalPhysical * buffsDebuffs.getDancingEdge();
                if (job.equals("Ninja") && buffsDebuffs.isDuality() && !damageType.equals("Auto-Attack")) {
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

        if (job.equals("Ninja") && (damageType.equals("Physical") || damageType.equals("Auto-Attack"))) {
            classMultiplier = 1.2;
        }


    }
}
