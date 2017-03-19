package io.github.krindor.ffxivsimulator.skills;

public enum MarauderSkill {
    Heavy_Swing(2.5, 0, false),
    Foresight(120, 0, true),
    Fracture(2.5, 0, false),
    Bloodbath(90, 0, true),
    Skull_Sunder(2.5, 0, false),
    Mercy_Stroke(90, 0, true),
    Maim(2.5, 0, false),
    Berserk(90, 0, true),
    Brutal_Swing(20, 0, true),
    Thrill_Of_Battle(120, 0, true),
    Overpower(2.5, 0, false),
    Storms_Path(2.5, 0, false),
    Holmgang(180, 0, true),
    Vengeance(120, 0, true),
    Storms_Eye(2.5, 0, false),
    Tomahawk(2.5, 0, false),
    Butchers_Block(2.5, 0, false);

    private final Skill skill;

    MarauderSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
