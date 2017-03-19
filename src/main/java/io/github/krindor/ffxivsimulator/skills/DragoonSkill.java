package io.github.krindor.ffxivsimulator.skills;

public enum DragoonSkill {
    Jump(30, 0, true),
    Power_Surge(60, 0, true),
    Elusive_Jump(180, 0, true),
    Spineshatter_Dive(60, 0, true),
    Dragonfire_Dive(120, 0, true),
    Blood_Of_The_Dragon(60, 0, true),
    Fang_And_Claw(2.5, 0, false),
    Geirskogul(10, 0, true),
    Wheeling_Thrust(2.5, 0, false),
    Battle_Litany(180, 0, true);

    private final Skill skill;

    DragoonSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
