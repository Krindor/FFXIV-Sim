package io.github.krindor.ffxivsimulator.skills;

public enum PugilistSkill {
    Bootshine(2.5, 0, false),
    True_Strike(2.5, 0, false),
    Featherfoot(90, 0, true),
    Snap_Punch(2.5, 0, false),
    Second_Wind(120, 0, true),
    Haymaker(2.5, 0, false),
    Internal_Release(60, 0, true),
    Fists_Of_Earth(3, 0, true),
    Twin_Snakes(2.5, 0, false),
    Arm_Of_The_Destroyer(2.5, 0, false),
    Steel_Peak(60, 0, true),
    Mantra(120, 0, true),
    Demolish(2.5, 0, false),
    Howling_Fist(60, 0, true),
    Touch_Of_Death(2.5, 0, false),
    Perfect_Balance(180, 0, true),
    Fists_Of_Wind(3, 0, true);

    private final Skill skill;

    PugilistSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
