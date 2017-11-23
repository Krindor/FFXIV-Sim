package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

import java.util.Arrays;
import java.util.LinkedList;

public class Rotation {
    private RotationConditionArray[] rotationConditionArrays;
    private LinkedList<RotationConditionArray> rotationList;

    public void makeArray() {
        rotationList = new LinkedList<>(Arrays.asList(rotationConditionArrays));
    }

    public String getNext(AllBuffs allBuffs, String prevAttack) {
        for (RotationConditionArray array : rotationList) {
            if (array.passes(allBuffs, prevAttack)) {
                return array.getName();
            }
        }
        return "Error in Rotation";
    }
}
