package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

/**
 * Created by andre on 2017-05-02.
 */
public class Resistance {
    private double dragonKick;


    private double dancingEdge;


    private double hyperCharge;


    private double trickAttack;

    public Resistance(){
        dragonKick = 1;
        dancingEdge = 1;
        hyperCharge = 1;
        trickAttack = 1;

    }

    protected void resistanceTimer(){
        dragonKick = 0;
        dancingEdge = 0;
        hyperCharge = 0;
        trickAttack = 0;
    }

    public void timeChangeResistance(double change){
        dragonKick = dragonKick - change;
        dancingEdge = dancingEdge - change;
        hyperCharge = hyperCharge - change;
        trickAttack = trickAttack - change;
    }

    public double getDragonKick() {
        return dragonKick;
    }

    public void setDragonKick(double dragonKick) {
        this.dragonKick = dragonKick;
    }

    public double getDancingEdge() {
        return dancingEdge;
    }

    public void setDancingEdge(double dancingEdge) {
        this.dancingEdge = dancingEdge;
    }

    public double getHyperCharge() {
        return hyperCharge;
    }

    public void setHyperCharge(double hyperCharge) {
        this.hyperCharge = hyperCharge;
    }

    public double getTrickAttack() {
        return trickAttack;
    }

    public void setTrickAttack(double trickAttack) {
        this.trickAttack = trickAttack;
    }
}
