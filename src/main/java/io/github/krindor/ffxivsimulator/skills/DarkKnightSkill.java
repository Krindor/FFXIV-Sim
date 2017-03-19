package io.github.krindor.ffxivsimulator.skills;

public enum DarkKnightSkill {
    Hard_Slash(2.5, 0, false),
    Shadowskin(90, 0, true),
    Spinning_Slash(2.5, 0, false),
    Scourge(2.5, 0, false),
    Unleash(2.5, 0, false),
    Low_Blow(25, 0, true),
    Syphon_Strike(2.5, 0, false),
    Unmend(2.5, 0, false),
    Blood_Weapon(60, 0, true),
    Reprisal(60, 0, true),
    Power_Slash(2.5, 0, false),
    Darkside(5, 0, true),
    Grit(2.5, 0, false),
    Dark_Dance(60, 0, true),
    Blood_Price(40, 0, true),
    Souleater(2.5, 0, false),
    Dark_Passenger(30, 0, true),
    Dark_Mind(60, 0, true),
    Dark_Arts(3, 0, true),
    Shadow_Wall(180, 0, true),
    Delirium(2.5, 0, false),
    Living_Dead(300, 0, true),
    Salted_Earth(45, 0, true),
    Plunge(30, 0, true),
    Abyssal_Drain(2.5, 0, false),
    Sole_Survivor(120, 0, true),
    Carve_And_Spit(60, 0, true);

    private final Skill skill;

    DarkKnightSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
