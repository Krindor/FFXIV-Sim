package io.github.krindor.ffxivsimulator.skills;

public enum WarriorSkill {
    Defiance(10, 0, true),
    Inner_Beast(2.5, 0, false),
    Unchained(120, 0, true),
    Steel_Cyclone(2.5, 0, false),
    Infuriate(60, 0, true),
    Deliverance(10, 0, true),
    Fell_Cleave(2.5, 0, false),
    Decimate(2.5, 0, false),
    Raw_Intuition(90, 0, true),
    Equilibrium(60, 0, true);

    private final Skill skill;

    WarriorSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
