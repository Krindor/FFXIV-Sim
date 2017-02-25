package Simulator.Ninja;

import Simulator.CrossClassSkills.Dragoon;
import Simulator.CrossClassSkills.General;
import Simulator.CrossClassSkills.Monk;
import Simulator.Ninja.Priority.DefaultOpener;
import Simulator.Ninja.Priority.Rotation;
import Simulator.Ninja.Skills.Ability;
import Simulator.Ninja.Skills.WeaponSkills;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by andre on 2017-02-08.
 */
public class Simulatorpart {


    private ArrayList<Integer> stats;


    private double timer;
    private double bFBTime;
    private double iRTime;
    private double kassatsuTime;
    private double dualityTime;
    private double dWaDTime;
    private double mudraTime;
    private double tATime;
    private double mugTime;
    private double jugTime;
    private double dancingTime;
    private double hutonTime;
    private double machinistTime;
    private double dragoonTime;
    private double potionTime;
    private boolean dualityUsed;
    private boolean kassatsuUsed;
    private double mutdoTTime;
    private double sfdoTTime;
    private boolean suitonUsed;


    private boolean machinistHC;
    private boolean dragoonBL;
    private boolean warrior;

    private double nextAA;
    private double nextDoT;
    private double nextGCD;
    private double nextoGCD;

    private double totalDamage;

    private double mainstat;
    private double weaponDamage;
    private double crit;
    private double determination;
    private double skillSpeed;

    private double dancingEdgeDamage;
    private double tADamage;
    private double bFBDamage;
    private double potionDamage;
    private double iRDamage;

    private double mutBfB;
    private double mutIR;
    private double mutTA;
    private double mutPot;

    private double sfBfB;
    private double sfIR;
    private double sfTA;
    private double sfPot;

    private double statmultiplier;

    private double damage;

    private DefaultOpener defaultOpener;
    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String mudraType;
    private String prevattack;
    private double sfdoTpotency;

    private double mutdoTpotency;

    private double recast;
    private double aaRecast;
    private ArrayList<Double> timers;
    private ArrayList<Double> timersForRotation;
    private Rotation rotation;
    private ArrayList<String> opener;
    private int openerNum;

    public Simulatorpart(ArrayList<Integer> mainstats, int time, boolean MCH, boolean DRG, boolean WAR, String openerType, int hutonBeforePull, ArrayList<String> Opener) {
        stats = new ArrayList<>(mainstats);
        stats = mainstats;
        timer = time;
        rotation = new Rotation();
        defaultOpener = new DefaultOpener();
        System.out.println(Opener);
        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;


        }
        System.out.println(opener);
        openerNum = 0;
        cooldownReset();
        suitonUsed = false;
        machinistHC = MCH;
        dragoonBL = DRG;
        weaponDamage = stats.get(0);
        mainstat = stats.get(1);
        crit = stats.get(2);
        determination = stats.get(3);
        skillSpeed = stats.get(4);
        statmultiplier = (1 + (weaponDamage * 0.0432544)) * (mainstat * 0.1027246) * (1 + (determination / 7290)) / 100;

        timers = new ArrayList<>(4);

        timers.add(nextAA);
        timers.add(nextDoT);
        timers.add(nextGCD);
        timers.add(nextoGCD);

