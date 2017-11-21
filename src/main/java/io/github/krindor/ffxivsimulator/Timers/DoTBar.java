package io.github.krindor.ffxivsimulator.Timers;

import io.github.krindor.ffxivsimulator.Damage.DamageOverTime;


import java.util.Map;
import java.util.TreeMap;

public class DoTBar extends BarBase<DamageOverTime>{


    public void timeChange(double change) {

        treeMap.forEach((k, v) -> v.timeChange(change));

    }

    public String addDoT(DamageOverTime damageOverTime) {
        String damageOverTimeName = damageOverTime.getName();
        return addBuff(damageOverTimeName, damageOverTime);

    }


    public TreeMap<String, DamageOverTime> getTreeMap() {
        return treeMap;
    }

    public String buffRunOut() {

        for (Map.Entry<String, DamageOverTime> entry : treeMap.entrySet()) {
            if (entry.getValue().getDuration() == 0) {
                treeMap.remove(entry.getKey());
                return entry.getValue().getName() + " falls off.";
            }
        }
        return null;


    }

    public double getNextBuffRunOut() {
        double timeLeft = 100;
        for (Map.Entry<String, DamageOverTime> entry : treeMap.entrySet()) {
            if (entry.getValue().getDuration() < timeLeft) {
                timeLeft = entry.getValue().getDuration();
            }
        }
        return timeLeft;
    }


}
