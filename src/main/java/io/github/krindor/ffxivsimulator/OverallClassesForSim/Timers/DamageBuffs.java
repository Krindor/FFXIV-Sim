package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-02.
 */
public class DamageBuffs extends Resistance{
    private double bloodForBlood;

    private double internalRelease;

    private boolean potion;

    private double potionTime;

    private double twinSnakes;

    private double battleLitany;

    private double heavyThrust;

    public DamageBuffs(){
        bloodForBlood = 1;
        internalRelease = 0;
        potion = false;
        twinSnakes = 1;
        battleLitany = 0;
    }

    protected void damageTimer(){
        bloodForBlood = 0;

        twinSnakes = 0;

    }

    public void timeChangeDamage(double change){
        bloodForBlood = bloodForBlood - change;
        internalRelease = internalRelease - change;
        twinSnakes = twinSnakes - change;
        battleLitany = battleLitany - change;
        potionTime = potionTime - change;
        heavyThrust = heavyThrust - change;
    }

    public double getHeavyThrust() {
        return heavyThrust;
    }

    public void setHeavyThrust(double heavyThrust) {
        this.heavyThrust = heavyThrust;
    }

    public void setBattleLitany(double battleLitany) {
        this.battleLitany = battleLitany;
    }

    public double getBloodForBlood() {
        return bloodForBlood;
    }

    public void setBloodForBlood(double bloodForBlood) {
        this.bloodForBlood = bloodForBlood;
    }

    public double getInternalRelease() {
        return internalRelease;
    }


    public double getBattleLitany() {
        return battleLitany;
    }

    public double getPotionTime() {
        return potionTime;
    }

    public void setPotionTime(double potionTime) {
        this.potionTime = potionTime;
    }

    public void setInternalRelease(double internalRelease) {
        this.internalRelease = internalRelease;
    }

    public boolean getPotion() {
        return potion;
    }

    public void setPotion(boolean potion) {
        this.potion = potion;
    }

    public double getTwinSnakes() {
        return twinSnakes;
    }

    public void setTwinSnakes(double twinSnakes) {
        this.twinSnakes = twinSnakes;
    }

    public double getCritBuff(){
        return battleLitany + internalRelease;
    }
}
