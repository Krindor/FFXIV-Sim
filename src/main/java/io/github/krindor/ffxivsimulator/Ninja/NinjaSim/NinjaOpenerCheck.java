package io.github.krindor.ffxivsimulator.Ninja.NinjaSim;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class NinjaOpenerCheck {
    public NinjaOpenerCheck(){}

    public String openerTypeCheck(ArrayList<String> opener, int openerNum) {
        switch (opener.get(openerNum)) {
            case "Suiton":
            case "Internal_Release":
            case "Blood_for_Blood":
            case "Potion":
            case "Kassatsu":
            case "Trick_Attack":
            case "Raiton":
            case "Dream_Within_a_Dream":
            case "Mug":
            case "Duality":
            case "Jugulate":
            case "Fuma_Shuriken":
                return "OGCD";

            case "Spinning_Edge":
            case "Shadow_Fang":
            case "Mutilate":
            case "Gust_Slash":
            case "Aeolian_Edge":
            case "Dancing_Edge":
            case "Armor_Crush":
                return "Weapon_Skill";
        }
        return "NoN";
    }
}
