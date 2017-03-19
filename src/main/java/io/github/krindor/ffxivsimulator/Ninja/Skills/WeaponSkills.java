package io.github.krindor.ffxivsimulator.Ninja.Skills;

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
public class WeaponSkills {
    private String skillname;
    private int potency;
    private String type;
    private int doTPotency;
    private int doTTime;
    private boolean used;

    public WeaponSkills() {
        used = false;
    }

    public void spinningEdge() {
        skillname = "Spinning_Edge";
        potency = 150;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void gustSlash() {
        skillname = "Gust_Slash";
        potency = 200;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void aeolianEdge() {
        skillname = "Aeolian_Edge";
        potency = 320;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void dancingEdge() {
        skillname = "Dancing_Edge";
        potency = 260;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void armorCrush() {
        skillname = "Armor_Crush";
        potency = 280;
        type = "Physical";
        doTPotency = 0;
        doTTime = 0;
        used = true;
    }

    public void shadowFang() {
        skillname = "Shadow_Fang";
        potency = 200;
        type = "Physical";
        doTPotency = 40;
        doTTime = 18;
        used = true;


    }

    public void mutilate() {
        skillname = "Mutilate";
        potency = 60;
        type = "Physical";
        doTPotency = 30;
        doTTime = 30;
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
