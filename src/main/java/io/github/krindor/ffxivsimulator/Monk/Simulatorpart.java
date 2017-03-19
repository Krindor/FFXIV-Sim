package io.github.krindor.ffxivsimulator.Monk;

import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.Monk.Priority.DefaultOpener;
import io.github.krindor.ffxivsimulator.Monk.Priority.Rotation;
import io.github.krindor.ffxivsimulator.Monk.Skills.Ability;
import io.github.krindor.ffxivsimulator.Monk.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Warrior;

import java.util.ArrayList;
import java.util.Collections;

/**
 FFXIV Simulator
 Copyright (C) 2017  Andreas Lund

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Simulatorpart {


    private ArrayList<Integer> stats;



    private double timer;
    private double bFBTime;
    private double iRTime;
    private double elixirFieldTime;
    private double perfectBalanceTime;
    private double steelPeakTime;
    private double twinSnakesTime;
    private double shoulderTackleTime;
    private double howlingFistTime;
    private double glTime;

    private double dragonKickTime;

    private double machinistTime;
    private double dragoonTime;
    private double potionTime;
    private double tODdoTTime;
    private double demolishdoTTime;
    private double fracturedoTTime;
    private double glStacks;
    private double chakraStacks;
    private double form;

    private boolean bootcrit;

    private boolean machinistHC;
    private boolean dragoonBL;


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

    private double dragonKickDamage;
    private double greasedLightningDamage;
    private double bFBDamage;
    private double potionDamage;
    private double iRDamage;
    private double twinSnakesDamage;

    private double todBfB;
    private double todIR;
    private double todGL;
    private double todPot;
    private double todTS;

    private double demoBfB;
    private double demoIR;
    private double demoGL;
    private double demoPot;
    private double demoTS;

    private double fractureBfB;
    private double fractureIR;
    private double fractureGL;
    private double fracturePot;
    private double fractureTS;

    private double statmultiplier;

    private double damage;

    private DefaultOpener defaultOpener;
    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String prevattack;
    private double demodoTpotency;
    private double fracturedoTpotency;
    private double toddoTpotency;
    private double recaster;

    private double recast;
    private double aaRecast;
    private ArrayList<Double> timers;
    private ArrayList<Double> timersForRotation;
    private Rotation rotation;
    private ArrayList<String> opener;
    private int openerNum;

    public Simulatorpart(ArrayList<Integer> mainstats, int time, boolean MCH, boolean DRG, String openerType, ArrayList<String> Opener) {
        stats = new ArrayList<>(mainstats);
        stats = mainstats;
        timer = time;
        rotation = new Rotation();
        defaultOpener = new DefaultOpener();
        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;


        }
        glStacks = 0;
        chakraStacks = 5;
        System.out.println(opener);
        openerNum = 0;
        cooldownReset();

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


        recaster = ((Math.floor((1 - ((Math.floor(((skillSpeed - 354) * 0.13 / 858) * 1000) / 1000))) * (2.5) * 100) / 100));
        aaRecast = 2.56;

    }

    public ArrayList<String> runSim() {
        double damagePerSecond;
        double currentTime = 0;
        ArrayList<String> damageLog = new ArrayList<>(150);
        while (currentTime <= timer) {
            recast = Math.floor(recaster*(1-(glStacks*0.05))*100)/100;
            setTimersForRotation();
            damage = 0;
            multipliers();



            if (nextGCD <= 0 || nextoGCD <= 0) {
                if (openerNum < opener.size()) {

                    if (openerTypeCheck().equals("OGCD") && nextoGCD <= 0) {
                        System.out.println(attack);
                        openerCheck();
                        skillUsed();
                        if (nextGCD < 0.7) {
                            nextGCD = 0.7;
                        }
                        nextoGCD = 0.7;


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

                    attack = rotation.getNextGCD(timersForRotation);
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

                        if (nextGCD < 0.7) {
                            nextGCD = 0.7;
                        }
                        nextoGCD = 0.7;
                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                    } else {
                        if (nextGCD < 0.7) {
                            nextGCD = 0.7;
                        }nextoGCD = 0.7;
                    }
                } else if (nextoGCD <= 0 && nextGCD < 1) {
                    nextoGCD = nextGCD;
                }
            }

            if (nextAA <= 0) {
                damage = damageMultiplier("Auto Attack");
                nextAA = Math.floor(aaRecast*(1 - (glStacks*0.05))*100)/100;
                damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
            }

            if (nextDoT <= 0) {
                double demodamage = 0;
                double toddamage = 0;
                double fracturedamage = 0;
                if (demolishdoTTime > 0) {
                    demodamage = damageMultiplier("Demolish DoT");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(demodamage * 100) / 100 + " Type: " + "Demolish DoT");
                }
                if (tODdoTTime > 0) {
                    toddamage = damageMultiplier("ToD DoT");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(toddamage * 100) / 100 + " Type: " + "ToD DoT");
                }
                if (fracturedoTTime > 0) {
                    fracturedamage = damageMultiplier("Fracture DoT");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(fracturedamage * 100) / 100 + " Type: " + "ToD DoT");
                }
                damage = damage + toddamage + demodamage + fracturedamage;
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


    private void openerCheck() {

        attack = opener.get(openerNum);


    }

    private String openerTypeCheck() {
        switch (opener.get(openerNum)) {
            case "Shoulder_Tackle":
            case "Internal_Release":
            case "Blood_for_Blood":
            case "Potion":
            case "Elixir_Field":
            case "Forbidden_Chakra":
            case "Steel_Peak":
            case "Howling_Fist":
            case "Perfect_Balance":
                return "OGCD";

            case "Bootshine":
            case "True_Strike":
            case "Touch_of_Death":
            case "Snap_Punch":
            case "Dragon_Kick":
            case "Twin_Snakes":
            case "Demolish":
            case "Fracture":
                return "Weapon_Skill";
        }
        return "NoN";
    }

    private void skillUsed() {
        Ability ability = new Ability();
        Warrior warriorCrossClass = new Warrior();
        WeaponSkills weaponSkills = new WeaponSkills();
        Dragoon dragoonCrossClass = new Dragoon();
        General potion = new General();
        type = null;

        potency = 0;


        if (attack.equals("Shoulder_Tackle")) {
            ability.shoulderTackle();
            type2 = "Ability";
            shoulderTackleTime = ability.getCooldown();
            potency = ability.getPotency();

            type = ability.getType();

        } else if (attack.equals("Elixir_Field")) {
            ability.elixirField();
            elixirFieldTime = ability.getCooldown();
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Forbidden_Chakra")) {
            ability.forbiddenChakra();
            chakraStacks = 0;
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";


        } else if (attack.equals("Steel_Peak")) {
            ability.steelPeak();
            steelPeakTime = ability.getCooldown();
            potency = ability.getPotency();

            type = ability.getType();

            type2 = "Ability";

        } else if (attack.equals("Howling_Fist")) {
            ability.howlingFist();
            howlingFistTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Perfect Balance")) {
            ability.perfectBalance();
            perfectBalanceTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Internal_Release")) {
            ability.internalRelease();
            iRTime = ability.getCooldown();
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
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
        } else if (attack.equals("Bootshine")) {
            weaponSkills.bootshine();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            bootcrit = true;
            form = 2;

        } else if (attack.equals("True_Strike")) {
            weaponSkills.trueStrike();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            form = 3;
        } else if (attack.equals("Snap_Punch")) {
            weaponSkills.snapPunch();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            glStacks++;
            if (glStacks > 3) {
                glStacks = 3;
            }
            glTime = 14;
            form = 1;
        } else if (attack.equals("Dragon_Kick")) {
            weaponSkills.dragonKick();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dragonKickTime = 15;
            type2 = "Weapon Skill";
            form = 2;
        } else if (attack.equals("Twin_Snakes")) {
            weaponSkills.twinSnakes();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            twinSnakesTime = 15;
            form = 3;
        } else if (attack.equals("Demolish")) {
            weaponSkills.demolish();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            demodoTpotency = weaponSkills.getDoTPotency();
            demolishdoTTime = weaponSkills.getDoTTime();
            demoBfB = bFBDamage;
            demoIR = iRDamage;
            demoGL = greasedLightningDamage;
            demoPot = potionDamage;
            demoTS = twinSnakesDamage;
            type2 = "Weapon Skill";
            glStacks++;
            if (glStacks > 3) {
                glStacks = 3;
            }
            glTime = 14;
            form = 1;

        } else if (attack.equals("Touch_of_Death")) {
            weaponSkills.touchOfDeath();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            toddoTpotency = weaponSkills.getDoTPotency();
            tODdoTTime = weaponSkills.getDoTTime();
            todBfB = bFBDamage;
            todIR = iRDamage;
            todGL = greasedLightningDamage;
            todPot = potionDamage;
            todTS = twinSnakesDamage;
            type2 = "Weapon Skill";
        } else if(attack.equals("Fracture")){
            warriorCrossClass.fracture();
            type = warriorCrossClass.getType();
            potency = warriorCrossClass.getPotency();
            fracturedoTpotency = warriorCrossClass.getDoTPotency();
            fracturedoTTime = warriorCrossClass.getDoTTime();
            fractureBfB = bFBDamage;
            fractureIR = iRDamage;
            fractureGL = greasedLightningDamage;
            fracturePot = potionDamage;
            fractureTS = twinSnakesDamage;
            type2 = "Weapon Skill";
        }


    }


    private void cooldownReset() {
        bFBTime = 0;
        iRTime = 0;
        twinSnakesTime = 0;
        shoulderTackleTime = 0;
        steelPeakTime = 0;
        howlingFistTime = 0;
        glTime = 0;
        dragonKickTime = 0;
        perfectBalanceTime = 0;
        elixirFieldTime = 0;
        nextAA = 0;
        nextDoT = 0;
        nextGCD = 0;
        nextoGCD = 0;
        demolishdoTTime = 0;
        tODdoTTime = 0;

    }

    private void multipliers() {
        if (bFBTime <= 80 && bFBTime >= 60) {
            bFBDamage = 1.1;
        } else bFBDamage = 1;

        if (dragonKickDamage > 0) {
            dragonKickDamage = 1.1;
        } else dragonKickDamage = 1;

        if (potionTime <= 270 && potionTime >= 255) {
            potionDamage = (154 + mainstat) / mainstat;
        } else potionDamage = 1;

        if (twinSnakesTime > 0) {
            twinSnakesDamage = 1.1;
        } else twinSnakesDamage = 1;

        if (glTime > 0) {
            greasedLightningDamage = 1 + (glStacks * 0.1);
        }else greasedLightningDamage = 1;

        if (iRTime <= 60 && iRTime >= 45) {
            iRDamage = 0.3;

        } else iRDamage = 0;
    }

    private double damageMultiplier(String type) {
        int multiplier = 1;
        if (type.equals("Physical")) {
            if (bootcrit) {
                bootcrit = false;
                return (1 + (((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * dragonKickDamage * twinSnakesDamage * greasedLightningDamage * 1.05;
            } else {
                return (1 + ((((crit - 354) / (858 * 5)) + 0.05 + iRDamage) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * bFBDamage * potionDamage * dragonKickDamage * twinSnakesDamage * greasedLightningDamage * 1.05;
            }

        } else if (type.equals("Auto Attack")) {
            {

                return ((1 + ((((crit - 354) / (858 * 5)) + 0.05 + iRDamage) * ((crit - 354) / (858 * 5) + 0.45))) * (weaponDamage / (3 / 2.56) * 0.0593365489928915 + 1) * (mainstat * 0.0841892) * (determination / 6832.8 + 1) * bFBDamage * twinSnakesDamage * potionDamage * dragonKickDamage * greasedLightningDamage * 1.05);

            }
        } else if (type.equals("ToD DoT")) {
            return toddoTpotency * (1 + ((((crit - 354) / (858 * 5)) + 0.05 + todIR) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * todBfB * todTS * todPot * todGL * 1.05 * ((skillSpeed) / 6800 + 1);

        } else if (type.equals("Demolish DoT")) {

            return demodoTpotency * (1 + ((((crit - 354) / (858 * 5)) + 0.05 + demoIR) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * demoBfB * demoTS * demoPot * demoGL * 1.05 * ((skillSpeed) / 6800 + 1);
        } else if(type.equals("Fracture DoT")){
            return fracturedoTpotency * (1 + ((((crit - 354) / (858 * 5)) + 0.05 + fractureIR) * ((crit - 354) / (858 * 5) + 0.45))) * statmultiplier * fractureBfB * fractureTS * fracturePot * fractureGL * 1.05 * ((skillSpeed) / 6800 + 1);
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
        twinSnakesTime = twinSnakesTime - change;
        shoulderTackleTime = shoulderTackleTime - change;
        glTime = glTime - change;
        steelPeakTime = steelPeakTime - change;
        dragonKickTime = dragonKickTime - change;
        elixirFieldTime = elixirFieldTime - change;
        howlingFistTime = howlingFistTime - change;
        perfectBalanceTime = perfectBalanceTime - change;
        machinistTime = machinistTime - change;
        dragoonTime = dragoonTime - change;
        potionTime = potionTime - change;
        demolishdoTTime = demolishdoTTime - change;
        tODdoTTime = tODdoTTime - change;
        fracturedoTTime = fracturedoTTime - change;
        if(glTime <= 0){
            glStacks = 0;
        }

    }

    private void setTimersForRotation() {
        timersForRotation = new ArrayList<>(18);
        timersForRotation.add(bFBTime);
        timersForRotation.add(iRTime);
        timersForRotation.add(twinSnakesTime);
        timersForRotation.add(shoulderTackleTime);
        timersForRotation.add(glTime);
        timersForRotation.add(steelPeakTime);
        timersForRotation.add(dragonKickTime);
        timersForRotation.add(elixirFieldTime);
        timersForRotation.add(howlingFistTime);
        timersForRotation.add(perfectBalanceTime);
        timersForRotation.add(potionTime);
        timersForRotation.add(demolishdoTTime);
        timersForRotation.add(tODdoTTime);
        timersForRotation.add(recast);
        timersForRotation.add(nextGCD);
        timersForRotation.add(form);
        timersForRotation.add(fracturedoTTime);
        timersForRotation.add(chakraStacks);
    }


}
