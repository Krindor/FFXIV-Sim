package io.github.krindor.ffxivsimulator.Timers;

import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Abilities;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class AllBuffs {
    private TreeMap<BuffBarNames, BuffBar> treeMap;

    public AllBuffs() {
        treeMap = new TreeMap<>();
        for (BuffBarNames names : BuffBarNames.values()) {
            treeMap.put(names, new BuffBar());
        }

    }

    public BuffBar getBuffBar(BuffBarNames target) {

        return treeMap.get(target);
    }

    public void setBuffBar(BuffBarNames target, BuffBar buffBar) {
        treeMap.replace(target, buffBar);

    }

    public void timeChange(double change) {
        for (BuffBarNames barNames : BuffBarNames.values()) {
            BuffBar newValue = treeMap.get(barNames);
            newValue.timeChange(change);
            treeMap.replace(barNames, newValue);
        }
    }

    public void addBuff(BuffBarNames target, Buffs buffs) {
        BuffBar value = treeMap.get(target);
        value.addBuff(buffs);
        treeMap.replace(target, value);
    }

    public void setCooldown(Abilities abilities) {
        Buffs cooldown = new Buffs();
        cooldown.setDuration(abilities.getCooldown());
        cooldown.setName(abilities.getName());
        BuffBar buffBar = treeMap.get(BuffBarNames.Cooldown);
        buffBar.addBuff(cooldown);
        treeMap.replace(BuffBarNames.Cooldown, buffBar);
    }

    public Buffs getBuff(BuffBarNames target, String buffName) {
        return treeMap.get(target).getTreeMap().get(buffName);
    }

    public boolean buffExists(BuffBarNames target, String buffName) {
        return treeMap.get(target).getTreeMap().containsKey(buffName);
    }

    public double getMultiplier(TypeNames type, TypeNames type2) {
        double multiplier = 1;

        if (type2.equals("Crit")) {
            for (BuffBarNames barNames : BuffBarNames.values()) {
                if (barNames != BuffBarNames.Cooldown) {
                    multiplier += barMultiplier(treeMap.get(barNames), type, type2);
                }
            }

        } else {
            for (BuffBarNames barNames : BuffBarNames.values()) {
                if (barNames != BuffBarNames.Cooldown) {
                    multiplier *= barMultiplier(treeMap.get(barNames), type, type2);
                }
            }
        }


        return multiplier;
    }


    public double getNextRunOut() {
        ArrayList<Double> runOut = new ArrayList<>(4);
        for (BuffBarNames barNames : BuffBarNames.values()) {
            runOut.add(treeMap.get(barNames).getNextBuffRunOut());
        }
        return Collections.min(runOut);
    }


    private double barMultiplier(BuffBar buffBar, TypeNames type, TypeNames type2) {
        double multiplier = 1;
        for (Map.Entry<String, Buffs> entry : buffBar.getTreeMap().entrySet()) {
            Buffs value = entry.getValue();
            if ((value.getType().equals(type) && (value.getType2().equals(type2) || value.getType2().equals("All"))) || value.getType().equals("All")) {
                if (value.getType2().equals("Crit")) {
                    multiplier = multiplier + value.getIncrease();
                } else if (value.getType().equals("Haste")) {
                    multiplier = multiplier * (1 - value.getIncrease());
                } else {
                    multiplier = multiplier * value.getIncrease();
                }
            }

        }
        return multiplier;
    }
}
