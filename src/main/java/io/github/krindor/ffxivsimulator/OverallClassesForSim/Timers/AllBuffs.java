package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class AllBuffs {
    private TreeMap<BuffBarNames, BuffBar> treeMap;

    public AllBuffs() {
        treeMap = new TreeMap<>();
        for (BuffBarNames names: BuffBarNames.values()){
            treeMap.put(names, new BuffBar());
        }

    }

    public BuffBar getBuffBar(BuffBarNames target) {

        return treeMap.get(target);
    }

    public void setBuffBar(BuffBarNames target, BuffBar buffBar) {
        treeMap.replace(target, buffBar);

    }

    public void timeChange(double change){
        for (BuffBarNames barNames: BuffBarNames.values()){
            BuffBar newValue = treeMap.get(barNames);
            newValue.timeChange(change);
            treeMap.replace(barNames, newValue);
        }
    }

    public void addBuff(BuffBarNames target, Buffs buffs){
        BuffBar value = treeMap.get(target);
        value.addBuff(buffs);
        treeMap.replace(target, value);
    }


    public double getMultiplier(String type, String type2) {
        double multiplier = 1;

        if (type2.equals("Crit")) {
            for (BuffBarNames barNames: BuffBarNames.values()){
                if (barNames != BuffBarNames.Cooldown) {
                    multiplier += barMultiplier(treeMap.get(barNames), type, type2);
                }
            }

        } else {
            for (BuffBarNames barNames: BuffBarNames.values()){
                if (barNames != BuffBarNames.Cooldown) {
                    multiplier *= barMultiplier(treeMap.get(barNames), type, type2);
                }
            }
        }


        return multiplier;
    }


    public double getNextRunOut() {
        ArrayList<Double> runOut = new ArrayList<>(4);
        for (BuffBarNames barNames: BuffBarNames.values()){
            runOut.add(treeMap.get(barNames).getNextBuffRunOut());
        }
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
