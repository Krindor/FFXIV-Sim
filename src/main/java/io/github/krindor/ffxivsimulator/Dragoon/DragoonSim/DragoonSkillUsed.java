package io.github.krindor.ffxivsimulator.Dragoon.DragoonSim;


import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.Dragoon.Skills.Ability;
import io.github.krindor.ffxivsimulator.Dragoon.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.DamageOverTime;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffBar;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class DragoonSkillUsed {
    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private BuffsDebuffs timers;
    private BuffsDebuffs state;
    private BuffBar buffBar;

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

    private void useSkill() {
        Ability ability = new Ability();
        WeaponSkills weaponSkills = new WeaponSkills();
        General potion = new General();


        if (attack.equals("Jump")) {
            ability.jump();
            type2 = "Ability";
            timers.setShoulderTackle(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();

        } else if (attack.equals("Spineshatter_Dive")) {
            ability.spineshatterDive();
            timers.setElixirField(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Dragonfire_Dive")) {
            ability.dragonfireDive();
            state.setChakraStacks(0);
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";


        } else if (attack.equals("Power_Surge")) {
            ability.powerSurge();
            timers.setSteelPeak(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();

            type2 = "Ability";

        } else if (attack.equals("Blood_of_the_Dragon")) {
            ability.bloodOfTheDragon();
            timers.setHowlingFist(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("geirskogul")) {
            ability.geirskogul();
            timers.setPerfectBalance(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Battle_Litany")) {
            ability.battleLitany();
            timers.setInternalRelease(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Blood_for_Blood")) {
            ability.bloodForBlood();
            timers.setBloodForBlood(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Potion")) {
            potion.potion();
            timers.setPotionTime(potion.getCooldown());
            potency = potion.getPotency();
            type2 = "Ability";
            type = potion.getType();
        } else if (attack.equals("True_Thrust")) {
            weaponSkills.trueThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";


        } else if (attack.equals("Vorpal_Thrust")) {
            weaponSkills.vorpalThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";

        } else if (attack.equals("Full_Thrust")) {
            weaponSkills.fullThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";


        } else if (attack.equals("Impulsive_Drive")) {
            weaponSkills.impulseDrive();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();

            type2 = "Weapon Skill";

        } else if (attack.equals("Disembowel")) {
            weaponSkills.disembowel();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";

        } else if (attack.equals("Chaos_Thrust")) {
            weaponSkills.chaosThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dotsArray.get(0).setTime(weaponSkills.getDoTTime());

            type2 = "Weapon Skill";


        } else if (attack.equals("Phlebotomize")) {
            weaponSkills.phlebotomize();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dotsArray.get(1).setTime(weaponSkills.getDoTTime());

            type2 = "Weapon Skill";


        } else if (attack.equals("Wheeling_Thrust")) {
            weaponSkills.wheelingThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";

        } else if (attack.equals("Heavy_Thrust")) {
            weaponSkills.heavyThrust();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
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
