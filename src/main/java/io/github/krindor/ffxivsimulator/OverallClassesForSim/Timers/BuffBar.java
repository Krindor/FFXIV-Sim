package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;


import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by andre on 2017-06-20.
 */
public class BuffBar {
    private ArrayList<Buffs> buffsArrayList;

    public BuffBar() {
        buffsArrayList = new ArrayList<>(10);

    }

    public void timeChange(double change) {
        for (Buffs i : buffsArrayList) {
            i.timeChange(change);
        }
    }

    public void addBuff(Buffs buffs) {
        for (Buffs i : buffsArrayList) {
            if (i.getName().equals(buffs.getName())) {
                i.setDuration(buffs.getDuration());
            } else buffsArrayList.add(buffs);
        }
    }

    public void removeBuff(String buffName) {
        for (Buffs i : buffsArrayList) {
            if (i.getName().equals(buffName)) {
                buffsArrayList.remove(i);
            }
        }

    }

    public ArrayList<Buffs> getBuffsArrayList() {
        return buffsArrayList;
    }

    public String buffRunOut() {

        for (Buffs i : buffsArrayList) {
            if (i.getDuration() <= 0) {
                buffsArrayList.remove(i);
                return i.getName() + "falls off.";
            }
        }
        return null;
    }

    public double getNextBuffRunOut() {
        ArrayList<Double> timeLeft = new ArrayList<>(buffsArrayList.size());
        for (Buffs i : buffsArrayList) {
            timeLeft.add(i.getDuration());
        }
        return Collections.min(timeLeft);
    }

    public void extendBuff(String buffName, double timeExtension) {
        for (Buffs i : buffsArrayList) {
            if (i.getName().equals(buffName)) {
                i.setDuration(i.getDuration() + timeExtension);
                break;
            }
        }
    }

    public boolean skillActive(String skillName) {
        for (Buffs i : buffsArrayList) {
            if (i.getName().equals(skillName)) {
                return true;
            }
        }
        return false;
    }


}
