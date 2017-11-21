package io.github.krindor.ffxivsimulator.model;

public class StatModel {
    private final int weaponDamage;
    private final int mainStat;
    private final int determination;
    private final int criticalHitRating;
    private final int skillSpeed;
    private final int spellSpeed;
    private final int jobMod;
    private final int directHit;

    public StatModel(int weaponDamage, int mainStat, int determination, int criticalHitRating, int skillSpeed, int spellSpeed, int jobMod, int directHit) {
        this.weaponDamage = weaponDamage;
        this.mainStat = mainStat;
        this.determination = determination;
        this.criticalHitRating = criticalHitRating;
        this.skillSpeed = skillSpeed;
        this.spellSpeed = spellSpeed;
        this.jobMod = jobMod;
        this.directHit = directHit;
    }

    public double getWeaponDamage() {
        return weaponDamage;
    }

    public double getMainStat() {
        return mainStat;
    }

    public double getDetermination() {
        return determination;
    }

    public int getDirectHit() {
        return directHit;
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

    public int getJobMod() {
        return jobMod;
    }
}
