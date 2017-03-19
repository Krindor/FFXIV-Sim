package io.github.krindor.ffxivsimulator.skills;

public enum SummonerSkill {
    Summon_III(2.5, 6, false),
    Fester(10, 0, true),
    Tribind(2.5, 3, false),
    Spur(120, 0, true),
    Enkindle(180, 0, true),
    Painflare(10, 0, true),
    Ruin_III(2.5, 2.5, false),
    Tridisaster(60, 0, true),
    Dreadwyrm_Trance(30, 0, true),
    Deathflare(30, 0, true);

    private final Skill skill;

    SummonerSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
