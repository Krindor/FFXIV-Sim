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

    public int getWeaponDamage() {
        return weaponDamage;
    }

    public int getMainStat() {
        return mainStat;
    }

    public int getDetermination() {
        return determination;
    }

    public int getCriticalHitRating() {
        return criticalHitRating;
    }

    public int getSkillSpeed() {
        return skillSpeed;
    }

    public int getSpellSpeed() {
        return spellSpeed;
    }
}
