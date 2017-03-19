package io.github.krindor.ffxivsimulator.skills;

public enum ConjurerSkill {
    Stone(2.5, 2.5, false),
    Cure(2.5, 2, false),
    Aero(2.5, 0, false),
    Cleric_Stance(5, 0, true),
    Protect(2.5, 3, false),
    Medica(2.5, 2.5, false),
    Raise(2.5, 8, false),
    Esuna(2.5, 1, false),
    Stone_II(2.5, 2.5, false),
    Repose(2.5, 2.5, false),
    Stoneskin(2.5, 3, false),
    Shroud_Of_Saints(120, 0, true),
    Cure_III(2.5, 2, false),
    Aero_II(2.5, 2.5, false),
    Medica_II(2.5, 3, false),
    Fluid_Aura(30, 0, true),
    Cure_II(2.5, 2, false),
    Stoneskin_II(2.5, 6, false);

    private final Skill skill;

    ConjurerSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
