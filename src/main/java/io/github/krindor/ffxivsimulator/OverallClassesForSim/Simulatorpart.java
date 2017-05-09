package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Monk;
import io.github.krindor.ffxivsimulator.Ninja.*;
import io.github.krindor.ffxivsimulator.Ninja.Priority.DefaultOpener;
import io.github.krindor.ffxivsimulator.Ninja.Priority.Rotation;
import io.github.krindor.ffxivsimulator.Ninja.Skills.Ability;
import io.github.krindor.ffxivsimulator.Ninja.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.*;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.ArrayList;

/**
 * Created by andre on 2017-02-08.
 */
public class Simulatorpart{


    private StatModel stats;


    private double time;

    private BuffsDebuffs timers;
    private BuffsDebuffs state;

    private boolean machinistHC;
    private boolean dragoonBL;


    private double totalDamage;




    private double damage;
    private double currentTime;

    private ArrayList<String> damageLog;

    private DefaultOpener defaultOpener;
    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String specialType;
    private String prevAttack;

    private ArrayList<DamageOverTime> dotsArray;
    private NextAttack nextAttack;
    private ArrayList<Double> timersForRotation;
    private Rotation rotation;
    private ArrayList<String> opener;
    private int openerNum;
    private Formulas formulas;
    private String resistance;
    private int jobmod;
    private String job;
    private JobHub jobHub;

    private DamageCalculation damageCalculation;

    public Simulatorpart(StatModel stats, int time, String openerType, ArrayList<String> Opener, String job) {
        this.job = job;
        this.stats = stats;
        this.time = time;
        rotation = new Rotation();
        defaultOpener = new DefaultOpener();

        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;
        }


        openerNum = 0;
        timers = new BuffsDebuffs(0);
        state = new BuffsDebuffs();
        loadClass();
        jobHub = new JobHub(job);

        formulas = new Formulas(stats, jobmod);
        damageCalculation = new DamageCalculation(job, formulas);
        nextAttack = new NextAttack();



    }

    private void loadClass() {
        switch (job){
            case "Ninja": loadNinja(); break;
        }



    }

    public ArrayList<String> runSim() {

        double damagePerSecond;
        currentTime = 0;

        damageLog = new ArrayList<>(150);
        while (currentTime <= time) {
            multipliers();
            formulas.changeRecast(state);
            setTimersForRotation();
            damage = 0;

            if (nextAttack.getNextGCD() <= 0 || nextAttack.getNextOGCD() <= 0) {

                if (openerNum < opener.size()) {

                    if (jobHub.openerTypeCheck(opener, openerNum).equals("OGCD") && nextAttack.getNextOGCD() <= 0) {
                        openerCheck();


                        oGCD();
                        openerNum++;
                        checkBoolean();

                    } else if (jobHub.openerTypeCheck(opener, openerNum).equals("Weapon_Skill") && nextAttack.getNextGCD() <= 0) {

                        openerCheck();
                        GCD();
                        openerNum++;
                        checkBoolean();
                    } else nextAttack.setNextOGCD(0.5);
                } else if (nextAttack.getNextGCD() <= 0) {

                    attack = rotation.getNextGCD(timersForRotation, prevAttack, timers, state);
                    GCD();
                    checkBoolean();

                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() > 1) {

                    attack = rotation.getNextOGCD(timersForRotation, timers, state);
                    if (rotation.getNextOGCD(timersForRotation, timers, state) != null) {
                        oGCD();
                        checkBoolean();
                    } else checkDelay();


                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() < 1) {
                    nextAttack.setNextOGCD(nextAttack.getNextGCD());
                }
            }

            if (nextAttack.getNextAA() <= 0) {
                type = "Auto-Attack";
                damage = damageCalculation.getDamage(0, type, resistance, state);
                nextAttack.setNextAA(formulas.getAaRecast());
                damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
            }

            if (nextAttack.getNextDoT() <= 0) {

                for (DamageOverTime dot : dotsArray) {
                    if (dot.getTime() > 0) {
                        damage = damage + dot.getDamage(job);

                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(dot.getDamage(job) * 100) / 100 + " Type: " + dot.getName());
                    }


                }
                nextAttack.setNextDoT(3);
            }

            nextAttack.setNextAttack();


            totalDamage = totalDamage + damage;

            currentTime = (Math.floor((currentTime + nextAttack.getNextAttack()) * 1000)) / 1000;
            timeChange(nextAttack.getNextAttack());


        }

        damagePerSecond = totalDamage / time;
        double dps = Math.floor(damagePerSecond);
        damageLog.add(Double.toString(dps));
        return damageLog;
    }

    private void GCD() {

        skillUsed();
        prevAttack = attack;
        if (potency > 0) {

            damage = damageCalculation.getDamage(potency, type, resistance, state);
        }

        nextAttack.setNextGCD(formulas.getRecast());
        nextAttack.setNextOGCD(0.7);
        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void oGCD() {
        skillUsed();
        checkDelay();

        if (potency > 0) {

            damage = damageCalculation.getDamage(potency, type, resistance, state);
        }

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void checkDelay() {

        nextAttack = jobHub.nextAttack(currentTime, nextAttack, specialType, attack);
    }

    private void checkBoolean(){
        state = jobHub.checkState(state, specialType, type2);
    }

    private void openerCheck() {

        attack = opener.get(openerNum);


    }


    private void skillUsed() {


        specialType = "NoN";
        potency = 0;
        jobHub.skillUsed(null, specialType, potency, attack, type2, timers, state, dotsArray);

        attack =  jobHub.getAttack();
        dotsArray = jobHub.getDotsArray();
        potency = jobHub.getPotency();
        specialType = jobHub.getSpecialType();
        state = jobHub.getState();
        timers = jobHub.getTimers();
        type = jobHub.getType();
        type2 = jobHub.getType2();



    }


    private void multipliers() {
        state = jobHub.multiplier(timers, state);
    }



    private void timeChange(double change) {
        nextAttack.timeChange(change);
        timers.timeChange(change);
        for (DamageOverTime dot : dotsArray) {
            dot.timeChange(change);
        }

    }

    private void setTimersForRotation() {
        timersForRotation = new ArrayList<>(4);
        for (DamageOverTime dot : dotsArray) {
            timersForRotation.add(dot.getTime());
        }

        timersForRotation.add(formulas.getRecast());
        timersForRotation.add(nextAttack.getNextGCD());
    }

    private void loadNinja(){
        io.github.krindor.ffxivsimulator.Ninja.SimulatorCore simulatorCore = new io.github.krindor.ffxivsimulator.Ninja.SimulatorCore();
        jobmod = simulatorCore.getJobmod();
        resistance = "Slashing";

        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(40, stats, jobmod, "Shadow Fang"));
        dotsArray.add(new DamageOverTime(30, stats, jobmod, "Mutilate"));


        machinistHC = simulatorCore.isMachinist();
        dragoonBL = simulatorCore.isDragoon();

        if (simulatorCore.isWarThere()) {
            timers.setDancingEdge(time + 1);
        }
        timers.setHutonTime(70 - simulatorCore.getHutonTime());

        timers.setMudra(20 - simulatorCore.getHutonTime());
    }


}
