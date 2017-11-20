package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.Damage.DamageCalculation;
import io.github.krindor.ffxivsimulator.Damage.DamageOverTime;
import io.github.krindor.ffxivsimulator.Damage.Formulas;
import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Enums.TimerNames;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.JSON.Rotation.Rotation;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Skills;
import io.github.krindor.ffxivsimulator.JobClasses.JobInfo;
import io.github.krindor.ffxivsimulator.JobClasses.Resources;
import io.github.krindor.ffxivsimulator.TextFileLoader;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
import io.github.krindor.ffxivsimulator.Timers.AttackType;
import io.github.krindor.ffxivsimulator.Timers.BuffBar;
import io.github.krindor.ffxivsimulator.Timers.NextAttack;
import io.github.krindor.ffxivsimulator.model.SkillModel;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * Created by andre on 2017-02-08.
 */
class Simulatorpart {


    private StatModel stats;


    private double time;


    private double totalDamage;


    private double damage;
    private double currentTime;

    private ArrayList<String> damageLog;

    private double damagePerSecond;
    private String attack;
    private int potency;
    private String type2;
    private String specialType;
    private String prevAttack;
    private Rotation rotationGCD;
    private Rotation rotationoGCD;
    private ArrayList<DamageOverTime> dotsArray;
    private NextAttack nextAttack;
    private ArrayList<Double> timersForRotation;


    private Formulas formulas;
    private TypeNames resistance;
    private int jobmod;
    private String jobName;

    private AllBuffs allBuffs;
    private ArrayList<Skills> skills;
    private TreeMap<String, Buffs> buffs;
    private ArrayList<Abilities> abilities;
    private AttackType attackType;
    private double nextGCD;
    private Resources resources;
    private ArrayList<BuffBarNames> buffTargets;
    private DamageCalculation damageCalculation;

    private LinkedList<SkillModel> skillModels;

    public Simulatorpart(JobInfo jobInfo) {

        jobName = jobInfo.getJobName();
        stats = jobInfo.getStats();
        time = jobInfo.getTime();
        resources.setTacticalPoints(1000);
        resources.setTotalTactical(1000);
        Job job = jobInfo.getJob();
        skills = new ArrayList<>(job.getSkills().length);
        buffs = jobInfo.getTreeMap();
        abilities = new ArrayList<>(job.getAbilities().length);
        Collections.addAll(skills, job.getSkills());

        Collections.addAll(abilities, job.getAbilities());
        currentTime = 0;
        skillModels = new LinkedList<>();
        Collections.addAll(skillModels, jobInfo.getActionObjects().getSkillModel());

        jobmod = jobInfo.getJobmod();


        resources = new Resources();
        formulas = new Formulas(stats, jobmod);
        damageCalculation = new DamageCalculation(jobName, formulas);
        nextAttack = new NextAttack();
        resistance = jobInfo.getResistance();
        allBuffs = new AllBuffs();
        nextGCD = 0;
        buffTargets = new ArrayList<>(3);
        buffTargets.add(BuffBarNames.Player);
        buffTargets.add(BuffBarNames.Party);
        buffTargets.add(BuffBarNames.Target);

    }


    public ArrayList<String> runSim(AllBuffs buffs) {


        damageLog = new ArrayList<>(150);
        while (currentTime <= time) {


            formulas.changeRecast(buffs);
            setTimersForRotation();
            damage = 0;

            switch (attackType.getType()) {
                case Opener: {
                    SkillModel skillModel = skillModels.getFirst();
                    attack = skillModel.getName();
                    double nextTime = 0;
                    if (skillModel.getFixedTime() < 0.7) {
                        switch (skillModel.getType()) {
                            case "GCD":
                                nextTime = nextGCD;
                                nextGCD = formulas.getRecast();
                                break;
                            case "oGCD":
                                nextTime = 0.7;
                                if (nextGCD < 0.7 + skillModel.getOffset()) {
                                    nextGCD = 0.7 + skillModel.getOffset();
                                }
                                break;
                        }
                    } else nextTime = skillModel.getFixedTime();
                    nextTime = nextTime + skillModel.getOffset();
                    nextAttack.addNextAttack(TimerNames.Opener, nextTime);
                }
                break;
                case GCD: {
                    attack = rotationGCD.getNext(allBuffs);
                    GCD();

                    nextAttack.addNextAttack(TimerNames.GCD, formulas.getRecast());
                    nextAttack.addNextAttack(TimerNames.oGCD, 0.7);
                }
                break;
                case oGCD: {
                    attack = rotationoGCD.getNext(allBuffs);
                    if (attack != null) {
                        oGCD();

                    } else checkDelay();
                }
                break;
                case AutoAttack: {
                    TimerNames type = TimerNames.AutoAttack;
                    damage = damageCalculation.getDamage(0, TypeNames.AutoAttack, resistance, allBuffs);
                    nextAttack.addNextAttack(type, formulas.getAaRecast());
                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
                }
                case DoT: {
                    for (DamageOverTime dot : dotsArray) {
                        if (dot.getTime() > 0) {
                            damage = damage + dot.getDamage(jobName);

                            damageLog.add("[" + currentTime + "] Damage: " + Math.floor(dot.getDamage(jobName) * 100) / 100 + " Type: " + dot.getName());
                        }
                    }
                    nextAttack.addNextAttack(TimerNames.DoT, 3);
                }
                case Buff: {
                    for (BuffBarNames i : buffTargets) {
                        BuffBar bar = allBuffs.getBuffBar(i);
                        damageLog.add(bar.buffRunOut());
                        allBuffs.setBuffBar(i, bar);
                    }
                }
            }

            nextAttack.addNextAttack(TimerNames.Buff, allBuffs.getNextRunOut());

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

            damage = damageCalculation.getDamage(skill.getPotency(), skill.getTypes(), skill.getType2s(), allBuffs);
        }
        if (skill.hasBuff()) {
            BuffBarNames target = buffs.get(skill.getBuff()).getTargetEnum();
            allBuffs.addBuff(target, buffs.get(skill.getBuff()));
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

            damage = damageCalculation.getDamage(abilitie.getPotency(), abilitie.getTypes(), abilitie.getType2s(), allBuffs);
        }

        if (abilitie.hasBuffs()) {
            BuffBarNames target = buffs.get(abilitie.getBuff()).getTargetEnum();
            allBuffs.addBuff(target, buffs.get(abilitie.getBuff()));
        }

        allBuffs.setCooldown(abilitie);

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void checkDelay() {


    }


    private void timeChange(double change) {
        nextAttack.timeChange(change);
        allBuffs.timeChange(change);
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


    private ArrayList<String> loadOpener(String job) {

        File file = new File("resources.io.github.krindor.ffxivsimulator.Openers." + job);
        TextFileLoader textFileLoader = new TextFileLoader();

        return textFileLoader.loadText(file);
    }


}
