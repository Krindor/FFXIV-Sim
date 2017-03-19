package io.github.krindor.ffxivsimulator.skills;

public enum LancerSkill {
    True_Thrust(2.5, 0, false),
    Feint(2.5, 0, false),
    Keen_Flurry(90, 0, true),
    Vorpal_Thrust(2.5, 0, false),
    Heavy_Thrust(2.5, 0, false),
    Invigorate(120, 0, true),
    Impulse_Drive(2.5, 0, false),
    Leg_Sweep(30, 0, true),
    Life_Surge(90, 0, true),
    Full_Thrust(2.5, 0, false),
    Blood_For_Blood(80, 0, true),
    Doom_Spike(2.5, 0, false),
    Disembowel(2.5, 0, false),
    Chaos_Thrust(2.5, 0, false),
    Ring_Of_Thorns(2.5, 0, false),
    Piercing_Talon(2.5, 0, false),
    Phlebotomize(2.5, 0, false);

    private final Skill skill;

    LancerSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
