package io.github.krindor.ffxivsimulator.skills;

public enum PaladinSkill {
    Sword_Oath(2.5, 0, false),
    Cover(120, 0, true),
    Shield_Oath(2.5, 0, false),
    Spirits_Within(30, 0, true),
    Hallowed_Ground(420, 0, true),
    Goring_Blade(2.5, 0, false),
    Royal_Authority(2.5, 0, false),
    Divine_Veil(120, 0, true),
    Clemency(2.5, 2, false),
    Sheltron(30, 0, true);

    private final Skill skill;

    PaladinSkill(double recastTime, double castTime, boolean isOGCD) {
        this.skill = new Skill(this.name(), recastTime, castTime, isOGCD);
    }

    public Skill getSkill() {
        return skill;
    }
}
