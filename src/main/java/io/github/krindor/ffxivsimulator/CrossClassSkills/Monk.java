package io.github.krindor.ffxivsimulator.CrossClassSkills;

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
public class Monk {
    String skillname;
    int potency;
    String type;
    int cooldown;
    private boolean used;

    public Monk(){
        used = false;
    }

    public void internalRelease() {
        skillname = "Internal_Release";
        potency = -1;
        type = "Cast";
        cooldown = 60;
        used = true;


    }
    public String getInfo() {
        return "Name: " + skillname + " " + "Potency: " + potency + " " + "Type: " + type;
    }

    public int getPotency() {
        if (type.equals("Physical") || type.equals("Magical")) {
            return potency;
        } else return 0;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getType(){
        return type;
    }

    public int getCooldown(){return cooldown;}

    public boolean getUsed(){return used;}


}
