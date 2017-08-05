package io.github.krindor.ffxivsimulator.JSON.SkillDB;

/**
 * Created by andre on 2017-07-09.
 */
public class Job {
    String name;
    Buffs[] buffs;
    Skills[] skills;
    Abilities[] abilities;

    public String getName() {
        return name;
    }

    public Buffs[] getBuffs() {
        return buffs;
    }

    public Skills[] getSkills() {
        return skills;
    }

    public Abilities[] getAbilities() {
        return abilities;
    }
}
