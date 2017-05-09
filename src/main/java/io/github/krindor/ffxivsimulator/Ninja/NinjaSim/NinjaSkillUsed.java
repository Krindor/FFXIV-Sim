package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Monk;
import io.github.krindor.ffxivsimulator.Ninja.Priority.Rotation;
import io.github.krindor.ffxivsimulator.Ninja.Skills.Ability;
import io.github.krindor.ffxivsimulator.Ninja.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.DamageOverTime;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaSkillUsed {
    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private BuffsDebuffs timers;
    private BuffsDebuffs state;

    private ArrayList<DamageOverTime> dotsArray;

    public void skillUsed(String type, String specialType, int potency, String attack, String type2, BuffsDebuffs timers, BuffsDebuffs state, ArrayList<DamageOverTime> dotsArray)

    {
        this.attack = attack;
        this.dotsArray = dotsArray;
        this.potency = potency;

        this.specialType = specialType;
        this.state = state;
        this.timers = timers;
        this.type = type;
        this.type2 = type2;
        useSkill();
    }
    private void useSkill(){
        Ability ability = new Ability();
        Monk monkCrossClass = new Monk();
        WeaponSkills weaponSkills = new WeaponSkills();
        Dragoon dragoonCrossClass = new Dragoon();
        General potion = new General();



        if (attack.equals("Fuma_Shuriken")) {
            ability.fuma_Shuriken();
            type2 = "Ability";
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            specialType = ability.getMudraType();
            type = ability.getType();

        } else if (attack.equals("Raiton")) {
            ability.raiton();
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            specialType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Suiton")) {
            ability.suiton();
            timers.setMudra(ability.getCooldown());
            potency = ability.getPotency();
            specialType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";
            state.setSuiton(true);
            timers.setSuitonTime(10);


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
            timers.setDreamWithinADream(ability.getCooldown());
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

            dotsArray.get(0).setTime(weaponSkills.getDoTTime());
            dotsArray.get(0).setBloodForBlood(state.getBloodForBlood());
            dotsArray.get(0).setInternalRelease(state.getInternalRelease());
            dotsArray.get(0).setTrickAttack(state.getTrickAttack());
            dotsArray.get(0).setPotion(state.getPotion());
            type2 = "Weapon Skill";

        } else if (attack.equals("Mutilate")) {
            weaponSkills.mutilate();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dotsArray.get(1).setTime(weaponSkills.getDoTTime());
            dotsArray.get(1).setBloodForBlood(state.getBloodForBlood());
            dotsArray.get(1).setInternalRelease(state.getInternalRelease());
            dotsArray.get(1).setTrickAttack(state.getTrickAttack());
            dotsArray.get(1).setPotion(state.getPotion());
            type2 = "Weapon Skill";
        }
    }

    public String getType() {
        return type;
    }

    public String getSpecialType() {
        return specialType;
    }

    public int getPotency() {
        return potency;
    }

    public String getAttack() {
        return attack;
    }

    public String getType2() {
        return type2;
    }

    public BuffsDebuffs getTimers() {
        return timers;
    }

    public BuffsDebuffs getState() {
        return state;
    }

    public ArrayList<DamageOverTime> getDotsArray() {
        return dotsArray;
    }
}
