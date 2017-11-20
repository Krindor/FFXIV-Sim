package io.github.krindor.ffxivsimulator.Timers;


import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;

import java.util.Map;
import java.util.TreeMap;


/**
 * Created by andre on 2017-06-20.
 */
public class BuffBar {
    private TreeMap<String, Buffs> treeMap;

    public BuffBar() {
        treeMap = new TreeMap<>();

    }

    public void timeChange(double change) {
        treeMap.forEach((k, v) -> v.timeChange(change));

    }

    public String addBuff(Buffs buffs) {
        String buffName = buffs.getName();
        if (treeMap.containsKey(buffName)) {
            treeMap.replace(buffName, buffs);
            return buffName + " is refreshed.";
        } else {
            treeMap.put(buffName, buffs);
            return buffName + " is gained.";
        }

    }

    public String removeBuff(String buffName) {
        treeMap.remove(buffName);
        return buffName + " falls off.";
    }

    public TreeMap<String, Buffs> getTreeMap() {
        return treeMap;
    }

    public String buffRunOut() {

        for (Map.Entry<String, Buffs> entry : treeMap.entrySet()) {
            if (entry.getValue().getDuration() == 0) {
                treeMap.remove(entry.getKey());
                return entry.getValue().getName() + " falls off.";
            }
        }
        return null;


    }

    public Buffs getBuff(String name) {
        return treeMap.get(name);
    }

    public double getNextBuffRunOut() {
        double timeLeft = 100;
        for (Map.Entry<String, Buffs> entry : treeMap.entrySet()) {
            if (entry.getValue().getDuration() < timeLeft) {
                timeLeft = entry.getValue().getDuration();
            }
        }
        return timeLeft;
    }

    public void extendBuff(String buffName, double timeExtension) {
        treeMap.forEach((k, v) -> {
            if (v.getName().equals(buffName)) {
                v.setDuration(v.getDuration() + timeExtension);
            }
        });

    }


}
