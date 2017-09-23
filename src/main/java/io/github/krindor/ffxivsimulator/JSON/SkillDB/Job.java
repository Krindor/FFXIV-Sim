package io.github.krindor.ffxivsimulator.JSON.SkillDB;

/**
 * Created by andre on 2017-07-09.
 */
public class Job {
    String name;
    Buffs[] buffs;
    Skills[] skills;
    Abilities[] abilities;

    public void addEnumBuffs(){

        for (int i = 0; i<buffs.length; i++){
            Buffs buffs1 = buffs[i];
            buffs1.setTargetEnum();
            buffs[i] = buffs1;
        }
    }

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
