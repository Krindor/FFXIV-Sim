package io.github.krindor.ffxivsimulator.skills;

public enum ArcherSkill {
    Heavy_Shot(2.5, 0, false),
    Straight_Shot(2.5, 0, false),
    Hawks_Eye(90, 0, true),
    Venomous_Bite(2.5, 0, false),
    Raging_Strikes(180, 0, true),
    Flaming_Arrow(60, 0, true),
    Miserys_End(12, 0, true),
    Quelling_Strikes(120, 0, true),
    Swiftsong(2.5, 1, false),
    Quick_Nock(2.5, 0, false),
    Barrage(90, 0, true),
    Shadowbind(40, 0, true),
    Blunt_Arrow(30, 0, true),
    Bloodletter(15, 0, true),
    Wide_Volley(2.5, 0, false),
    Repelling_Shot(30, 0, true),
    Windbite(2.5, 0, false);

    private final Skill skill;

    ArcherSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }
}
