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
public class Ability{
    String skillname;
    int potency;
    String type;
    String mudraType;
    int cooldown;
    private boolean used;

    public Ability() {
        used = false;
    }

    public void fuma_Shuriken() {
        skillname = "Fuma_Shuriken";
        potency = 240;
        type = "Physical";
        mudraType = "1-step";
        cooldown = 20;
        used = true;

    }

    public void raiton() {
        skillname = "Raiton";
        potency = 360;
        type = "Magical";
        mudraType = "2-step";
        cooldown = 20;
        used = true;
    }

    public void suiton() {
        skillname = "Suiton";
        potency = 180;
        type = "Magical";
        mudraType = "3-step";
        cooldown = 20;
        used = true;
    }

    public void kassatsu() {
        skillname = "Kassatsu";
        potency = -1;
        type = "Cast";
        mudraType = "NoN";
        cooldown = 120;
        used = true;
    }

    public void duality() {
        skillname = "Duality";
        potency = -1;
        type = "Cast";
        mudraType = "NoN";
        cooldown = 90;
        used = true;
    }

    public void dreamWithinADream() {
        skillname = "Dream_Within_a_Dream";
        potency = 300;
        type = "Physical";
        mudraType = "NoN";
        cooldown = 90;
        used = true;
    }

    public void mug() {
        skillname = "Mug";
        potency = 140;
        type = "Physical";
        mudraType = "NoN";
        cooldown = 90;
        used = true;
    }

    public void jugulate() {
        skillname = "Jugulate";
        potency = 80;
        type = "Physical";
        mudraType = "NoN";
        cooldown = 30;
        used = true;
    }

    public void trickAttack() {
        skillname = "Trick_Attack";
        potency = 400;
        type = "Physical";
        mudraType = "NoN";
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

    public String getType() {
        return type;
    }

    public String getMudraType() {
        return mudraType;
    }

    public int getCooldown(){return cooldown;}

    public boolean getUsed(){return used;}
}
