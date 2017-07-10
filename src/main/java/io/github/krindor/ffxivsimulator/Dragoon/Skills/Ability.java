package io.github.krindor.ffxivsimulator.Dragoon.Skills;

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

    int potency;
    String type;

    int cooldown;


    public Ability() {

    }

    public void jump() {
        potency = 200;
        type = "Physical";
        cooldown = 30;


    }

    public void spineshatterDive() {

        potency = 170;
        type = "Physical";

        cooldown = 60;

    }

    public void dragonfireDive() {

        potency = 250;
        type = "Physical";

        cooldown = 120;

    }

    public void battleLitany() {

        potency = -1;
        type = "Cast";

        cooldown = 180;

    }

    public void bloodOfTheDragon() {

        potency = -1;
        type = "Cast";
        cooldown = 60;

    }

    public void geirskogul() {

        potency = 200;
        type = "Physical";

        cooldown = 10;

    }

    public void lifeSurge() {

        potency = -1;
        type = "Cast";

        cooldown = 90;

    }

    public void legSweep(){
        potency = 130;
        type = "Physical";
        cooldown = 30;
    }

    public void bloodForBlood(){
        potency = -1;
        type = "Cast";
        cooldown = 80;
    }

    public void powerSurge(){
        potency = -1;
        type = "Cast";
        cooldown = 60;
    }





    public int getPotency() {
        if (type.equals("Physical") || type.equals("Magical")) {
            return potency;
        } else return 0;
    }



    public String getType() {
        return type;
    }



    public int getCooldown(){return cooldown;}


}
