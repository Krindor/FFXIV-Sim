package io.github.krindor.ffxivsimulator.JSON.Rotation;

import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

public class RotationCondition {
    private String name;
    private String compare;
    private double value;
    private String notBoolean;
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

    public String getNotBoolean() {
        return notBoolean;
    }

    public void changeStringToEnum() {
        buffType = BuffBarNames.valueOf(type);
    }

    public boolean compare(AllBuffs buffs, String prevAttack) {
        boolean check = false;
        if (prevAttack.equals("null") && prevAttack.equals(comboSkill)){
            check = true;
        }
        if (value != -1) {
            double duration = buffs.getBuff(buffType, name).getDuration();
            switch (compare) {
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
        if (notBoolean.equals("True")) {
            check = buffs.buffExists(buffType, name);
        } else if (notBoolean.equals("False")) {
            check = !buffs.buffExists(buffType, name);
        }
        return check;
    }
}
