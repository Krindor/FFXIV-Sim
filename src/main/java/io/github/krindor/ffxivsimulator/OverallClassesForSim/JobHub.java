package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Monk.MonkSim.MonkCheckDelay;
import io.github.krindor.ffxivsimulator.Ninja.NinjaSim.NinjaCheckDelay;
import io.github.krindor.ffxivsimulator.Ninja.Priority.Rotation;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class JobHub {
    private String job;

    public JobHub(String job) {
        this.job = job;
    }


    public NextAttack nextAttack(double currentTime, NextAttack nextAttack, String specialType, String attack) {
        switch (job) {
            case "Ninja":
                NinjaCheckDelay ninjaCheckDelay = new NinjaCheckDelay();
                return ninjaCheckDelay.nextAttack(currentTime, nextAttack, specialType, attack);
            case "Monk":
                MonkCheckDelay monkCheckDelay = new MonkCheckDelay();
                return monkCheckDelay.nextAttack(currentTime, nextAttack, specialType, attack);
        }
        return nextAttack;
    }


    public String getNextGCD(ArrayList<Double> timers, String prevSkill, AllBuffs buffs, Resources resources) {
        switch (job) {
            case "Ninja":
                Rotation ninjaRotation = new Rotation();
                return ninjaRotation.getNextGCD(timers, prevSkill, buffs, resources);
            case "Monk":
                io.github.krindor.ffxivsimulator.Monk.Priority.Rotation monkRotation = new io.github.krindor.ffxivsimulator.Monk.Priority.Rotation();
                return monkRotation.getNextGCD(timers, prevSkill, buffs, resources);
        }
        return "NoN";
    }

    public String getNextOGCD(ArrayList<Double> timers, AllBuffs buffs, Resources resources) {
        switch (job) {
            case "Ninja":
                Rotation ninjaRotation = new Rotation();
                return ninjaRotation.getNextOGCD(timers, buffs, resources);
            case "Monk":
                io.github.krindor.ffxivsimulator.Monk.Priority.Rotation monkRotation = new io.github.krindor.ffxivsimulator.Monk.Priority.Rotation();
                return monkRotation.getNextOGCD(timers, buffs, resources);
        }
        return "NoN";
    }


}
