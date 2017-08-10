package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;

import java.util.ArrayList;
import java.util.Collections;

public class AllBuffs {
    private BuffBar playerBuffs;
    private BuffBar partyBuffs;
    private BuffBar targetBuffs;

    public AllBuffs() {
        playerBuffs = new BuffBar();
        partyBuffs = new BuffBar();
        targetBuffs = new BuffBar();
    }

    public BuffBar getBuffBar(String target) {
        switch (target) {
            case "Player":
                return playerBuffs;
            case "Party":
                return partyBuffs;
            case "Target":
                return targetBuffs;
        }
        return null;
    }

    public void setBuffBar(String target, BuffBar buffBar) {
        switch (target) {
            case "Player":
                playerBuffs = buffBar;
                break;
            case "Party":
                partyBuffs = buffBar;
                break;
            case "Target":
                targetBuffs = buffBar;
                break;
        }

    }


    public double getMultiplier(String type, String type2) {
        double multiplier = 1;

        if (type2.equals("Crit")) {
            multiplier = multiplier + barMultiplier(playerBuffs, type, type2);
            multiplier = multiplier + barMultiplier(partyBuffs, type, type2);
            multiplier = multiplier + barMultiplier(targetBuffs, type, type2);
        } else {
            multiplier = multiplier * barMultiplier(playerBuffs, type, type2);
            multiplier = multiplier * barMultiplier(partyBuffs, type, type2);
            multiplier = multiplier * barMultiplier(targetBuffs, type, type2);
        }


        return multiplier;
    }


    public double getNextRunOut() {
        ArrayList<Double> runOut = new ArrayList<>(3);
        runOut.add(playerBuffs.getNextBuffRunOut());
        runOut.add(partyBuffs.getNextBuffRunOut());
        runOut.add(targetBuffs.getNextBuffRunOut());
        return Collections.min(runOut);
    }


    private double barMultiplier(BuffBar buffBar, String type, String type2) {
        double multiplier = 1;
        for (Buffs i : buffBar.getBuffsArrayList()) {
            if ((i.getType().equals(type) && (i.getType2().equals(type2) || i.getType2().equals("All"))) || i.getType().equals("All")) {
                if (i.getType2().equals("Crit")) {
                    multiplier = multiplier + i.getIncrease();
                } else if (i.getType().equals("Haste")) {
                    multiplier = multiplier * (1 - i.getIncrease());
                } else {
                    multiplier = multiplier * i.getIncrease();
                }
            }

        }
        return multiplier;
    }
}
