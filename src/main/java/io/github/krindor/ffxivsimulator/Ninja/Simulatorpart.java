package io.github.krindor.ffxivsimulator.Ninja;

import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Monk;
import io.github.krindor.ffxivsimulator.Ninja.Priority.DefaultOpener;
import io.github.krindor.ffxivsimulator.Ninja.Priority.Rotation;
import io.github.krindor.ffxivsimulator.Ninja.Skills.Ability;
import io.github.krindor.ffxivsimulator.Ninja.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.DamageOverTime;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.NextAttack;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.ArrayList;

/**
 * Created by andre on 2017-02-08.
 */
public class Simulatorpart {


    private StatModel stats;


    private double time;

    private BuffsDebuffs timers;
    private BuffsDebuffs state;

    private boolean machinistHC;
    private boolean dragoonBL;
    private boolean warrior;

    private double totalDamage;



    private DamageOverTime SF;
    private DamageOverTime Mut;

    private double statmultiplier;

    private double damage;

    private DefaultOpener defaultOpener;
    private String attack;
    private int potency;
    private String type;
    private String type2;
    private String mudraType;
    private String prevattack;

    private double recast;
    private double aaRecast;
    private NextAttack nextAttack;
    private ArrayList<Double> timersForRotation;
    private Rotation rotation;
    private ArrayList<String> opener;
    private int openerNum;

    public Simulatorpart(StatModel stats, int time, boolean MCH, boolean DRG, boolean WAR, String openerType, int hutonBeforePull, ArrayList<String> Opener) {

        this.stats = stats;
        this.time = time;
        rotation = new Rotation();
        defaultOpener = new DefaultOpener();

        if (openerType.equals("Default")) {
            opener = defaultOpener.getOpener();
        } else {
            opener = Opener;
        }
        SF = new DamageOverTime(40, stats, 105);
        Mut = new DamageOverTime(30, stats, 105);

        openerNum = 0;
        cooldownReset();

        timers = new BuffsDebuffs(0);
        state = new BuffsDebuffs();

        machinistHC = MCH;
        dragoonBL = DRG;
        statmultiplier = (1 + (stats.getWeaponDamage() * 0.0432544)) * (stats.getMainStat() * 0.1027246) * (1 + ((double) this.stats.getDetermination() / 7290)) / 100;

        nextAttack = new NextAttack();



        if (WAR) {
            timers.setDancingEdge(time + 1);
        } else timers.setDancingEdge(0);
        timers.setHutonTime(70 - hutonBeforePull);
        timers.setMudra(20 - hutonBeforePull);

        recast = Math.floor(((Math.floor((1 - ((Math.floor((((double) this.stats.getSkillSpeed() - 354) * 0.13 / 858) * 1000) / 1000))) * (2.5) * 100) / 100) * 0.85) * 100) / 100;
        aaRecast = Math.floor((2.56 * 0.85) * 100) / 100;


    }

    public ArrayList<String> runSim() {
        double damagePerSecond;
        double currentTime = 0;

        ArrayList<String> damageLog = new ArrayList<>(150);
        while (currentTime <= time) {
            setTimersForRotation();
            damage = 0;
            multipliers();
            if (nextAttack.getNextGCD() <= 0 || nextAttack.getNextOGCD() <= 0) {
                if (openerNum < opener.size()) {

                    if (openerTypeCheck().equals("OGCD") && nextAttack.getNextOGCD() <= 0) {
                        openerCheck();
                        skillUsed();

                        if (currentTime == 0 && attack.equals("Suiton")) {
                            nextAttack.setNextGCD(0.7);
                            nextAttack.setNextOGCD(0.7);
                        } else checkMudraStep();

                        if (potency > 0) {

                            damage = potency * damageMultiplier(type);
                        }

                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                        openerNum++;


                    } else if (openerTypeCheck().equals("Weapon_Skill") && nextAttack.getNextGCD() <= 0) {

                        openerCheck();
                        skillUsed();
                        prevattack = attack;
                        if (potency > 0) {
                            damage = (potency * damageMultiplier(type));
                        }
                        nextAttack.setNextGCD(recast);
                        nextAttack.setNextOGCD(0.7);
                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                        openerNum++;
                    } else nextAttack.setNextOGCD(0.5);
                } else if (nextAttack.getNextGCD() <= 0) {

                    attack = rotation.getNextGCD(timersForRotation, prevattack, timers);
                    skillUsed();
                    prevattack = attack;
                    if (potency > 0) {
                        damage = (potency * damageMultiplier(type));
                    }
                    nextAttack.setNextGCD(recast);
                    nextAttack.setNextOGCD(0.7);
                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);

                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() > 1) {

                    attack = rotation.getNextOGCD(timersForRotation, timers);
                    if (rotation.getNextOGCD(timersForRotation, timers) != null) {
                        skillUsed();
                        if (potency > 0) {
                            damage = (potency * damageMultiplier(type));
                        }

                        checkMudraStep();
                        damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + attack);
                    } else checkMudraStep();
                } else if (nextAttack.getNextOGCD() <= 0 && nextAttack.getNextGCD() < 1) {
                    nextAttack.setNextOGCD(nextAttack.getNextGCD());
                }
            }

