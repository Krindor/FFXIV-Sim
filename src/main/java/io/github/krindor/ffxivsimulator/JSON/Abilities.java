package io.github.krindor.ffxivsimulator.JSON;

/**
 * Created by andre on 2017-07-14.
 */
public class Abilities {
    String name;
    int cooldown;
    int potency;
    String buff;
    String type;
    String type2;
    int specialValue;

    public String getName() {
        return name;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getPotency() {
        return potency;
    }

    public String getBuff() {
        return buff;
    }

    public String getType() {
        return type;
    }

    public String getType2() {
        return type2;
    }

    public int getSpecialValue() {
        return specialValue;
    }
}
