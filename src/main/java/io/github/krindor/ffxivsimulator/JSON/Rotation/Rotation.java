package io.github.krindor.ffxivsimulator.JSON.Rotation;


import io.github.krindor.ffxivsimulator.Enums.BuffBarNames;
import io.github.krindor.ffxivsimulator.Timers.AllBuffs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that contains the full conditional rotation, built to bind a JSON rotation file to it
 */
public class Rotation {
    private SkillConditionalChecker[] skillConditionalCheckers;

    private TreeMap<String, SkillConditionalChecker> skillTree;
    //Turns the Array into a LinkedList to get better access to methods
    public void makeTreemap() {

        skillTree = new TreeMap<>();
        for (SkillConditionalChecker checker : skillConditionalCheckers) {
            checker.makeArray();
            skillTree.put(checker.getName(), checker);
        }


    }
    //Checks for the next skill and returns a string with the name of the skill
    public String getNext(AllBuffs allBuffs, String prevAttack) {
        for (Map.Entry<String, SkillConditionalChecker> map : skillTree.entrySet()) {
            if (map.getKey().equals(prevAttack) || allBuffs.buffExists(BuffBarNames.All, map.getKey())){
                return map.getValue().getNext(allBuffs);
            }
        }
        return "Error in Rotation";
    }
}
