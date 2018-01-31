package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
/*
Each instance of this class represents one skill
 */
class RotationSkill {
    private String name;
    private RotationCondition[] rotationConditions;

    public RotationCondition[] getRotationConditions() {
        return rotationConditions;
    }
//Using enums for skill types
    public void changeEnum() {
        for (int i = 0; i < rotationConditions.length; i++) {
            RotationCondition toEnum = rotationConditions[i];
            toEnum.changeStringToEnum();
            rotationConditions[i] = toEnum;
        }
    }
//checks if all conditions matches the current state of the player, returns true if all conditions are true
    public boolean passes(AllBuffs allBuffs, String prevAttack) {
        for (RotationCondition i : rotationConditions) {
            if (!i.compare(allBuffs, prevAttack)) {
                return false;
            }
        }
        return true;
    }


    public String getName() {
        return name;
    }
}
