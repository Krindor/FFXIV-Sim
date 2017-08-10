package io.github.krindor.ffxivsimulator.OverallClassesForSim;

/**
 * Created by andre on 2017-06-20.
 */
public class Resources {
    private int tacticalPoints;
    private int manaPoints;
    private int classSpecific;
    private int totalMana;
    private int totalTactical;
    private int totalClass;

    public int getTacticalPoints() {
        return tacticalPoints;
    }

    public void setTacticalPoints(int tacticalPoints) {
        this.tacticalPoints = tacticalPoints;
    }

    public int getManaPoints() {
        return manaPoints;
    }

    public void setManaPoints(int manaPoints) {
        this.manaPoints = manaPoints;
    }

    public int getClassSpecific() {
        return classSpecific;
    }

    public void setClassSpecific(int classSpecific) {
        this.classSpecific = classSpecific;
    }

    public void manaChange(int change) {
        manaPoints = manaPoints + change;
    }

    public void tacticalChange(int change) {
        tacticalPoints = tacticalPoints + change;
    }

    public void classChange(int change) {
        classSpecific = classSpecific + change;
    }

    public int getTotalMana() {
        return totalMana;
    }

    public void setTotalMana(int totalMana) {
        this.totalMana = totalMana;
    }

    public int getTotalTactical() {
        return totalTactical;
    }

    public void setTotalTactical(int totalTactical) {
        this.totalTactical = totalTactical;
    }

    public int getTotalClass() {
        return totalClass;
    }

    public void setTotalClass(int totalClass) {
        this.totalClass = totalClass;
    }
}
