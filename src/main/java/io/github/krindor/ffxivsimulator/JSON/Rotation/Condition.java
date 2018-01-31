package io.github.krindor.ffxivsimulator.JSON.Rotation;

import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

public class Condition {
    private String name;
    private String compare;
    private double value;
    private BuffBarNames type;



    public String getName() {
        return name;
    }

    public String getCompare() {
        return compare;
    }

    public double getValue() {
        return value;
    }


//Tests the members to check if the condition matches the current state, returns true if it matches
    public boolean compare(AllBuffs buffs) {
        boolean check = false;

        if (value != -1) {
            double duration = buffs.getBuff(type, name).getDuration();
            switch (compare) {
                case "Boolean":
                if (value == 1) {
                    check = buffs.buffExists(type, name);
                } else if (value == 0) {
                    check = !buffs.buffExists(type, name);
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
