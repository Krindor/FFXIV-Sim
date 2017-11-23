package io.github.krindor.ffxivsimulator.Timers;

import java.util.TreeMap;

public class BarBase<T> {
    protected TreeMap<String, T> treeMap;

    public BarBase() {
        treeMap = new TreeMap<>();
    }

    public String addBuff(String name, T value) {
        if (treeMap.containsKey(name)) {
            treeMap.replace(name, value);
            return name + " is refreshed.";
        } else {
            treeMap.put(name, value);
            return name + " is gained.";
        }
    }

    public TreeMap<String, T> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(TreeMap<String, T> treeMap) {
        this.treeMap = treeMap;
    }

    public String removeBuff(String buffName) {
        treeMap.remove(buffName);
        return buffName + " falls off.";
    }

    public T getBuff(String name) {
        return treeMap.get(name);
    }
}
