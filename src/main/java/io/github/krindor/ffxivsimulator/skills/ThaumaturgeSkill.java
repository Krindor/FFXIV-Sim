package io.github.krindor.ffxivsimulator.skills;

public enum ThaumaturgeSkill {
    Fire(2.5, 2.5, false),
    Blizzard(2.5, 2.5, false),
    Surecast(30, 0, true),
    Thunder(2.5, 2.5, false),
    Sleep(2.5, 2.5, false),
    Blizzard_II(2.5, 2, false),
    Fire_II(2.5, 3, false),
    Thunder_II(2.5, 3, false),
    Transpose(12, 0, true),
    Swiftcast(60, 0, true),
    Lethargy(30, 0, true),
    Fire_III(2.5, 3.5, false),
    Thunder_III(2.5, 3.5, false),
    Blizzard_III(2.5, 3.5, false),
    Aetherial_Manipulation(30, 0, true),
    Scathe(2.5, 0, false),
    Manaward(120, 0, true);

    private final Skill skill;

    ThaumaturgeSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
