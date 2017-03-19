package io.github.krindor.ffxivsimulator.skills;

public enum GladiatorSkill {
    Fast_Blade(2.5, 0, false),
    Rampart(90, 0, true),
    Savage_Blade(2.5, 0, false),
    Convalescence(120, 0, true),
    Awareness(120, 0, true),
    Flash(2.5, 0, false),
    Riot_Blade(2.5, 0, false),
    Shield_Bash(2.5, 0, false),
    Sentinel(180, 0, true),
    Provoke(40, 0, true),
    Tempered_Will(180, 0, true),
    Fight_Or_Flight(90, 0, true),
    Rage_Of_Halone(2.5, 0, false),
    Bulwark(180, 0, true),
    Circle_Of_Scorn(25, 0, true),
    Shield_Lob(2.5, 0, false),
    Shield_Swipe(15, 0, true);

    private final Skill skill;

    GladiatorSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
