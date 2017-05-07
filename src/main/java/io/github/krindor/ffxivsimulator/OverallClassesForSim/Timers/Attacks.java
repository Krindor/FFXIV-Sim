package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-07.
 */
public class Attacks extends OtherBuffs{
    private double jugulate;

    private double dreamWithinADream;

    private double mug;

    public Attacks(){
        jugulate = 0;
        dreamWithinADream = 0;
        mug = 0;
    }

    public void timeChangeAttacks(double change){
        jugulate = jugulate - change;
        dreamWithinADream = dreamWithinADream - change;
        mug = mug - change;
    }

    public double getJugulate() {
        return jugulate;
    }

    public void setJugulate(double jugulate) {
        this.jugulate = jugulate;
    }

    public double getDreamWithinADream() {
        return dreamWithinADream;
    }

    public void setDreamWithinADream(double dreamWithinADream) {
        this.dreamWithinADream = dreamWithinADream;
    }

    public double getMug() {
        return mug;
    }

    public void setMug(double mug) {
        this.mug = mug;
    }
}
