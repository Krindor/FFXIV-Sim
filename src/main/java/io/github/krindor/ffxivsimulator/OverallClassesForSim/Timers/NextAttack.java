package io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Created by andre on 2017-05-02.
 */
public class NextAttack {


    private LinkedList<AttackType> timers;

    public NextAttack(){
        timers = new LinkedList<>();
    }



    public void addNextAttack(String type, double time) {
        AttackType attackType = new AttackType(type, time);
        for (AttackType i : timers){
            if (i.getType().equals(type)){
                timers.remove(i);
            }
        }
        timers.add(attackType);
    }

    public void timeChange(double change){
        for (AttackType attackType : timers){
            attackType.setTime(attackType.getTime() - change);
        }
    }

    public AttackType getNextAttack(){

        return timers.removeFirst();
    }

    public double getNextAttack(String type){

        for (AttackType i : timers){
            if (i.getType().equals(type)){
                return i.getTime();
            }
        }
        return -1;
    }
}
