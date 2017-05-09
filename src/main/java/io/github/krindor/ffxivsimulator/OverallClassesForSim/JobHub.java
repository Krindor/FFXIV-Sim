package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Ninja.NinjaSim.*;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class JobHub {
    private String job;
    public JobHub(String job){this.job = job;}

    public String openerTypeCheck(ArrayList<String> opener, int openerNum){
        switch (job){
            case "Ninja":
                NinjaOpenerCheck ninjaOpenerCheck = new NinjaOpenerCheck();
                return ninjaOpenerCheck.openerTypeCheck(opener, openerNum);
            case "Monk":

        }
        return "NoN";
    }

    public BuffsDebuffs multiplier(BuffsDebuffs timers, BuffsDebuffs state){
        switch (job){
            case "Ninja":
                NinjaMultipliers ninjaMultipliers = new NinjaMultipliers();
                return ninjaMultipliers.multiplier(timers, state);
        }
        return state;
    }

    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack){
        switch (job){
            case "Ninja":
                NinjaCheckDelay ninjaCheckDelay = new NinjaCheckDelay();
                return ninjaCheckDelay.nextAttack(currentTime, nextAttack, specialType, attack);
        }return nextAttack;
    }

    public BuffsDebuffs checkState(BuffsDebuffs state, String specialType, String type2){
        switch (job) {
            case "Ninja":
                NinjaCheckState ninjaCheckState = new NinjaCheckState();
                return ninjaCheckState.checkState(state, specialType, type2);
        }return state;
    }

    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private BuffsDebuffs timers;
    private BuffsDebuffs state;
    private ArrayList<DamageOverTime> dotsArray;

    public void skillUsed(String type, String specialType, int potency, String attack, String type2, BuffsDebuffs timers, BuffsDebuffs state, ArrayList<DamageOverTime> dotsArray){
        switch (job){
            case "Ninja":
                NinjaSkillUsed ninjaSkillUsed = new NinjaSkillUsed();
                ninjaSkillUsed.skillUsed(type, specialType, potency, attack, type2, timers, state, dotsArray);
                this.type = ninjaSkillUsed.getType();
                this.specialType = ninjaSkillUsed.getSpecialType();
                this.potency = ninjaSkillUsed.getPotency();
                this.attack = ninjaSkillUsed.getAttack();
                this.type2 = ninjaSkillUsed.getType2();
                this.timers = ninjaSkillUsed.getTimers();
                this.state = ninjaSkillUsed.getState();
                this.dotsArray = ninjaSkillUsed.getDotsArray();


        }
    }

    public String getType() {
        return type;
    }

    public String getSpecialType() {
        return specialType;
    }

    public int getPotency() {
        return potency;
    }

    public String getAttack() {
        return attack;
    }

    public String getType2() {
        return type2;
    }

    public BuffsDebuffs getTimers() {
        return timers;
    }

    public BuffsDebuffs getState() {
        return state;
    }

    public ArrayList<DamageOverTime> getDotsArray() {
        return dotsArray;
    }
}
