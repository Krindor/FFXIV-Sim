package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaMultipliers {
    public BuffsDebuffs multiplier(BuffsDebuffs timers, BuffsDebuffs state){
        if (timers.getBloodForBlood() <= 80 && timers.getBloodForBlood() >= 60) {
            state.setBloodForBlood(1.1);
        } else state.setBloodForBlood(1.0);

        if (timers.getDancingEdge() > 0) {
            state.setDancingEdge(1.1);
        } else state.setDancingEdge(1.0);

        if (timers.getPotionTime() <= 270 && timers.getPotionTime() >= 255) {
            state.setPotion(true);
        } else state.setPotion(false);

        if (timers.getTrickAttack() <= 60 && timers.getTrickAttack() >= 50) {
            state.setTrickAttack(1.1);
        } else state.setTrickAttack(1.0);

        if (timers.getInternalRelease() <= 60 && timers.getInternalRelease() >= 45) {
            state.setInternalRelease(0.1);

        } else state.setInternalRelease(0.0);

        if (timers.getHutonTime() > 0){
            state.setHutonTime(0.15);
        } else state.setHutonTime(0);
        return state;
    }

}
