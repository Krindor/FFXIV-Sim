package Simulator.Ninja.Priority;

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
    private double timer;
    private double bFBTime;
    private double iRTime;
    private double kassatsuTime;
    private double dualityTime;
    private double dWaDTime;
    private double mudraTime;
    private double tATime;
    private double mugTime;
    private double jugTime;
    private double dancingTime;
    private double hutonTime;
    private boolean suitonUsed;
    private double nextGCD;


    private double potionTime;
    private boolean dualityUsed;
    private boolean kassatsuUsed;
    private double mutdoTTime;
    private double sfdoTTime;

    private double recast;
    private String prevSkill;

    public Rotation() {
        dualityUsed = false;
        kassatsuUsed = false;

    }

    public String getNextGCD(ArrayList<Double> timers, String prevSkill) {
        time(timers);
        if (prevSkill.equals("Spinning_Edge") && sfdoTTime < recast * 2) {
            return "Shadow_Fang";
        } else if (prevSkill.equals("Spinning_Edge")) {
            return "Gust_Slash";
        } else if (prevSkill.equals("Gust_Slash") && hutonTime < recast * 3) {
            return "Armor_Crush";
        } else if (prevSkill.equals("Gust_Slash") && dancingTime < recast * 3) {
            return "Dancing_Edge";
        } else if (prevSkill.equals("Gust_Slash") && hutonTime < 40 && bFBTime < 60 && iRTime < 45 && potionTime < 255 && tATime < 50 && !dualityUsed) {
            return "Armor_Crush";
        } else if (prevSkill.equals("Gust_Slash")) {
            dualityUsed = false;
            return "Aeolian_Edge";

        }
        if (mutdoTTime < recast * 2) {
            return "Mutilate";
        } else return "Spinning_Edge";

    }

    public String getNextOGCD(ArrayList<Double> timers) {
        time(timers);
        if (bFBTime <= 0) {
            return "Blood_for_Blood";
        } else if (iRTime <= 0) {
            return "Internal_Release";
        } else if (potionTime <= 0) {
            return "Potion";

        } else if (suitonUsed && tATime <= 0) {

            return "Trick_Attack";

        } else if (mudraTime <= 0) {
            if (kassatsuUsed) {
                kassatsuUsed = false;
                return "Raiton";

            } else if (tATime <= 8 && !suitonUsed) {
                suitonUsed = true;
                return "Suiton";

            } else return "Fuma_Shuriken";
        } else if (mudraTime > 15 && kassatsuTime <= 0) {
            kassatsuUsed = true;
            return "Kassatsu";
        } else if (dancingTime >= recast * 3 && hutonTime >= recast * 3 && dualityTime <= 0) {
            dualityUsed = true;
            return "Duality";
        } else if (dWaDTime <= 0) {
            return "Dream_Within_a_Dream";
        } else if (mugTime <= 0) {
            return "Mug";
        } else if (jugTime <= 0) {
            return "Jugulate";
        } else return null;

    }

    private void time(ArrayList<Double> timersForRotation) {
        bFBTime = timersForRotation.get(0);
        iRTime = timersForRotation.get(1);
        kassatsuTime = timersForRotation.get(2);
        dualityTime = timersForRotation.get(3);
        dWaDTime = timersForRotation.get(4);
        mudraTime = timersForRotation.get(5);
        tATime = timersForRotation.get(6);
        mugTime = timersForRotation.get(7);
        jugTime = timersForRotation.get(8);
        dancingTime = timersForRotation.get(9);
        hutonTime = timersForRotation.get(10);
        potionTime = timersForRotation.get(11);
        sfdoTTime = timersForRotation.get(12);
        mutdoTTime = timersForRotation.get(13);
        recast = timersForRotation.get(14);
        nextGCD = timersForRotation.get(15);

    }
}
