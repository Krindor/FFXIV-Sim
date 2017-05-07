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
}
