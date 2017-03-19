package io.github.krindor.ffxivsimulator.skills;

public enum WhiteMageSkill {
    Presence_Of_Mind(150, 0, true),
    Regen(2.5, 0, false),
    Divine_Seal(60, 0, true),
    Holy(2.5, 3, false),
    Benediction(300, 0, true),
    Stone_III(2.5, 2.5, false),
    Asylum(90, 0, true),
    Tetragrammaton(60, 0, true),
    Assize(90, 0, true),
    Aero_III(2.5, 3, false);

    private final Skill skill;

    WhiteMageSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