        if (WAR) {
            dancingTime = time + 1;
        } else dancingTime = 0;
        hutonTime = 70 - hutonBeforePull;
        mudraTime = 20 - hutonBeforePull;
        dualityUsed = false;
        kassatsuUsed = false;
        recast = Math.floor(((Math.floor((1 - ((Math.floor(((skillSpeed - 354) * 0.13 / 858) * 1000) / 1000))) * (2.5) * 100) / 100) * 0.85) * 100) / 100;
        aaRecast = Math.floor((2.56 * 0.85) * 100) / 100;

    }

    public ArrayList<String> runSim() {
        double damagePerSecond;
        double currentTime = 0;
        ArrayList<String> damageLog = new ArrayList<>(150);
        while (currentTime <= timer) {
            setTimersForRotation();
            damage = 0;
            multipliers();
            if (nextGCD <= 0 || nextoGCD <= 0) {
                if (openerNum < opener.size()) {

                    if (openerTypeCheck().equals("OGCD") && nextoGCD <= 0) {
                        openerCheck();
                        skillUsed();

                        if (currentTime == 0 && attack.equals("Suiton")) {
                            nextoGCD = 0.7;
                            nextGCD = 0.7;
                        } else checkMudraStep();

                        if (potency > 0) {

                            damage = potency * damageMultiplier(type);
                        }

                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                        openerNum++;


                    } else if (openerTypeCheck().equals("Weapon_Skill") && nextGCD <= 0) {

                        openerCheck();
                        skillUsed();
                        prevattack = attack;
                        if (potency > 0) {
                            damage = (potency * damageMultiplier(type));
                        }
                        nextGCD = recast;
                        nextoGCD = 0.7;
                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                        openerNum++;
                    } else nextoGCD = 0.5;
                } else if (nextGCD <= 0) {

                    attack = rotation.getNextGCD(timersForRotation, prevattack);
                    skillUsed();
                    prevattack = attack;
                    if (potency > 0) {
                        damage = (potency * damageMultiplier(type));
                    }
                    nextGCD = recast;
                    nextoGCD = 0.7;
                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);

                } else if (nextoGCD <= 0 && nextGCD > 1) {

                    attack = rotation.getNextOGCD(timersForRotation);
                    if (rotation.getNextOGCD(timersForRotation) != null) {
                        skillUsed();
                        if (potency > 0) {
                            damage = (potency * damageMultiplier(type));
                        }

                        checkMudraStep();
                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                    } else checkMudraStep();
                } else if (nextoGCD <= 0 && nextGCD < 1) {
                    nextoGCD = nextGCD;
                }
            }

            if (nextAA <= 0) {
                damage = damageMultiplier("Auto Attack");
                nextAA = aaRecast;
                damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
            }

            if (nextDoT <= 0) {
                double sfdamage = 0;
                double mutdamage = 0;
                if (sfdoTTime > 0) {
                    sfdamage = damageMultiplier("SF DoT");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(sfdamage * 100) / 100 + " Type: " + "SF DoT");
                }
                if (mutdoTTime > 0) {
                    mutdamage = damageMultiplier("Mut DoT");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(mutdamage * 100) / 100 + " Type: " + "Mut DoT");
                }
                damage = damage + mutdamage + sfdamage;
                nextDoT = 3;
            }

            timers.set(0, nextGCD);
            timers.set(1, nextoGCD);
            timers.set(2, nextAA);
            timers.set(3, nextDoT);


            totalDamage = totalDamage + damage;
            currentTime = (Math.floor((currentTime + Collections.min(timers)) * 1000)) / 1000;
            timeChange(Collections.min(timers));


        }

        damagePerSecond = totalDamage / timer;
        damageLog.add("DPS:" + Math.floor(damagePerSecond));
        return damageLog;
    }

    private void checkMudraStep() {


        if (nextGCD < 1.7 && mudraType.equals("2-step")) {
            nextGCD = 1.7;
            nextoGCD = 1.7;
        } else if (nextGCD < 1.2 && mudraType.equals("1-step")) {
            nextGCD = 1.2;
            nextoGCD = 1.2;
        } else if (nextGCD < 2.2 && mudraType.equals("3-step")) {
            nextGCD = 2.2;
            nextoGCD = 2.2;
        } else {
            if (nextGCD < 0.5) {
                nextGCD = 0.7;
                nextoGCD = 0.7;
            } else nextoGCD = 0.7;

        }
    }

    private void openerCheck() {

        attack = opener.get(openerNum);


    }

    private String openerTypeCheck() {
        switch (opener.get(openerNum)) {
            case "Suiton":
            case "Internal_Release":
            case "Blood_for_Blood":
            case "Potion":
            case "Kassatsu":
            case "Trick_Attack":
            case "Raiton":
            case "Dream_Within_a_Dream":
            case "Mug":
            case "Duality":
            case "Jugulate":
            case "Fuma_Shuriken":
                return "OGCD";

            case "Spinning_Edge":
            case "Shadow_Fang":
            case "Mutilate":
            case "Gust_Slash":
            case "Aeolian_Edge":
            case "Dancing_Edge":
            case "Armor_Crush":
                return "Weapon_Skill";
        }
        return "NoN";
    }

    private void skillUsed() {
        Ability ability = new Ability();
        Monk monkCrossClass = new Monk();
        WeaponSkills weaponSkills = new WeaponSkills();
        Dragoon dragoonCrossClass = new Dragoon();
        General potion = new General();
        type = null;
        mudraType = "NoN";
        potency = 0;


        if (attack.equals("Fuma_Shuriken")) {
            ability.fuma_Shuriken();
            type2 = "Ability";
            mudraTime = ability.getCooldown();
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();

        } else if (attack.equals("Raiton")) {
            ability.raiton();
            mudraTime = ability.getCooldown();
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Suiton")) {
            ability.suiton();
            mudraTime = ability.getCooldown();
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";
            suitonUsed = true;


        } else if (attack.equals("Kassatsu")) {
            ability.kassatsu();
            kassatsuTime = ability.getCooldown();
            potency = ability.getPotency();
            kassatsuUsed = true;
            type = ability.getType();
            mudraTime = 0;
            type2 = "Ability";

        } else if (attack.equals("Duality")) {
            ability.duality();
            dualityTime = ability.getCooldown();
            potency = ability.getPotency();
            dualityUsed = true;
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Dream_Within_a_Dream")) {
            ability.dreamWithinADream();
            dWaDTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Mug")) {
            ability.mug();
            mugTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Jugulate")) {
            ability.jugulate();
            jugTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Trick_Attack")) {
            ability.trickAttack();
            tATime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
            suitonUsed = false;
        } else if (attack.equals("Internal_Release")) {
            monkCrossClass.internalRelease();
            iRTime = monkCrossClass.getCooldown();
            potency = monkCrossClass.getPotency();
            type2 = "Ability";
            type = monkCrossClass.getType();
        } else if (attack.equals("Blood_for_Blood")) {
            dragoonCrossClass.bloodForBlood();
            bFBTime = dragoonCrossClass.getCooldown();
            potency = dragoonCrossClass.getPotency();
            type2 = "Ability";
            type = dragoonCrossClass.getType();
        } else if (attack.equals("Potion")) {
            potion.potion();
            potionTime = potion.getCooldown();
            potency = potion.getPotency();
            type2 = "Ability";
            type = potion.getType();
        } else if (attack.equals("Spinning_Edge")) {
            weaponSkills.spinningEdge();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
        } else if (attack.equals("Gust_Slash")) {
            weaponSkills.gustSlash();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
        } else if (attack.equals("Aeolian_Edge")) {
            weaponSkills.aeolianEdge();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
        } else if (attack.equals("Dancing_Edge")) {
            weaponSkills.dancingEdge();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dancingTime = 20;
            type2 = "Weapon Skill";
        } else if (attack.equals("Armor_Crush")) {
            weaponSkills.armorCrush();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            hutonTime = hutonTime + 30;
            if (hutonTime > 70) {
                hutonTime = 70;
            }
            type2 = "Weapon Skill";
        } else if (attack.equals("Shadow_Fang")) {
            weaponSkills.shadowFang();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            sfdoTpotency = weaponSkills.getDoTPotency();
            sfdoTTime = weaponSkills.getDoTTime();
            sfBfB = bFBDamage;
            sfIR = iRDamage;
            sfTA = tADamage;
            sfPot = potionDamage;
            type2 = "Weapon Skill";

        } else if (attack.equals("Mutilate")) {
            weaponSkills.mutilate();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            mutdoTpotency = weaponSkills.getDoTPotency();
            mutdoTTime = weaponSkills.getDoTTime();
            mutBfB = bFBDamage;
            mutIR = iRDamage;
            mutTA = tADamage;
            mutPot = potionDamage;
            type2 = "Weapon Skill";
        }


    }


    private void cooldownReset() {
        bFBTime = 0;
        iRTime = 0;
        kassatsuTime = 0;
        dualityTime = 0;
        dWaDTime = 0;
        mudraTime = 0;
        tATime = 0;
        mugTime = 0;
        jugTime = 0;
        nextAA = 0;
        nextDoT = 0;
        nextGCD = 0;
        nextoGCD = 0;
        sfdoTTime = 0;
        mutdoTTime = 0;

    }

    private void multipliers() {
        if (bFBTime <= 80 && bFBTime >= 60) {
            bFBDamage = 1.1;
        } else bFBDamage = 1;

        if (dancingTime > 0) {
            dancingEdgeDamage = 1.1;
        } else dancingEdgeDamage = 1;

        if (potionTime <= 270 && potionTime >= 255) {
            potionDamage = (154 + mainstat) / mainstat;
        } else potionDamage = 1;

        if (tATime <= 60 && tATime >= 50) {
            tADamage = 1.1;
        } else tADamage = 1;

        if (iRTime <= 60 && iRTime >= 45) {
            iRDamage = 0.1;

        } else iRDamage = 0;
    }

    private double damageMultiplier(String type) {
        int multiplier = 1;
        if (type.equals("Physical")) {

            if (dualityUsed && type2.equals("Weapon Skill")) {

                dualityUsed = false;
                return statmultiplier * bFBDamage * potionDamage * dancingEdgeDamage * tADamage * 2 * 1.2;


            } else if (iRTime <= 60 && iRTime >= 45) {

                return (1 + ((((crit - 354) / (858 * 5) + 0.05 + 0.1)) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * dancingEdgeDamage * tADamage * 1.2;
            } else {
                return (1 + ((((crit - 354) / (858 * 5)) + 0.05) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * dancingEdgeDamage * tADamage * 1.2;
            }
        } else if (type.equals("Magical")) {
            if (!mudraType.equals("NoN") && kassatsuUsed) {
                kassatsuUsed = false;
                return (1 + (((crit - 354) / (858 * 5)) + 0.45)) * statmultiplier * bFBDamage * potionDamage * tADamage;

            } else if (iRTime <= 60 && iRTime >= 45) {
                return (1 + ((((crit - 354) / (858 * 5)) + 0.05 + 0.1) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * tADamage;

            } else {
                return (1 + ((((crit - 354) / (858 * 5)) + 0.05) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * tADamage;

            }
        } else if (type.equals("Auto Attack")) {
            if (iRTime <= 60 && iRTime >= 45) {
                return ((1 + ((((crit - 354) / (858 * 5)) + 0.05 + 0.1) * ((crit - 354) / (858 * 5) + 0.45))) * (weaponDamage / (3 / 2.56) * 0.0593365489928915 + 1) * (mainstat * 0.0841892) * (determination / 6832.8 + 1) * bFBDamage * tADamage * potionDamage * dancingEdgeDamage * 1.2);
            } else {

                return ((1 + ((((crit - 354) / (858 * 5)) + 0.05) * ((crit - 354) / (858 * 5) + 0.45))) * (weaponDamage / (3 / 2.56) * 0.0593365489928915 + 1) * (mainstat * 0.0841892) * (determination / 6832.8 + 1) * bFBDamage * tADamage * potionDamage * dancingEdgeDamage * 1.2);

            }
        } else if (type.equals("Mut DoT")) {
            return mutdoTpotency * (1 + ((((crit - 354) / (858 * 5)) + 0.05 + mutIR) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * mutBfB * mutTA * mutPot * 1.2 * ((skillSpeed) / 6800 + 1);

        } else if (type.equals("SF DoT")) {

            return sfdoTpotency * (1 + ((((crit - 354) / (858 * 5)) + 0.05 + sfIR) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * sfBfB * sfTA * sfPot * 1.2 * ((skillSpeed) / 6800 + 1);
        }

        return multiplier;
    }

    private void timeChange(double change) {
        nextAA = nextAA - change;
        nextoGCD = nextoGCD - change;
        nextGCD = nextGCD - change;
        nextDoT = nextDoT - change;
        bFBTime = bFBTime - change;
        iRTime = iRTime - change;
        kassatsuTime = kassatsuTime - change;
        dualityTime = dualityTime - change;
        dWaDTime = dWaDTime - change;
        mudraTime = mudraTime - change;
        tATime = tATime - change;
        mugTime = mugTime - change;
        jugTime = jugTime - change;
        dancingTime = dancingTime - change;
        hutonTime = hutonTime - change;
        machinistTime = machinistTime - change;
        dragoonTime = dragoonTime - change;
        potionTime = potionTime - change;
        sfdoTTime = sfdoTTime - change;
        mutdoTTime = mutdoTTime - change;


    }

    private void setTimersForRotation() {
        timersForRotation = new ArrayList<>(16);
        timersForRotation.add(bFBTime);
        timersForRotation.add(iRTime);
        timersForRotation.add(kassatsuTime);
        timersForRotation.add(dualityTime);
        timersForRotation.add(dWaDTime);
        timersForRotation.add(mudraTime);
        timersForRotation.add(tATime);
        timersForRotation.add(mugTime);
        timersForRotation.add(jugTime);
        timersForRotation.add(dancingTime);
        timersForRotation.add(hutonTime);
        timersForRotation.add(potionTime);
        timersForRotation.add(sfdoTTime);
        timersForRotation.add(mutdoTTime);
        timersForRotation.add(recast);
        timersForRotation.add(nextGCD);
    }


}
