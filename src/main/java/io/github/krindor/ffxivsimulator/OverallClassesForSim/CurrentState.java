package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Damage.DamageOverTime;
import io.github.krindor.ffxivsimulator.JobClasses.Resources;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

import java.util.ArrayList;

public class CurrentState {
    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private AllBuffs buffs;
    private ArrayList<DamageOverTime> dotsArray;
    private Resources resources;

    public CurrentState(String type, String specialType, int potency, String attack, String type2, AllBuffs buffs, ArrayList<DamageOverTime> dotsArray, Resources resources) {
        this.type = type;
        this.specialType = specialType;
        this.potency = potency;
        this.attack = attack;
        this.type2 = type2;
        this.buffs = buffs;
        this.dotsArray = dotsArray;
        this.resources = resources;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialType() {
        return specialType;
    }

    public void setSpecialType(String specialType) {
        this.specialType = specialType;
    }

    public int getPotency() {
        return potency;
    }

    public void setPotency(int potency) {
        this.potency = potency;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public AllBuffs getBuffs() {
        return buffs;
    }

    public void setBuffs(AllBuffs buffs) {
        this.buffs = buffs;
    }

    public ArrayList<DamageOverTime> getDotsArray() {
        return dotsArray;
    }

    public void setDotsArray(ArrayList<DamageOverTime> dotsArray) {
        this.dotsArray = dotsArray;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
