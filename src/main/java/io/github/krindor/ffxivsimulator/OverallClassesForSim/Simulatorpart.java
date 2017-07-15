package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.Dragoon.DragoonSimulatorCore;
import io.github.krindor.ffxivsimulator.Monk.Priority.MonkDefaultOpener;
import io.github.krindor.ffxivsimulator.Ninja.Priority.DefaultOpener;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;
import io.github.krindor.ffxivsimulator.TextFileLoader;
import io.github.krindor.ffxivsimulator.model.StatModel;


import java.io.File;

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


    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String specialType;
    private String prevAttack;

    private ArrayList<DamageOverTime> dotsArray;
    private NextAttack nextAttack;
    private ArrayList<Double> timersForRotation;

    private ArrayList<String> opener;
    private int openerNum;
    private Formulas formulas;
    private String resistance;
    private int jobmod;
    private String job;
    private JobHub jobHub;
    private BuffBar buffBar;

    private Resources resources;

    private DamageCalculation damageCalculation;

    public Simulatorpart(StatModel stats, int time, String openerType, ArrayList<String> opener, String job) {
        this.job = job;
        this.stats = stats;
        this.time = time;
        resources.setTacticalPoints(1000);
        resources.setTotalTactical(1000);




        openerNum = 0;
        timers = new BuffsDebuffs(0);
        state = new BuffsDebuffs();
        loadClass(openerType, opener);
        jobHub = new JobHub(job);
        resources = new Resources();
        formulas = new Formulas(stats, jobmod);
        damageCalculation = new DamageCalculation(job, formulas);
        nextAttack = new NextAttack();



    }

    private void loadClass(String openerType, ArrayList<String> opener) {
        switch (job){
            case "Ninja": loadNinja(openerType, opener); break;
            case "Monk": loadMonk(openerType, opener); break;
            case "Dragoon": loadDragoon(openerType, opener); break;
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
                        System.out.println(attack);

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

                    attack = jobHub.getNextGCD(timersForRotation, prevAttack, timers, state, resources);
                    GCD();
                    checkBoolean();

                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() > 0.9) {

                    attack = jobHub.getNextOGCD(timersForRotation, timers, state, resources);
                    if (jobHub.getNextOGCD(timersForRotation, timers, state, resources) != null) {
                        oGCD();
                        checkBoolean();
                    } else checkDelay();


                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() < 1) {
                    nextAttack.setNextOGCD(nextAttack.getNextGCD());
                }
            }

            if (nextAttack.getNextAA() <= 0) {
                type = "Auto-Attack";
                damage = damageCalculation.getDamage(0, type, resistance, buffBar);
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

            damage = damageCalculation.getDamage(potency, type, resistance, buffBar);
        }

        nextAttack.setNextGCD(formulas.getRecast());
        nextAttack.setNextOGCD(0.7);
        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void oGCD() {
        skillUsed();
        checkDelay();

        if (potency > 0) {

            damage = damageCalculation.getDamage(potency, type, resistance, buffBar);
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
        jobHub.skillUsed(null, specialType, potency, attack, type2, timers, state, dotsArray, resources, buffBar);

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

    private void loadNinja(String openerType, ArrayList<String> Opener){
        DefaultOpener defaultOpener = new DefaultOpener();
        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;
        }

        io.github.krindor.ffxivsimulator.Ninja.SimulatorCore simulatorCore = new io.github.krindor.ffxivsimulator.Ninja.SimulatorCore();
        jobmod = simulatorCore.getJobmod();
        resistance = "Slashing";

        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(40, stats, jobmod, "Shadow Fang"));
        dotsArray.add(new DamageOverTime(30, stats, jobmod, "Mutilate"));
        resources.setManaPoints(5000);
        resources.setTotalMana(5000);
        resources.setClassSpecific(0);
        resources.setTotalClass(100);

        machinistHC = simulatorCore.isMachinist();
        dragoonBL = simulatorCore.isDragoon();

        if (simulatorCore.isWarThere()) {
            timers.setDancingEdge(time + 1);
        }
        timers.setHutonTime(70 - simulatorCore.getHutonTime());

        timers.setMudra(20 - simulatorCore.getHutonTime());
    }

    private void loadMonk(String openerType, ArrayList<String> Opener){
        MonkDefaultOpener defaultOpener = new MonkDefaultOpener();
        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;
        }
        io.github.krindor.ffxivsimulator.Monk.SimulatorCore simulatorCore = new io.github.krindor.ffxivsimulator.Monk.SimulatorCore();
        jobmod = simulatorCore.getJobmod();
        resistance = "Blunt";

        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(50, stats, jobmod, "Demolish"));
        dotsArray.add(new DamageOverTime(25, stats, jobmod, "Touch of Death"));
        dotsArray.add(new DamageOverTime(20, stats, jobmod, "Fracture"));


        machinistHC = simulatorCore.isMachinist();
        dragoonBL = simulatorCore.isDragoon();

        state.setForm(simulatorCore.getForm());

    }

    private void loadDragoon(String openerType, ArrayList<String> Opener){

        if (openerType.equals("Default")) {
            opener = loadOpener(job);
        } else {
            opener = Opener;
        }
        DragoonSimulatorCore dragoonSimulatorCore = new DragoonSimulatorCore();
        jobmod = dragoonSimulatorCore.getJobmod();
        resistance = "Piercing";
        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(35, stats, jobmod, "Chaos Thrust"));
        dotsArray.add(new DamageOverTime(30, stats, jobmod, "Phlebotomize"));



        machinistHC = dragoonSimulatorCore.isMachinist();
        dragoonBL = dragoonSimulatorCore.isDragoon();


    }

    private ArrayList<String> loadOpener(String job){

        File file = new File("resources.io.github.krindor.ffxivsimulator.Openers." + job);
        TextFileLoader textFileLoader = new TextFileLoader();

        return textFileLoader.loadText(file);
    }


}
