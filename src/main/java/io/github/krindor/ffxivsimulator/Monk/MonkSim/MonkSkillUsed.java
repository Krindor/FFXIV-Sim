package io.github.krindor.ffxivsimulator.Monk.MonkSim;

import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Monk;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Warrior;
import io.github.krindor.ffxivsimulator.Monk.Skills.Ability;
import io.github.krindor.ffxivsimulator.Monk.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.DamageOverTime;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Resources;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class MonkSkillUsed {
    private String type;
    private String specialType;
    private int potency;
    private String attack;
    private String type2;
    private BuffsDebuffs timers;
    private BuffsDebuffs state;
    private Resources resources;

    private ArrayList<DamageOverTime> dotsArray;

    public void skillUsed(String type, String specialType, int potency, String attack, String type2, BuffsDebuffs timers, BuffsDebuffs state, ArrayList<DamageOverTime> dotsArray, Resources resources)

    {
        this.attack = attack;
        this.dotsArray = dotsArray;
        this.potency = potency;
        this.resources = resources;
        this.specialType = specialType;
        this.state = state;
        this.timers = timers;
        this.type = type;
        this.type2 = type2;
        useSkill();
    }
    private void useSkill(){
        Ability ability = new Ability();
        Warrior warriorCrossClass = new Warrior();
        WeaponSkills weaponSkills = new WeaponSkills();
        Dragoon dragoonCrossClass = new Dragoon();
        General potion = new General();



        if (attack.equals("Shoulder_Tackle")) {
            ability.shoulderTackle();
            type2 = "Ability";
            timers.setShoulderTackle(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();

        } else if (attack.equals("Elixir_Field")) {
            ability.elixirField();
            timers.setElixirField(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Forbidden_Chakra")) {
            ability.forbiddenChakra();
            state.setChakraStacks(0);
            potency = ability.getPotency();

            type = ability.getType();
            type2 = "Ability";


        } else if (attack.equals("Steel_Peak")) {
            ability.steelPeak();
            timers.setSteelPeak(ability.getCooldown());
            potency = ability.getPotency();

            type = ability.getType();

            type2 = "Ability";

        } else if (attack.equals("Howling_Fist")) {
            ability.howlingFist();
            timers.setHowlingFist(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Perfect Balance")) {
            ability.perfectBalance();
            timers.setPerfectBalance(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Internal_Release")) {
            ability.internalRelease();
            timers.setInternalRelease(ability.getCooldown());
            potency = ability.getPotency();
            type2 = "Ability";
            type = ability.getType();
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
        } else if (attack.equals("Bootshine")) {
            weaponSkills.bootshine();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            state.setBootshine(true);
            state.setForm(2);
            specialType = "Bootshine";

        } else if (attack.equals("True_Strike")) {
            weaponSkills.trueStrike();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            state.setForm(3);
        } else if (attack.equals("Snap_Punch")) {
            weaponSkills.snapPunch();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";

            if (state.getGlStacks() < 3) {
                state.setGlStacks(state.getGlStacks() + 1);
            }
            timers.setGlTime(14);
            state.setForm(1);
        } else if (attack.equals("Dragon_Kick")) {
            weaponSkills.dragonKick();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            timers.setDragonKick(15);
            type2 = "Weapon Skill";
            state.setForm(2);
        } else if (attack.equals("Twin_Snakes")) {
            weaponSkills.twinSnakes();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            type2 = "Weapon Skill";
            timers.setTwinSnakes(15);
            state.setForm(3);
        } else if (attack.equals("Demolish")) {
            weaponSkills.demolish();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dotsArray.get(0).setTime(weaponSkills.getDoTTime());
            dotsArray.get(0).setBloodForBlood(state.getBloodForBlood());
            dotsArray.get(0).setInternalRelease(state.getInternalRelease());
            dotsArray.get(0).setTrickAttack(state.getTrickAttack());
            dotsArray.get(0).setPotion(state.getPotion());
            type2 = "Weapon Skill";
            if (state.getGlStacks() < 3) {
                state.setGlStacks(state.getGlStacks() + 1);
            }
            timers.setGlTime(14);
            state.setForm(1);

        } else if (attack.equals("Touch_of_Death")) {
            weaponSkills.touchOfDeath();
            type = weaponSkills.getType();
            potency = weaponSkills.getPotency();
            dotsArray.get(1).setTime(weaponSkills.getDoTTime());
            dotsArray.get(1).setBloodForBlood(state.getBloodForBlood());
            dotsArray.get(1).setInternalRelease(state.getInternalRelease());
            dotsArray.get(1).setTrickAttack(state.getTrickAttack());
            dotsArray.get(1).setPotion(state.getPotion());
            type2 = "Weapon Skill";
        } else if(attack.equals("Fracture")){
            warriorCrossClass.fracture();
            type = warriorCrossClass.getType();
            potency = warriorCrossClass.getPotency();
            dotsArray.get(2).setTime(weaponSkills.getDoTTime());
            dotsArray.get(2).setBloodForBlood(state.getBloodForBlood());
            dotsArray.get(2).setInternalRelease(state.getInternalRelease());
            dotsArray.get(2).setTrickAttack(state.getTrickAttack());
            dotsArray.get(2).setPotion(state.getPotion());
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

    public Resources getResources() {
        return resources;
    }
}
