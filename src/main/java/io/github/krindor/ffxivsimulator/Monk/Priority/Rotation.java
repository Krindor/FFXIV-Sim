package io.github.krindor.ffxivsimulator.Monk.Priority;

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
    private double bFBTime;
    private double iRTime;
    private double elixirFieldTime;
    private double perfectBalanceTime;
    private double steelPeakTime;
    private double twinSnakesTime;
    private double shoulderTackleTime;
    private double howlingFistTime;
    private double glTime;
    private double dragonKickTime;
    private double greasedLightningTime;
    private double fractureDoTTime;
    private double chakraStacks;
    private double form;

    private double nextGCD;


    private double potionTime;
    private double toddoTTime;
    private double demodoTTime;

    private double recast;
    private String prevSkill;

    public Rotation() {
    }

    public String getNextGCD(ArrayList<Double> timers) {
        time(timers);
        if(toddoTTime < recast){
            return "Touch_of_Death";
        }else if (form == 1 && dragonKickTime < 3*recast){
            return "Dragon_Kick";
        }else if (form == 1){
            return "Bootshine";
        }else if (form == 2 && twinSnakesTime < 3*recast){
            return "Twin_Snakes";
        }else if (form == 2){
            return "True_Strike";
        }else if (form == 3 && demodoTTime < 2*recast){
            return "Demolish";
        }else if (form == 3){
            return "Snap_Punch";
        }else return "Bootshine";

    }

    public String getNextOGCD(ArrayList<Double> timers) {
        time(timers);
        if(bFBTime <= 0){
            return "Blood_for_Blood";
        }else if(iRTime <= 0){
            return "Internal_Release";
        }else if(potionTime <= 0){
            return "Potion";
        }else if(elixirFieldTime <= 0){
            return "Elixir_Field";
        }else if(howlingFistTime <= 0){
            return "Howling_Fist";
        }else if(steelPeakTime <= 0){
            return "Steel_Peak";
        }else if(shoulderTackleTime <= 0){
            return "Shoulder_Tackle";
        }else if(chakraStacks == 5){
            return "Forbidden_Chakra";
        }else return null;

    }

    private void time(ArrayList<Double> timersForRotation) {
        bFBTime = timersForRotation.get(0);
        iRTime = timersForRotation.get(1);
        twinSnakesTime = timersForRotation.get(2);
        shoulderTackleTime = timersForRotation.get(3);
        glTime = timersForRotation.get(4);
        steelPeakTime = timersForRotation.get(5);
        dragonKickTime = timersForRotation.get(6);
        elixirFieldTime = timersForRotation.get(7);
        howlingFistTime = timersForRotation.get(8);
        perfectBalanceTime = timersForRotation.get(9);
        potionTime = timersForRotation.get(10);
        demodoTTime = timersForRotation.get(11);
        toddoTTime = timersForRotation.get(12);
        recast = timersForRotation.get(13);
        nextGCD = timersForRotation.get(14);
        form = timersForRotation.get(15);
        fractureDoTTime = timersForRotation.get(16);
        chakraStacks = timersForRotation.get(17);
    }
}
