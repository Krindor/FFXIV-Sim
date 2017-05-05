package io.github.krindor.ffxivsimulator.OverallClassesForSim;

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


    public Formulas(StatModel statModel, int jobmod){
        this.statModel=statModel;
        multiplier =(statModel.getWeaponDamage()+((218*jobmod/1000)))*(((statModel.getMainStat()*100)/218)/100)*((1+(((statModel.getDetermination()-218)*1000)/(9.3*858)/1000)));
        ssModifier = (((statModel.getSkillSpeed() + statModel.getSpellSpeed()-(2 * 354))/6800)+1);
        potionMultiplier = (statModel.getMainStat() + 154.0)/statModel.getMainStat();
    }

    public double getPotionMultiplier(boolean potion){
        if (potion){
            return potionMultiplier;
        }
        return 1;
    }

    public double getMultiplier(){
        return multiplier;
    }

    public double getCritMultiplier() {
        return critMultiplier;
    }

    public void setCritMultiplier(double critBuff) {
        critMultiplier = 1 + ((statModel.getCriticalHitRating()-354)/(5*858)+0.05 + critBuff) * ((statModel.getCriticalHitRating()-354)/(5*858)+0.45);
    }

    public double getSSModifier() {
        return ssModifier;
    }


}
