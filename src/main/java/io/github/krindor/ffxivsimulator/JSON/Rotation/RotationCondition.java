package io.github.krindor.ffxivsimulator.JSON.Rotation;

import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

public class RotationCondition {
    private String name;
    private String compare;
    private double value;
    private String type;
    private BuffBarNames buffType;
    private String comboSkill;

    public String getName() {
        return name;
    }

    public String getCompare() {
        return compare;
    }

    public double getValue() {
        return value;
    }

//Change type from being a string to enum
    public void changeStringToEnum() {
        buffType = BuffBarNames.valueOf(type);
    }
//Tests the members to check if the condition matches the current state, returns true if it matches
    public boolean compare(AllBuffs buffs, String prevAttack) {
        boolean check = false;
        if (prevAttack.equals("null") && prevAttack.equals(comboSkill)){
            check = true;
        }
        if (value != -1) {
            double duration = buffs.getBuff(buffType, name).getDuration();
            switch (compare) {
                case "Boolean":
                if (value == 1) {
                    check = buffs.buffExists(buffType, name);
                } else if (value == 0) {
                    check = !buffs.buffExists(buffType, name);
                }
                break;
                case ">":

                    if (value > duration) {
                        check = true;
                    }
                    break;
                case "<":
                    if (value < duration) {
                        check = true;
                    }
                    break;
                case "=":
                    if (value == duration) {
                        check = true;
                    }
                    break;
                case ">=":
                    if (value >= duration) {
                        check = true;
                    }
                    break;
                case "<=":
                    if (value <= duration) {
                        check = true;
                    }
                    break;
                case "!=":
                    if (value != duration) {
                        check = true;
                    }
                    break;

            }
        }

        return check;
    }
}
