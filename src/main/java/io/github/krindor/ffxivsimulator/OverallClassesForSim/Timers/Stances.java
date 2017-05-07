package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-04-30.
 */
public class Stances extends DamageBuffs{
    private double hutonTime;
    private double glStacks;
    private double chakraStacks;
    private double form;
    private double glTime;



    public void timeChangeStances(double change){
        hutonTime = hutonTime - change;
        glTime = glTime - change;
    }


    public Stances(){
        hutonTime = 0;
        glTime = 0;
    }

    public void setGlTime(double glTime){
        this.glTime = glTime;
    }

    public void setHutonTime(double hutonTime){
        this.hutonTime = hutonTime;
    }

    public void setGlStacks(double glStacks){
        this.glStacks = glStacks;
    }

    public void setChakraStacks(double chakraStacks){
        this.chakraStacks = chakraStacks;
    }

    public void setForm(double form){
        this.form = form;
    }

    public double getGlTime(){
        return glTime;
    }

    public double getHutonTime(){
        return hutonTime;
    }

    public double getGlStacks(){
        return glStacks;
    }

    public double getChakraStacks(){
        return chakraStacks;
    }

    public double getForm(){
        return form;
    }


}
