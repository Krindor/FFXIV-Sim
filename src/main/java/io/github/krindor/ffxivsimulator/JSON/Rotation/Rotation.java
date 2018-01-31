package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class that contains the full conditional rotation, built to bind a JSON rotation file to it
 */
public class Rotation {
    private RotationSkill[] rotationConditionArrays;
    private LinkedList<RotationSkill> rotationList;
    //Turns the Array into a LinkedList to get better access to methods
    public void makeArray() {
        rotationList = new LinkedList<>(Arrays.asList(rotationConditionArrays));
        for (int i = 0; i < rotationList.size(); i++){
            RotationSkill conditionArray = rotationList.get(i);
            conditionArray.changeEnum();
            rotationList.add(i, conditionArray);
        }
    }
    //Checks for the next skill and returns a string with the name of the skill
    public String getNext(AllBuffs allBuffs, String prevAttack) {
        for (RotationSkill array : rotationList) {
            if (array.passes(allBuffs, prevAttack)) {
                return array.getName();
            }
        }
        return "Error in Rotation";
    }
}
