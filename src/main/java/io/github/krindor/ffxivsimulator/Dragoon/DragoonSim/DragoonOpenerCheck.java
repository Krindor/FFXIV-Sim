package io.github.krindor.ffxivsimulator.Dragoon.DragoonSim;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class DragoonOpenerCheck {
    public DragoonOpenerCheck() {
    }

    public String openerTypeCheck(ArrayList<String> opener, int openerNum) {
        switch (opener.get(openerNum)) {
            case "Jump":
            case "Battle_Litany":
            case "Blood_for_Blood":
            case "Potion":
            case "Spineshatter_Dive":
            case "Dragonfire_Dive":
            case "Blood_of_the_Dragoon":
            case "Geirskogul":
            case "Life_Surge":
            case "Leg_Sweep":
            case "Power_Surge":
                return "OGCD";

            case "True_Thrust":
            case "Vorpal_Thrust":
            case "Full_Thrust":
            case "Impulse_Drive":
            case "Disembowel":
            case "Chaos_Thrust":
            case "Phlebotomiza":
            case "Heavy_Thrust":
            case "Wheeling_Thrust":
                return "Weapon_Skill";
        }
        return "NoN";
    }
}
