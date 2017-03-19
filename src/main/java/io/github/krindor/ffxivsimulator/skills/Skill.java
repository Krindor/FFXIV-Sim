package io.github.krindor.ffxivsimulator.skills;

public class Skill {
    public Skill(String name, double recastTime, double castTime, boolean isOGCD) {
        this.name = name;
        this.recastTime = recastTime;
        this.castTime = castTime;
        this.isOGCD = isOGCD;
    }

    private final String name;
    private final double recastTime;
    private final double castTime;
    private final boolean isOGCD;

    /**
     * @return Skill Name
     */
    public String getName() {
        return name;
    }

    /**
     * @return Time for skill to be reusable.
     */
    public double getRecastTime() {
        return recastTime;
    }

    /**
     * @return Time it takes for skill to be cast.
     */
    public double getCastTime() {
        return castTime;
    }

    /**
     * @return Can be cast while during Global Cooldown.
     */
    public boolean isOGCD() {
        return isOGCD;
    }
}
