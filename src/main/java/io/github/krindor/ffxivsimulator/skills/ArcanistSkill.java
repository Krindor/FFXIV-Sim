package io.github.krindor.ffxivsimulator.skills;

public enum ArcanistSkill {
    Ruin(2.5, 2.5, false),
    Bio(2.5, 0, false),
    Summon(2.5, 6, false),
    Aetherflow(60, 0, true),
    Energy_Drain(3, 0, true),
    Miasma(2.5, 2.5, false),
    Virus(90, 0, true),
    Summon_II(2.5, 6, false),
    Sustain(2.5, 1, false),
    Ruin_II(2.5, 0, false),
    Resurrection(2.5, 8, false),
    Bane(10, 0, true),
    Eye_For_An_Eye(180, 0, true),
    Rouse(90, 0, true),
    Miasma_II(2.5, 0, false),
    Bio_II(2.5, 2.5, false),
    Shadow_Flare(2.5, 3, false),
    Physick(2.5, 2, false);

    private final Skill skill;

    ArcanistSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
