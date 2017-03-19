package io.github.krindor.ffxivsimulator.skills;

public enum BardSkill {
    Mages_Ballad(2.5, 1.5, false),
    Foe_Requiem(2.5, 1.5, false),
    Armys_Paeon(2.5, 1.5, false),
    Rain_Of_Death(15, 0, true),
    Battle_Voice(180, 0, true),
    Empyreal_Arrow(15, 0, false),
    The_Wanderers_Minuet(15, 0, true),
    Iron_Jaws(2.5, 0, false),
    The_Wardens_Paean(45, 0, true),
    Sidewinder(60, 0, true);

    private final Skill skill;

    BardSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
