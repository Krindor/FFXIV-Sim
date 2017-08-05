package io.github.krindor.ffxivsimulator.JSON.SkillDB;

/**
 * Created by andre on 2017-07-13.
 */
public class Skills {
    String name;
    int potency;
    String type;
    String type2;
    String buff;
    int buffTime;
    int dotPotency;
    int dotTime;

    public String getName() {
        return name;
    }

    public int getPotency() {
        return potency;
    }

    public String getType() {
        return type;
    }

    public String getType2() {
        return type2;
    }

    public String getBuff() {
        return buff;
    }

    public int getBuffTime() {
        return buffTime;
    }

    public int getDotPotency() {
        return dotPotency;
    }

    public int getDotTime() {
        return dotTime;
    }
}
