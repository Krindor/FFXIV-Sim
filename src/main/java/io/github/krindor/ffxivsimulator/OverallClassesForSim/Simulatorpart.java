package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Skills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.AttackType;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;
import io.github.krindor.ffxivsimulator.RotationOpenerClasses.JobInfo;
import io.github.krindor.ffxivsimulator.RotationOpenerClasses.SkillAction;
import io.github.krindor.ffxivsimulator.TextFileLoader;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by andre on 2017-02-08.
 */
public class Simulatorpart {


    private StatModel stats;


    private double time;


    private double totalDamage;


    private double damage;
    private double currentTime;

    private ArrayList<String> damageLog;

    private double damagePerSecond;
    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String specialType;
    private String prevAttack;

    private ArrayList<DamageOverTime> dotsArray;
    private NextAttack nextAttack;
    private ArrayList<Double> timersForRotation;


    private Formulas formulas;
    private String resistance;
    private int jobmod;
    private String jobName;
    private JobHub jobHub;
    private AllBuffs allBuffs;
    private ArrayList<Skills> skills;
    private ArrayList<Buffs> buffs;
    private ArrayList<Abilities> abilities;
    private AttackType attackType;
    private double nextGCD;
    private Resources resources;
    private ArrayList<String> buffTargets;
    private DamageCalculation damageCalculation;

    private LinkedList<SkillAction> skillActions;

    public Simulatorpart(JobInfo jobInfo) {

        jobName = jobInfo.getJobName();
        stats = jobInfo.getStats();
        time = jobInfo.getTime();
        resources.setTacticalPoints(1000);
        resources.setTotalTactical(1000);
        Job job = jobInfo.getJob();
        skills = new ArrayList<>(job.getSkills().length);
        buffs = new ArrayList<>(job.getAbilities().length);
        abilities = new ArrayList<>(job.getAbilities().length);
        Collections.addAll(skills, job.getSkills());
        Collections.addAll(buffs, job.getBuffs());
        Collections.addAll(abilities, job.getAbilities());
        currentTime = 0;
        skillActions = new LinkedList<>();
        Collections.addAll(skillActions, jobInfo.getActionObjects().getSkillAction());

        jobmod = jobInfo.getJobmod();
        loadClass();
        jobHub = new JobHub(jobName);
        resources = new Resources();
        formulas = new Formulas(stats, jobmod);
        damageCalculation = new DamageCalculation(jobName, formulas);
        nextAttack = new NextAttack();
        resistance = jobInfo.getResistance();

        nextGCD = 0;
        buffTargets = new ArrayList<>(3);
        buffTargets.add("Player");
        buffTargets.add("Party");
        buffTargets.add("Target");

    }

    private void loadClass() {
        switch (jobName) {
            case "Ninja":
                loadNinja();
                break;
            case "Monk":
                loadMonk();
                break;
            case "Dragoon":
                loadDragoon();
                break;
        }


    }

