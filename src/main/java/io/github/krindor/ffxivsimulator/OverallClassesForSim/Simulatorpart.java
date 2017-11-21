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
import io.github.krindor.ffxivsimulator.Timers.*;
import io.github.krindor.ffxivsimulator.model.SkillModel;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.io.File;
import java.util.*;

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

    private String prevAttack;
    private Rotation rotationGCD;
    private Rotation rotationoGCD;
    private DoTBar dotBar;
    private NextAttack nextAttack;



    private Formulas formulas;
    private TypeNames resistance;
    private String jobName;

    private AllBuffs allBuffs;
    private ArrayList<Skills> skills;
    private TreeMap<String, Buffs> buffs;
    private ArrayList<Abilities> abilities;
    private AttackType attackType;
    private double nextGCD;
    private Resources resources;

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

        rotationGCD = jobInfo.getRotationGCD();
        rotationoGCD = jobInfo.getRotationOGCD();

        resources = new Resources();
        formulas = new Formulas(stats);
        damageCalculation = new DamageCalculation(jobName, formulas);
        nextAttack = new NextAttack();
        resistance = jobInfo.getResistance();
        allBuffs = new AllBuffs();
        nextGCD = 0;


    }


    public ArrayList<String> runSim(AllBuffs buffs) {


        damageLog = new ArrayList<>(150);
        while (currentTime <= time) {


            formulas.changeRecast(buffs);

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
                    damage = damageCalculation.getDamage(110, TypeNames.AutoAttack, resistance, allBuffs);
                    nextAttack.addNextAttack(type, formulas.getAaRecast());
                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
                }
                case DoT: {
                    for (Map.Entry<String, DamageOverTime> dot : dotBar.getTreeMap().entrySet()) {

                            damage = damage + dot.getValue().getDamage();

                            damageLog.add("[" + currentTime + "] Damage: " + Math.floor(dot.getValue().getDamage() * 100) / 100 + " Type: " + dot.getValue().getName());

                    }
                    nextAttack.addNextAttack(TimerNames.DoT, 3);
                }
                case Buff: {
                    for (BuffBarNames i : BuffBarNames.values()) {
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

        if (skill.hasDoT()){
            DamageOverTime newDoT = new DamageOverTime(skill.getDotPotency(), stats, skill.getName(), allBuffs);
            dotBar.addDoT(newDoT);
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
        dotBar.timeChange(change);
    }

    private ArrayList<String> loadOpener(String job) {

        File file = new File("resources.io.github.krindor.ffxivsimulator.Openers." + job);
        TextFileLoader textFileLoader = new TextFileLoader();

        return textFileLoader.loadText(file);
    }


}
