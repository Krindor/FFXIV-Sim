package io.github.krindor.ffxivsimulator.skills;

public enum AstrologianSkill {
    Draw(30, 0, true),
    Royal_Road(15, 0, true),
    Spread(30, 0, true),
    Redraw(30, 0, true),
    Benefic(2.5, 2, false),
    Aspected_Benefic(2.5, 0, false),
    Malefic(2.5, 2.5, false),
    Stella(2.5, 2.5, false),
    Malefic_II(2.5, 2.5, false),
    Combust(2.5, 0, false),
    Helios(2.5, 2.5, false),
    Aspected_Helios(2.5, 3, false),
    Exalted_Detriment(2.5, 1, false),
    Ascend(2.5, 8, false),
    Diurnal_Sect(5, 0, true),
    Nocturnal_Sect(5, 0, true),
    Lightspeed(150, 0, true),
    Luminiferous_Aether(120, 0, true),
    Combust_II(2.5, 2.5, false),
    Disable(60, 0, true),
    Benefic_II(2.5, 2, false),
    Time_Dilation(90, 0, true),
    Synastry(90, 0, true),
    Collective_Unconscious(90, 0, true),
    Essential_Dignity(60, 0, true),
    Gravity(2.5, 3, false),
    Celestial_Opposition(120, 0, true),
    The_Balance(30, 0, true),
    The_Bole(30, 0, true),
    The_Arrow(30, 0, true),
    The_Spear(30, 0, true),
    The_Ewer(30, 0, true),
    The_Spire(30, 0, true);

    private final Skill skill;

    AstrologianSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
