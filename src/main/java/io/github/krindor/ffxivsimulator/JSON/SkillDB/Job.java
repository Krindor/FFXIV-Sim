package io.github.krindor.ffxivsimulator.JSON.SkillDB;

/**
 * Created by andre on 2017-07-09.
 */
public class Job {
    private String name;
    private Buffs[] buffs;
    private Abilities[] skills;
    private Abilities[] abilities;

    public void addEnum() {

        for (int i = 0; i < buffs.length; i++) {
            Buffs buffs1 = buffs[i];
            buffs1.stringToEnum();
            buffs[i] = buffs1;
        }
        for (int i = 0; i < skills.length; i++) {
            Abilities skills1 = skills[i];

            skills[i] = skills1;
        }
        for (int i = 0; i < abilities.length; i++) {
            Abilities abilities1 = abilities[i];

            abilities[i] = abilities1;
        }
    }

    public String getName() {
        return name;
    }

    public Buffs[] getBuffs() {
        return buffs;
    }

    public Abilities[] getSkills() {
        return skills;
    }

    public Abilities[] getAbilities() {
        return abilities;
    }
}
