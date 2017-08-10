package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-07.
 */
public class Attacks extends OtherBuffs {
    private double jugulate;

    private double dreamWithinADream;

    private double mug;

    private double shoulderTackle;

    private double howlingFist;

    private double steelPeak;

    private double elixirField;

    public Attacks() {
        jugulate = 0;
        dreamWithinADream = 0;
        mug = 0;
        shoulderTackle = 0;
        howlingFist = 0;
        steelPeak = 0;
        elixirField = 0;
    }

    public void timeChangeAttacks(double change) {
        jugulate = jugulate - change;
        dreamWithinADream = dreamWithinADream - change;
        mug = mug - change;
        shoulderTackle = shoulderTackle - change;
        howlingFist = howlingFist - change;
        steelPeak = steelPeak - change;
        elixirField = elixirField - change;

    }

    public double getShoulderTackle() {
        return shoulderTackle;
    }

    public void setShoulderTackle(double shoulderTackle) {
        this.shoulderTackle = shoulderTackle;
    }

    public double getHowlingFist() {
        return howlingFist;
    }

    public void setHowlingFist(double howlingFist) {
        this.howlingFist = howlingFist;
    }

    public double getSteelPeak() {
        return steelPeak;
    }

    public void setSteelPeak(double steelPeak) {
        this.steelPeak = steelPeak;
    }

    public double getElixirField() {
        return elixirField;
    }

    public void setElixirField(double elixirField) {
        this.elixirField = elixirField;
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
