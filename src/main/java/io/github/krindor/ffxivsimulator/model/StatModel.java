package io.github.krindor.ffxivsimulator.model;

public class StatModel {
    public StatModel(int weaponDamage, int mainStat, int determination, int criticalHitRating, int skillSpeed, int spellSpeed) {
        this.weaponDamage = weaponDamage;
        this.mainStat = mainStat;
        this.determination = determination;
        this.criticalHitRating = criticalHitRating;
        this.skillSpeed = skillSpeed;
        this.spellSpeed = spellSpeed;
    }

    private final int weaponDamage;
    private final int mainStat;
    private final int determination;
    private final int criticalHitRating;
    private final int skillSpeed;
    private final int spellSpeed;

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
