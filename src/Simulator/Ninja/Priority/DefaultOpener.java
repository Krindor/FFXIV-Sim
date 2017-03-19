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
public class DefaultOpener {
    int counter = 0;
    String type2;
    public DefaultOpener(){}
    public String runDefaultOpener(){
        counter++;
        return switches(counter);
    }

    public boolean hasNext(){
        String check = switches(counter + 1);
        if(check.equals("NoN")){
            return false;
        }else
        return true;

    }

    public String getType2(){
        String check = switches(counter + 1);
        switch (check){
            case "Suiton": case "Internal Release":case "Blood for Blood":case "Potion":case "Kassatsu":case "Trick Attack":case "Raiton":case "Dream Within a Dream":case "Mug":case "Duality":case "Jugulate": return "OGCD";
            case "Spinning Edge":case "Shadow Fang":case "Mutilate":case "Gust Slash":case "Aeolian Edge": return "Weapon Skill";
        }return "NoN";
    }

    public ArrayList<String> getOpener(){
        ArrayList<String> opener = new ArrayList<>(20);
        for(int i = 1; i <= 20; i++){
            opener.add(switches(i));
        }
        return opener;
    }



    private String switches(int counter){

        switch(counter){

            case 1: return "Suiton";
            case 2: return "Spinning_Edge";
            case 3: return "Internal_Release";
            case 4: return "Blood_for_Blood";
            case 5: return "Shadow_Fang";
            case 6: return "Potion";
            case 7: return "Mutilate";
            case 8: return "Kassatsu";
            case 9: return "Spinning_Edge";
            case 10: return "Trick_Attack";
            case 11: return "Gust_Slash";
            case 12: return "Raiton";
            case 13: return "Aeolian_Edge";
            case 14: return "Dream_Within_a_Dream";
            case 15: return "Spinning_Edge";
            case 16: return "Mug";
            case 17: return "Gust_Slash";
            case 18: return "Duality";
            case 19: return "Aeolian_Edge";
            case 20: return "Jugulate";

        }return "NoN";
    }

}
