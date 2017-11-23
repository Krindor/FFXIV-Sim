package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

class RotationConditionArray {
    private String name;
    private RotationCondition[] rotationConditions;

    public RotationCondition[] getRotationConditions() {
        return rotationConditions;
    }

    public void changeEnum() {
        for (int i = 0; i < rotationConditions.length; i++) {
            RotationCondition toEnum = rotationConditions[i];
            toEnum.changeStringToEnum();
            rotationConditions[i] = toEnum;
        }
    }

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
