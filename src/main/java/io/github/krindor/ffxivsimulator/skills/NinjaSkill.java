package io.github.krindor.ffxivsimulator.skills;

public enum NinjaSkill {
    Ten(0.5, 0, true),
    Ninjutsu(20, 0, true),
    Chi(0.5, 0, true),
    Shukuchi(60, 0, true),
    Jin(0.5, 0, true),
    Kassatsu(120, 0, true),
    Fuma_Shuriken(20, 0, true),
    Katon(20, 0, true),
    Raiton(20, 0, true),
    Hyoton(20, 0, true),
    Huton(20, 0, true),
    Doton(20, 0, true),
    Suiton(20, 0, true),
    Rabbit_Medium(20, 0, true),
    Armor_Crush(2.5, 0, false),
    Shadewalker(120, 0, true),
    Smoke_Screen(180, 0, true),
    Dream_Within_A_Dream(90, 0, true),
    Duality(90, 0, true);

    private final Skill skill;

    NinjaSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
