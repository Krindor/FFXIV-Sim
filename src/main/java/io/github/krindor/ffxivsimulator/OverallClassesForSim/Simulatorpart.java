package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.Damage.DamageCalculation;
import io.github.krindor.ffxivsimulator.Damage.DamageOverTime;
import io.github.krindor.ffxivsimulator.Damage.Formulas;
import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Enums.TimerNames;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.Interfaces.ISkill;
import io.github.krindor.ffxivsimulator.JSON.Rotation.Rotation;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.JobClasses.JobInfo;
import io.github.krindor.ffxivsimulator.JobClasses.Resources;
import io.github.krindor.ffxivsimulator.Timers.*;
import io.github.krindor.ffxivsimulator.model.SkillModel;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.*;

/**
 * Primary class for the sim
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

    private String prevAttack;
    private Rotation rotationGCD;
    private Rotation rotationoGCD;
    private DoTBar dotBar;
    private NextAttack nextAttack;


    private Formulas formulas;
    private TypeNames resistance;

    private AllBuffs allBuffs;
    private final ArrayList<Abilities> skills;
    private TreeMap<String, Buffs> buffs;
    private final ArrayList<Abilities> abilities;
    private AttackType attackType;
    private double nextGCD;
    private Resources resources;
    private Abilities skill;
    private DamageCalculation damageCalculation;
    private Abilities abilitie;
    private LinkedList<SkillModel> skillModels;

    public Simulatorpart(JobInfo jobInfo) {

        stats = jobInfo.getStats();
        time = jobInfo.getTime();
        resources.setTacticalPoints(1000);
        resources.setTotalTactical(1000);
        Job job = jobInfo.getJob();
        skills = new ArrayList<>(job.getSkills().length);
        buffs = jobInfo.getTreeMap();
        abilities = new ArrayList<>(job.getAbilities().length);
        Collections.addAll(skills, job.getSkills());
        dotBar = new DoTBar();
        Collections.addAll(abilities, job.getAbilities());
        currentTime = 0;
        skillModels = new LinkedList<>();
        Collections.addAll(skillModels, jobInfo.getActionObjects().getSkillModel());
        rotationGCD = jobInfo.getRotationGCD();
        rotationoGCD = jobInfo.getRotationOGCD();


        resources = new Resources();
        formulas = new Formulas(stats);
        damageCalculation = new DamageCalculation(formulas);
        nextAttack = new NextAttack();
        resistance = jobInfo.getResistance();
        allBuffs = new AllBuffs();
        nextGCD = 0;


    }

//each time runSim is executed equals one event happening
    public ArrayList<String> runSim() {


        damageLog = new ArrayList<>(150);
        while (currentTime <= time) {


            damage = 0;
            //Switch to check for the next event
            switch (attackType.getType()) {
                //handles the opener to overwrite the regular rotation
                case Opener: {
                    //gets the next part of the opener
                    SkillModel skillModel = skillModels.getFirst();
                    attack = skillModel.getName();
                    //checks type to execute the corresponding method
                    if (skillModel.getType().equals("GCD")){
                        GCD(false);
                    }else if (skillModel.getType().equals("oGCD")){
                        oGCD(false);
                    }
                    double nextTime = 0;
                    //For some openers you want to delay skills, this checks if the delay is less than the standard animation lock and then adds the time for the next attack
                    if (skillModel.getFixedTime() < 0.7) {
                        switch (skillModels.get(0).getType()) {
                            case "GCD":
                                nextTime = nextGCD;
                                break;
                            case "oGCD":
                                nextTime = 0.7;

                                break;
                        }
                    } else nextTime = skillModel.getFixedTime();
                    //checks for the current recast if GCD or 0.7 if it's an oGCD
                    if (skillModel.getType().equals("GCD")){
                        nextGCD = formulas.getRecast(0, allBuffs);
                    }else if (skillModel.getType().equals("oGCD")){
                        if (nextGCD < 0.7 + skillModel.getOffset()) {
                            nextGCD = 0.7 + skillModel.getOffset();
                        }
                    }
                    nextTime = nextTime + skillModel.getOffset();
                    nextAttack.addNextAttack(TimerNames.Opener, nextTime);
                }
                break;
                //The regular rotation checking what the next move is
                case GCD: {
                    //gets next skill that matches the conditions in the GCD rotation
                    attack = rotationGCD.getNext(allBuffs, prevAttack);
                    GCD(true);


                }
                break;
                case oGCD: {
                    attack = rotationoGCD.getNext(allBuffs, prevAttack);
                    if (attack != null) {
                        oGCD(true);

                    } else {
                        nextAttack.addNextAttack(TimerNames.oGCD, 0.7);
                        nextAttack.addNextAttack(TimerNames.GCD, 0.7);
                    }
                }
                break;
                case AutoAttack: {
                    TimerNames type = TimerNames.AutoAttack;
                    damage = damageCalculation.getDamage(110, TypeNames.AutoAttack, resistance, allBuffs);
                    nextAttack.addNextAttack(type, formulas.getAaRecast(allBuffs));
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

    private void GCD(boolean notOpener) {

        for (Abilities i : skills) {
            if (i.getName().equals(attack)) {
                skill = i;
                break;
            }
        }
        if (notOpener) {
            checkDelay(skill.getDelayOffset(), TimerNames.GCD);
        }
        prevAttack = attack;
        if (skill.getPotency() > 0) {

            damage = damageCalculation.getDamage(skill.getPotency(), skill.getType(), skill.getType2(), allBuffs);
        }
        if (skill.hasBuffs()) {
            BuffBarNames target = buffs.get(skill.getBuff()).getTargetEnum();
            allBuffs.addBuff(target, buffs.get(skill.getBuff()));
        }

        if (skill.getDotPotency() > 0) {
            DamageOverTime newDoT = new DamageOverTime(skill.getDotPotency(), stats, skill.getName(), allBuffs);
            dotBar.addDoT(newDoT);
        }

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void oGCD(boolean notOpener) {



        for (Abilities i : abilities) {
            if (i.getName().equals(attack)) {
                abilitie = i;
                break;
            }
        }
        if (notOpener) {
            checkDelay(abilitie.getDelayOffset(), TimerNames.oGCD);
        }
        if (abilitie.getPotency() > 0) {

            damage = damageCalculation.getDamage(abilitie.getPotency(), abilitie.getType(), abilitie.getType2(), allBuffs);
        }

        if (abilitie.hasBuffs()) {
            BuffBarNames target = buffs.get(abilitie.getBuff()).getTargetEnum();
            allBuffs.addBuff(target, buffs.get(abilitie.getBuff()));
        }

        allBuffs.setCooldown(abilitie);

        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
    }

    private void checkDelay(double offset, TimerNames name) {
        if (name == TimerNames.GCD){
            nextAttack.addNextAttack(name, formulas.getRecast(2.5+offset, allBuffs));
            nextAttack.addNextAttack(TimerNames.oGCD, 0.7);
            nextAttack.addNextAttack(TimerNames.Cast, skill.getCastTime());
        }else if (name == TimerNames.oGCD){
            if (nextAttack.getNextAttack(TimerNames.GCD)< 0.7+offset){
                nextAttack.addNextAttack(TimerNames.GCD, 0.7+offset);
            }
            nextAttack.addNextAttack(TimerNames.oGCD, 0.7+offset);
            nextAttack.addNextAttack(TimerNames.Cast, abilitie.getCastTime());
        }

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




}
