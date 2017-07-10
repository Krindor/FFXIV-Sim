package io.github.krindor.ffxivsimulator.Monk.MonkSim;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class MonkOpenerCheck {
    public MonkOpenerCheck() {
    }

    public String openerTypeCheck(ArrayList<String> opener, int openerNum) {
        switch (opener.get(openerNum)) {
            case "Shoulder_Tackle":
            case "Internal_Release":
            case "Blood_for_Blood":
            case "Potion":
            case "Elixir_Field":
            case "Forbidden_Chakra":
            case "Steel_Peak":
            case "Howling_Fist":
            case "Perfect_Balance":
                return "OGCD";

            case "Bootshine":
            case "True_Strike":
            case "Touch_of_Death":
            case "Snap_Punch":
            case "Dragon_Kick":
            case "Twin_Snakes":
            case "Demolish":
            case "Fracture":
                return "Weapon_Skill";
        }
        return "NoN";
    }
}
