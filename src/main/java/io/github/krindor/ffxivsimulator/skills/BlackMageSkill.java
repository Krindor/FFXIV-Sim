package io.github.krindor.ffxivsimulator.skills;

public enum BlackMageSkill {
    Convert(180, 0, true),
    Freeze(2.5, 3, false),
    Apocatastasis(180, 0, true),
    Manawall(120, 0, true),
    Flare(2.5, 4, false),
    Ley_Lines(90, 0, true),
    Sharpcast(60, 0, true),
    Enochian(60, 0, true),
    Blizzard_IV(2.5, 3, false),
    Fire_IV(2.5, 3, false);

    private final Skill skill;

    BlackMageSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
