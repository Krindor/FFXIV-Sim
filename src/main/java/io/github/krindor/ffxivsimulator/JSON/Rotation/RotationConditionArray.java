package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

public class RotationConditionArray {
    private String name;
    private RotationCondition[] rotationConditions;

    public RotationCondition[] getRotationConditions() {
        return rotationConditions;
    }

    public void changeEnum(){
        for (int i = 0; i< rotationConditions.length; i++){
            RotationCondition toEnum = rotationConditions[i];
            toEnum.changeStringToEnum();
            rotationConditions[i] = toEnum;
        }
    }

    public boolean passes(AllBuffs allBuffs){
        for (RotationCondition i: rotationConditions){
            if (!i.compare(allBuffs)){
                return false;
            }
        }
        return true;
    }


    public String getName() {
        return name;
    }
}
