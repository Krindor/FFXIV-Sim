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
public class Warrior {
    private String skillname;
    private int potency;
    private String type;
    private int doTPotency;
    private int doTTime;
    private boolean used;

    public Warrior(){
        used = false;
    }

    public void fracture() {
        skillname = "Fracture";
        potency = 100;
        type = "Physical";
        doTPotency = 20;
        doTTime = 18;
        used = true;
    }
    public String getInfo() {
        return "Name: " + skillname + " " + "Potency: " + potency + " " + "Type: " + type;
    }

    public int getPotency() {
        return potency;
    }

    public int getDoTPotency() {
        return doTPotency;
    }

    public int getDoTTime() {
        return doTTime;
    }

    public String getSkillname() {
        return skillname;
    }

    public String getType() {
        return type;
    }

    public boolean getUsed() {
        return used;
    }
}
