package io.github.krindor.ffxivsimulator.skills;

public enum MonkSkill {
    Fists_Of_Fire(3, 0, true),
    Rockbreaker(2.5, 0, false),
    Shoulder_Tackle(30, 0, true),
    One_Ilm_Punch(2.5, 0, false),
    Dragon_Kick(2.5, 0, false),
    Tornado_Kick(40, 0, true),
    Purification(120, 0, true),
    Elixir_Field(30, 0, true),
    Meditation(1.2, 0, true),
    The_Forbidden_Chakra(5, 0, true),
    Form_Shift(2.5, 0, false);

    private final Skill skill;

    MonkSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