    public ArrayList<String> runSim(AllBuffs buffs) {


        damageLog = new ArrayList<>(150);
        while (currentTime <= time) {


            formulas.changeRecast(buffs);
            setTimersForRotation();
            damage = 0;

            switch (attackType.getType()) {
                case "Opener": {
                    SkillAction skillAction = skillActions.getFirst();
                    attack = skillAction.getName();
                    double nextTime = 0;
                    if (skillAction.getFixedTime() < 0.7) {
                        switch (skillAction.getType()) {
                            case "GCD":
                                nextTime = nextGCD;
                                nextGCD = formulas.getRecast();
                                break;
                            case "oGCD":
                                nextTime = 0.7;
                                if (nextGCD < 0.7 + skillAction.getOffset()) {
                                    nextGCD = 0.7 + skillAction.getOffset();
                                }
                                break;
                        }
                    } else nextTime = skillAction.getFixedTime();
                    nextTime = nextTime + skillAction.getOffset();
                    nextAttack.addNextAttack("Opener", nextTime);
                }
                break;
                case "GCD": {
                    attack = jobHub.getNextGCD(timersForRotation, prevAttack, allBuffs, resources);
                    GCD();

                    nextAttack.addNextAttack("GCD", formulas.getRecast());
                    nextAttack.addNextAttack("oGCD", 0.7);
                }
                break;
                case "oGCD": {
                    attack = jobHub.getNextOGCD(timersForRotation, allBuffs, resources);
                    if (jobHub.getNextOGCD(timersForRotation, allBuffs, resources) != null) {
                        oGCD();

                    } else checkDelay();
                }
                break;
                case "Auto-Attack": {
                    type = "Auto-Attack";
                    damage = damageCalculation.getDamage(0, type, resistance, allBuffs);
                    nextAttack.addNextAttack(type, formulas.getAaRecast());
                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
                }
                case "DoT": {
                    for (DamageOverTime dot : dotsArray) {
                        if (dot.getTime() > 0) {
                            damage = damage + dot.getDamage(jobName);

                            damageLog.add("[" + currentTime + "] Damage: " + Math.floor(dot.getDamage(jobName) * 100) / 100 + " Type: " + dot.getName());
                        }
                    }
                    nextAttack.addNextAttack("DoT", 3);
                }
                case "Buff": {
                    for (String i : buffTargets) {
                        BuffBar bar = allBuffs.getBuffBar(i);
                        damageLog.add(bar.buffRunOut());
                        allBuffs.setBuffBar(i, bar);
                    }
                }
            }

            nextAttack.addNextAttack("Buff", allBuffs.getNextRunOut());

            attackType = nextAttack.getNextAttack();

            totalDamage = totalDamage + damage;

            currentTime = (Math.floor((currentTime + attackType.getTime()) * 1000)) / 1000;
            timeChange(attackType.getTime());


        }

        damagePerSecond = totalDamage / time;
        double dps = Math.floor(damagePerSecond);
        damageLog.add(Double.toString(dps));
        return damageLog;
    }

    private void GCD() {
        Skills skill = null;
        for (Skills i : skills) {
            if (i.getName().equals(attack)) {
                skill = i;
                break;
            }
        }

        prevAttack = attack;
        if (skill.getPotency() > 0) {

            damage = damageCalculation.getDamage(skill.getPotency(), skill.getType(), skill.getType2(), allBuffs);
        }

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void oGCD() {

        checkDelay();

        Abilities abilitie = null;
        for (Abilities i : abilities) {
            if (i.getName().equals(attack)) {
                abilitie = i;
                break;
            }
        }

        if (abilitie.getPotency() > 0) {

            damage = damageCalculation.getDamage(abilitie.getPotency(), abilitie.getType(), abilitie.getType2(), allBuffs);
        }

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void checkDelay() {

        nextAttack = jobHub.nextAttack(currentTime, nextAttack, specialType, attack);
    }


    private void timeChange(double change) {
        nextAttack.timeChange(change);
        timers.timeChange(change);
        nextGCD = nextGCD - attackType.getTime();
        if (nextGCD < 0) {
            nextGCD = 0;
        }
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

    }

    private void loadNinja() {

        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(40, stats, jobmod, "Shadow Fang"));
        dotsArray.add(new DamageOverTime(30, stats, jobmod, "Mutilate"));
        resources.setManaPoints(5000);
        resources.setTotalMana(5000);
        resources.setClassSpecific(0);
        resources.setTotalClass(100);


    }

    private void loadMonk() {


        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(50, stats, jobmod, "Demolish"));
        dotsArray.add(new DamageOverTime(25, stats, jobmod, "Touch of Death"));
        dotsArray.add(new DamageOverTime(20, stats, jobmod, "Fracture"));

    }

    private void loadDragoon() {


        dotsArray = new ArrayList<>(2);
        dotsArray.add(new DamageOverTime(35, stats, jobmod, "Chaos Thrust"));
        dotsArray.add(new DamageOverTime(30, stats, jobmod, "Phlebotomize"));


    }

    private ArrayList<String> loadOpener(String job) {

        File file = new File("resources.io.github.krindor.ffxivsimulator.Openers." + job);
        TextFileLoader textFileLoader = new TextFileLoader();

        return textFileLoader.loadText(file);
    }


}
