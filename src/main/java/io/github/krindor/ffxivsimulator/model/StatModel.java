package io.github.krindor.ffxivsimulator.model;

public class StatModel {
    public StatModel(double weaponDamage, double mainStat, double determination, double criticalHitRating, double skillSpeed, double spellSpeed) {
        this.weaponDamage = weaponDamage;
        this.mainStat = mainStat;
        this.determination = determination;
        this.criticalHitRating = criticalHitRating;
        this.skillSpeed = skillSpeed;
        this.spellSpeed = spellSpeed;
    }

    private final double weaponDamage;
    private final double mainStat;
    private final double determination;
    private final double criticalHitRating;
    private final double skillSpeed;
    private final double spellSpeed;

    public double getWeaponDamage() {
        return weaponDamage;
    }

    public double getMainStat() {
        return mainStat;
    }

    public double getDetermination() {
        return determination;
    }

    public double getCriticalHitRating() {
        return criticalHitRating;
    }

    public double getSkillSpeed() {
        return skillSpeed;
    }

    public double getSpellSpeed() {
        return spellSpeed;
    }
}
