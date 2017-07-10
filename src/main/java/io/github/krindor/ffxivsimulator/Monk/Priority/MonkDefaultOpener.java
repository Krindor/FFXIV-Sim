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
public class MonkDefaultOpener {

    public MonkDefaultOpener(){}




    public ArrayList<String> getOpener(){
        ArrayList<String> opener = new ArrayList<>(20);
        for(int i = 1; i <= 20; i++){
            opener.add(switches(i));
        }
        return opener;
    }



    private String switches(int counter){

        switch(counter){

            case 1: return "Demolish";
            case 2: return "Perfect_Balance";
            case 3: return "Snap_Punch";
            case 4: return "Snap_Punch";
            case 5: return "Internal_Release";
            case 6: return "Dragon_Kick";
            case 7: return "Blood_for_Blood";
            case 8: return "Twin_Snakes";
            case 9: return "Potion";
            case 10: return "Touch_of_Death";
            case 11: return "Elixir_Field";
            case 12: return "Bootshine";
            case 13: return "Howling_Fist";
            case 14: return "True_Strike";
            case 15: return "Steel_Peak";
            case 16: return "Demolish";
            case 17: return "Forbidden_Chakra";
            case 18: return "Fracture";
            case 19: return "Shoulder_Tackle";
            case 20: return "Dragon_Kick";

        }return "NoN";
    }

}
