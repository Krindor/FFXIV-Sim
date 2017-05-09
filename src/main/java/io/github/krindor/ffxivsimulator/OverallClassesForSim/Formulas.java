package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class Formulas{
    private double multiplier;
    private final StatModel statModel;
    private double critMultiplier;
    private double ssModifier;
    private double potionMultiplier;
    private double critDamage;
    private double recast;
    private double aaRecast;
    private double aaMultiplier;

    public Formulas(StatModel statModel, int jobmod){
        this.statModel=statModel;

        multiplier =(statModel.getWeaponDamage()+((218*jobmod/1000.0)))*(((statModel.getMainStat()*100.0)/218)/100)*((1+(((statModel.getDetermination()-218.0)*1000)/(9.3*858)/1000)));

        aaMultiplier = (statModel.getWeaponDamage() / (3 / 2.56) * 0.0593365489928915 + 1) * (statModel.getMainStat() * 0.0841892) * ( statModel.getDetermination() / 6832.8 + 1);

        ssModifier = (((statModel.getSkillSpeed() + statModel.getSpellSpeed()-(2 * 354))/6800)+1);

        potionMultiplier = (statModel.getMainStat() + 154.0)/statModel.getMainStat();

        critDamage = ((statModel.getCriticalHitRating()-354.0)/(5*858)+0.45);

        recast = Math.floor(((Math.floor((1 - ((Math.floor((((double) this.statModel.getSkillSpeed() - 354) * 0.13 / 858) * 1000) / 1000))) * (2.5) * 100) / 100) * 0.85) * 100) / 100;

        aaRecast = Math.floor((2.56 * 0.85) * 100) / 100;
    }

    public double getPotionMultiplier(boolean potion){
        if (potion){
            return potionMultiplier;
        }
        return 1;
    }

    public double getAaMultiplier() {
        return aaMultiplier;
    }

    public void changeRecast(BuffsDebuffs buffsDebuffs){
        double speedChange = 1 - (buffsDebuffs.getGlStacks()*0.05) - buffsDebuffs.getHutonTime();
        recast = Math.floor(((Math.floor((1 - ((Math.floor((((double) statModel.getSkillSpeed() - 354) * 0.13 / 858) * 1000) / 1000))) * (2.5) * 100) / 100) * speedChange) * 100) / 100;
        aaRecast = Math.floor((2.56 * speedChange) * 100) / 100;
    }

    public double getRecast() {
        return recast;
    }

    public double getAaRecast() {
        return aaRecast;
    }

    public double getMultiplier(){
        return multiplier;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double critBuff) {
        critMultiplier = 1 + (((statModel.getCriticalHitRating()-354.0)/(5*858)+0.05 + critBuff) * critDamage);
    }

    public double getCritDamage() {
        return 1+critDamage;
    }

    public double getSSModifier() {
        return ssModifier;
    }


}
