package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Timers.AllBuffs;
/*
Each instance of this class represents one skill
 */
class Skill {
    private String name;
    private Condition[] conditions;

    public Condition[] getConditions() {
        return conditions;
    }

//checks if all conditions matches the current state of the player, returns true if all conditions are true
    public boolean passes(AllBuffs allBuffs) {
        for (Condition i : conditions) {
            if (!i.compare(allBuffs)) {
                return false;
            }
        }
        return true;
    }


    public String getName() {
        return name;
    }
}
