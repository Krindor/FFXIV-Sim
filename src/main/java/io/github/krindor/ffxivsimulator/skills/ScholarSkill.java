package io.github.krindor.ffxivsimulator.skills;

public enum ScholarSkill {
    Adloquium(2.5, 2, false),
    Succor(2.5, 2.5, false),
    Leeches(2.5, 1, false),
    Sacred_Soil(30, 0, true),
    Lustrate(1, 0, true),
    Indomitability(30, 0, true),
    Broil(2.5, 2.5, false),
    Deployment_Tactics(120, 0, true),
    Emergency_Tactics(30, 0, true),
    Dissipation(180, 0, true);

    private final Skill skill;

    ScholarSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
