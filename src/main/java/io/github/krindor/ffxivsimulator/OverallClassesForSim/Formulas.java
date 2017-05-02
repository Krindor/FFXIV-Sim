package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.model.StatModel;

/**
 * Created by andre on 2017-05-02.
 */
public class Formulas{
    private double multiplier;
    private final StatModel statModel;

    public Formulas(StatModel statModel, int jobmod){
        this.statModel=statModel;
        multiplier =(statModel.getWeaponDamage()+((218*jobmod/1000)))*(((statModel.getMainStat()*100)/218)/100)*((1+((statModel.getDetermination()-218)*1000)/(9.3*858)));
    }

    public double getMultiplier(){
        return multiplier;
    }

}
