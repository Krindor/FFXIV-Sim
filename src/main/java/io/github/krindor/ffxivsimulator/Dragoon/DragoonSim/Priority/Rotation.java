package io.github.krindor.ffxivsimulator.Dragoon.DragoonSim.Priority;

import io.github.krindor.ffxivsimulator.OverallClassesForSim.Timers.BuffsDebuffs;

import java.util.ArrayList;

/**
 FFXIV Simulator
 Copyright (C) 2017  Andreas Lund

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class Rotation {

    private double toddoTTime;
    private double demodoTTime;

    private double recast;
    private String prevSkill;

    public Rotation() {
    }

    public String getNextGCD(ArrayList<Double> timers, String prevSkill, BuffsDebuffs timer, BuffsDebuffs state) {
        time(timers, timer, state);
        if(toddoTTime < recast){
            return "Touch_of_Death";
        }else if (state.getForm() == 1 && timer.getDragonKick() < 3*recast){
            return "Dragon_Kick";
        }else if (state.getForm() == 1){
            return "Bootshine";
        }else if (state.getForm() == 2 && timer.getTwinSnakes() < 3*recast){
            return "Twin_Snakes";
        }else if (state.getForm() == 2){
            return "True_Strike";
        }else if (state.getForm() == 3 && demodoTTime < 2*recast){
            return "Demolish";
        }else if (state.getForm() == 3){
            return "Snap_Punch";
        }else return "Bootshine";

    }

    public String getNextOGCD(ArrayList<Double> timers, BuffsDebuffs timer, BuffsDebuffs state) {
        time(timers, timer, state);
        if(timer.getBloodForBlood() <= 0){
            return "Blood_for_Blood";
        }else if(timer.getInternalRelease() <= 0){
            return "Internal_Release";
        }else if(timer.getPotionTime() <= 0){
            return "Potion";
        }else if(timer.getElixirField() <= 0){
            return "Elixir_Field";
        }else if(timer.getHowlingFist() <= 0){
            return "Howling_Fist";
        }else if(timer.getSteelPeak() <= 0){
            return "Steel_Peak";
        }else if(timer.getShoulderTackle() <= 0){
            return "Shoulder_Tackle";
        }else if(state.getChakraStacks() == 5){
            return "Forbidden_Chakra";
        }else return null;

    }

    private void time(ArrayList<Double> timersForRotation, BuffsDebuffs timer, BuffsDebuffs state) {

        demodoTTime = timersForRotation.get(0);
        toddoTTime = timersForRotation.get(1);
        recast = timersForRotation.get(3);

    }
}
