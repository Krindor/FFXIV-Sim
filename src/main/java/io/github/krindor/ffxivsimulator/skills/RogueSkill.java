package io.github.krindor.ffxivsimulator.skills;

public enum RogueSkill {
    Spinning_Edge(2.5, 0, false),
    Shade_Shift(120, 0, true),
    Gust_Slash(2.5, 0, false),
    Kiss_Of_The_Wasp(5, 0, true),
    Mutilate(2.5, 0, false),
    Hide(20, 0, true),
    Assassinate(40, 0, true),
    Throwing_Dagger(2.5, 0, false),
    Mug(90, 0, true),
    Goad(180, 0, true),
    Sneak_Attack(60, 0, true),
    Dancing_Edge(2.5, 0, false),
    Kiss_Of_The_Viper(5, 0, true),
    Death_Blossom(2.5, 0, false),
    Aeolian_Edge(2.5, 0, false),
    Jugulate(30, 0, true),
    Shadow_Fang(2.5, 0, false),
    Trick_Attack(60, 0, true);

    private final Skill skill;

    RogueSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
