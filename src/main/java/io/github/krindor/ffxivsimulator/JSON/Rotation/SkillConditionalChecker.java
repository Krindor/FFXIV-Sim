package io.github.krindor.ffxivsimulator.JSON.Rotation;

import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

import java.util.Arrays;
import java.util.LinkedList;

public class SkillConditionalChecker {
    private String name;
    private Skill[] skill;
    private LinkedList<Skill> rotationList;
    //Turns the Array into a LinkedList to get better access to methods
    public void makeArray() {
        rotationList = new LinkedList<>(Arrays.asList(skill));

    }
    //Checks for the next skill and returns a string with the name of the skill
    public String getNext(AllBuffs allBuffs) {
        for (Skill array : rotationList) {
            if (array.passes(allBuffs)) {
                return array.getName();
            }
        }
        return "Error in Rotation";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Skill[] getSkill() {
        return skill;
    }

    public void setSkill(Skill[] skill) {
        this.skill = skill;
    }

    public LinkedList<Skill> getRotationList() {
        return rotationList;
    }

    public void setRotationList(LinkedList<Skill> rotationList) {
        this.rotationList = rotationList;
    }
}

