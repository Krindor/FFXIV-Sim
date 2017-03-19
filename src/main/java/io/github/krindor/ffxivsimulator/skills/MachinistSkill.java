package io.github.krindor.ffxivsimulator.skills;

public enum MachinistSkill {
    Rook_Autoturret(10, 0, true),
    Bishop_Autoturret(10, 0, true),
    Split_Shot(2.5, 0, false),
    Reload(60, 0, true),
    Slug_Shot(2.5, 0, false),
    Lead_Shot(2.5, 0, false),
    Spread_Shot(2.5, 0, false),
    Grenado_Shot(2.5, 0, false),
    Hot_Shot(2.5, 0, false),
    Clean_Shot(2.5, 0, false),
    Gauss_Round(20, 0, true),
    Heartbreak(30, 0, true),
    Reassemble(90, 0, true),
    Leg_Graze(20, 0, true),
    Wildfire(90, 0, true),
    Quick_Reload(30, 0, true),
    Gauss_Barrel(15, 0, true),
    Rapid_Fire(90, 0, true),
    Rend_Mind(90, 0, true),
    Suppressive_Fire(25, 0, true),
    Foot_Graze(20, 0, true),
    Hypercharge(120, 0, true),
    Head_Graze(25, 0, true),
    Dismantle(90, 0, true),
    Blank(30, 0, true),
    Promotion(5, 0, true),
    Ricochet(60, 0, true),
    Turret_Retrieval(5, 0, true);

    private final Skill skill;

    MachinistSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
