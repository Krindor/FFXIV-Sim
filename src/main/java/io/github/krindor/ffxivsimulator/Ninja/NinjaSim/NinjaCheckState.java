package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaCheckState {
    public BuffsDebuffs checkState(BuffsDebuffs state, String specialType, String type2) {
        if (!specialType.equals("NoN") && state.isKassatsu()) {
            state.setKassatsu(false);
        }
        if (type2.equals("Weapon Skill") && state.isDuality()) {
            state.setDuality(false);
        }
        return state;
    }
}