            if (nextAttack.getNextAA() <= 0) {
                damage = damageMultiplier("Auto Attack");
                nextAttack.setNextAA(aaRecast);
                damageLog.add("[" + currentTime + "] Damage: " + Math.floor(damage * 100) / 100 + " Type: " + "Auto Attack");
            }

            if (nextAttack.getNextDoT() <= 0) {
                double sfdamage = 0;
                double mutdamage = 0;
                if (SF.getTime() > 0) {
                    sfdamage = SF.getDamage("Ninja");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(sfdamage * 100) / 100 + " Type: " + "SF DoT");
                }
                if (Mut.getTime() > 0) {
                    mutdamage = Mut.getDamage("Ninja");

                    damageLog.add("[" + currentTime + "] Damage: " + Math.floor(mutdamage * 100) / 100 + " Type: " + "Mut DoT");
                }
                damage = damage + mutdamage + sfdamage;
                nextAttack.setNextDoT(3);
            }

            nextAttack.setNextAttack();


            totalDamage = totalDamage + damage;

            currentTime = (Math.floor((currentTime + nextAttack.getNextAttack()) * 1000)) / 1000;
            timeChange(nextAttack.getNextAttack());


        }

        damagePerSecond = totalDamage / time;
        double dps=Math.floor(damagePerSecond);
        damageLog.add(Double.toString(dps));
        return damageLog;
    }

    private void checkMudraStep() {


        if (nextAttack.getNextGCD() < 1.7 && mudraType.equals("2-step")) {
            nextAttack.setNextGCD(1.7);
            nextAttack.setNextOGCD(1.7);
        } else if (nextAttack.getNextGCD() < 1.2 && mudraType.equals("1-step")) {
            nextAttack.setNextGCD(1.2);
            nextAttack.setNextOGCD(1.2);
        } else if (nextAttack.getNextGCD() < 2.2 && mudraType.equals("3-step")) {
            nextAttack.setNextGCD(2.2);
            nextAttack.setNextOGCD(2.2);
        } else {
            if (nextAttack.getNextGCD() < 0.5) {
                nextAttack.setNextGCD(0.7);
                nextAttack.setNextOGCD(0.7);
            } else nextAttack.setNextOGCD(0.7);

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
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();

        } else if (attack.equals("Raiton")) {
            ability.raiton();
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Suiton")) {
            ability.suiton();
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";
            state.setSuiton(true);
            timers.setSuitonTime(10);
            rotation.setSuitonUsed(state.isSuiton());


        } else if (attack.equals("Kassatsu")) {
            ability.kassatsu();
            timers.setKassatsuTime(ability.getCooldown());
            potency = ability.getPotency();
            state.setKassatsu(true);
            type = ability.getType();
            timers.setMudra(0);
            type2 = "Ability";

        } else if (attack.equals("Duality")) {
            ability.duality();
            timers.setDualityTime(ability.getCooldown());
            potency = ability.getPotency();
            state.setDuality(true);
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Dream_Within_a_Dream")) {
            ability.dreamWithinADream();
            timers.setDreamWithinADream( ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Mug")) {
            ability.mug();
            timers.setMug(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Jugulate")) {
            ability.jugulate();
            timers.setJugulate(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Trick_Attack")) {
            ability.trickAttack();
            timers.setTrickAttack(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
            state.setSuiton(false);
            rotation.setSuitonUsed(state.isSuiton());

        } else if (attack.equals("Internal_Release")) {
            monkCrossClass.internalRelease();
            timers.setInternalRelease(monkCrossClass.getCooldown());
            potency = monkCrossClass.getPotency();
            type2 = "Ability";
            type = monkCrossClass.getType();
        } else if (attack.equals("Blood_for_Blood")) {
            dragoonCrossClass.bloodForBlood();
            timers.setBloodForBlood(dragoonCrossClass.getCooldown());
            potency = dragoonCrossClass.getPotency();
            type2 = "Ability";
            type = dragoonCrossClass.getType();
        } else if (attack.equals("Potion")) {
            potion.potion();
            timers.setPotionTime(potion.getCooldown());
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
            timers.setDancingEdge(20);
            type2 = "Weapon Skill";
        } else if (attack.equals("Armor_Crush")) {
            weaponSkills.armorCrush();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            timers.setHutonTime(timers.getHutonTime() + 30);
            if (timers.getHutonTime() > 70) {
                timers.setHutonTime(70);
            }
            type2 = "Weapon Skill";
        } else if (attack.equals("Shadow_Fang")) {
            weaponSkills.shadowFang();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();

            SF.setTime(weaponSkills.getDoTTime());
            SF.setBloodForBlood(state.getBloodForBlood());
            SF.setInternalRelease(state.getInternalRelease());
            SF.setTrickAttack(state.getTrickAttack());
            if (timers.getPotionTime() >= 255 && timers.getPotionTime() <= 270){SF.setPotion(true);}
            else SF.setPotion(false);
            type2 = "Weapon Skill";

        } else if (attack.equals("Mutilate")) {
            weaponSkills.mutilate();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            Mut.setTime(weaponSkills.getDoTTime());
            Mut.setBloodForBlood(state.getBloodForBlood());
            Mut.setInternalRelease(state.getInternalRelease());
            Mut.setTrickAttack(state.getTrickAttack());
            if (timers.getPotionTime() >= 255 && timers.getPotionTime() <= 270){Mut.setPotion(true);}
            else Mut.setPotion(false);
            type2 = "Weapon Skill";
        }


    }


    private void cooldownReset() {
        Mut.setTime(0);
        SF.setTime(0);

    }

    private void multipliers() {
        if (timers.getBloodForBlood() <= 80 && timers.getBloodForBlood() >= 60) {
            state.setBloodForBlood(1.1);
        } else state.setBloodForBlood(1.0);

        if (timers.getDancingEdge() > 0) {
            state.setDancingEdge(1.1);
        } else state.setDancingEdge(1.0);

        if (timers.getPotionTime() <= 270 && timers.getPotionTime() >= 255) {
            state.setPotionTime((154.0 + stats.getMainStat()) / stats.getMainStat());
        } else state.setPotionTime(1.0);

        if (timers.getTrickAttack() <= 60 && timers.getTrickAttack() >= 50) {
            state.setTrickAttack(1.1);
        } else state.setTrickAttack(1.0);

        if (timers.getInternalRelease() <= 60 && timers.getInternalRelease() >= 45) {
            state.setInternalRelease(0.1);

        } else state.setInternalRelease(0.0);
    }

    private double damageMultiplier(String type) {
        int multiplier = 1;
        if (type.equals("Physical")) {

            if (state.isDuality() && type2.equals("Weapon Skill")) {

                state.setDuality(false);
                return statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getDancingEdge() * state.getTrickAttack() * 2 * 1.2;


            } else if (timers.getInternalRelease() <= 60 && timers.getInternalRelease() >= 45) {

                return (1 + (((((double)stats.getCriticalHitRating() - 354) / (858 * 5) + 0.05 + 0.1)) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getDancingEdge() * state.getTrickAttack() * 1.2;
            } else {
                return (1 + (((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.05) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getDancingEdge() * state.getTrickAttack() * 1.2;
            }
        } else if (type.equals("Magical")) {
            if (!mudraType.equals("NoN") && state.isKassatsu()) {
                state.setKassatsu(false);
                return (1 + ((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.45)) * statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getTrickAttack();

            } else if (timers.getInternalRelease() <= 60 && timers.getInternalRelease() >= 45) {
                return (1 + (((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.05 + 0.1) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getTrickAttack();

            } else {
                return (1 + (((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.05) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * statmultiplier * state.getBloodForBlood() * state.getPotionTime() * state.getTrickAttack();

            }
        } else if (type.equals("Auto Attack")) {
            if (timers.getInternalRelease() <= 60 && timers.getInternalRelease() >= 45) {
                return ((1 + (((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.05 + 0.1) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * (stats.getWeaponDamage() / (3 / 2.56) * 0.0593365489928915 + 1) * (stats.getMainStat() * 0.0841892) * ((double) stats.getDetermination() / 6832.8 + 1) * state.getBloodForBlood() * state.getPotionTime() * state.getDancingEdge() * state.getTrickAttack() * 1.2);
            } else {

                return ((1 + (((((double) stats.getCriticalHitRating() - 354) / (858 * 5)) + 0.05) * (((double) stats.getCriticalHitRating() - 354) / (858 * 5) + 0.45))) * (stats.getWeaponDamage() / (3 / 2.56) * 0.0593365489928915 + 1) * (stats.getMainStat() * 0.0841892) * ((double) stats.getDetermination() / 6832.8 + 1) * state.getBloodForBlood() * state.getPotionTime() * state.getDancingEdge() * state.getTrickAttack() * 1.2);

            }
        }


        return multiplier;
    }

    private void timeChange(double change) {
        nextAttack.timeChange(change);
        timers.timeChange(change);
        SF.timeChange(change);
        Mut.timeChange(change);


    }

    private void setTimersForRotation() {
        timersForRotation = new ArrayList<>(4);
        timersForRotation.add(SF.getTime());
        timersForRotation.add(Mut.getTime());
        timersForRotation.add(recast);
        timersForRotation.add(nextAttack.getNextGCD());
    }




}
