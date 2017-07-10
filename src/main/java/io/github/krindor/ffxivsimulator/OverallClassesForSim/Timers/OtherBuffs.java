package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-02.
 */
public class OtherBuffs extends Stances{
    private boolean kassatsu;

    private double kassatsuTime;

    private boolean duality;

    private double dualityTime;

    private double perfectBalance;

    private boolean suiton;

    private double suitonTime;

    private double mudra;

    private boolean bootshine;

    private boolean lifeSurge;

    private double lifeSurgeTime;

    private boolean powerSurge;

    private double powerSurgeTime;

    public OtherBuffs(){
        kassatsuTime = 0;
        dualityTime = 0;
        suitonTime = 0;
        perfectBalance = 0;
    }

    public void timeChangeOther(double change){
        kassatsuTime = kassatsuTime - change;
        dualityTime = dualityTime - change;
        perfectBalance = perfectBalance - change;
        suitonTime = suitonTime - change;
        mudra = mudra - change;
    }


    public boolean isPowerSurge() {
        return powerSurge;
    }

    public void setPowerSurge(boolean powerSurge) {
        this.powerSurge = powerSurge;
    }

    public double getPowerSurgeTime() {
        return powerSurgeTime;
    }

    public void setPowerSurgeTime(double powerSurgeTime) {
        this.powerSurgeTime = powerSurgeTime;
    }

    public boolean isLifeSurge() {
        return lifeSurge;
    }

    public void setLifeSurge(boolean lifeSurge) {
        this.lifeSurge = lifeSurge;
    }

    public double getLifeSurgeTime() {
        return lifeSurgeTime;
    }

    public void setLifeSurgeTime(double lifeSurgeTime) {
        this.lifeSurgeTime = lifeSurgeTime;
    }

    public double getMudra() {
        return mudra;
    }

    public void setMudra(double mudra) {
        this.mudra = mudra;
    }

    public boolean isKassatsu() {
        return kassatsu;
    }

    public void setKassatsu(boolean kassatsu) {
        this.kassatsu = kassatsu;
    }

    public double getKassatsuTime() {
        return kassatsuTime;
    }

    public void setKassatsuTime(double kassatsuTime) {
        this.kassatsuTime = kassatsuTime;
    }

    public boolean isDuality() {
        return duality;
    }

    public void setDuality(boolean duality) {
        this.duality = duality;
    }

    public double getDualityTime() {
        return dualityTime;
    }

    public void setDualityTime(double dualityTime) {
        this.dualityTime = dualityTime;
    }

    public double getPerfectBalance() {
        return perfectBalance;
    }

    public void setPerfectBalance(double perfectBalance) {
        this.perfectBalance = perfectBalance;
    }

    public boolean isSuiton() {
        return suiton;
    }

    public void setSuiton(boolean suiton) {
        this.suiton = suiton;
    }

    public double getSuitonTime() {
        return suitonTime;
    }

    public void setSuitonTime(double suitonTime) {
        this.suitonTime = suitonTime;
    }

    public boolean isBootshine() {
        return bootshine;
    }

    public void setBootshine(boolean bootshine) {
        this.bootshine = bootshine;
    }
}
