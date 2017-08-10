package io.github.krindor.ffxivsimulator.Dragoon.Skills;

/**
 * FFXIV Simulator
 * Copyright (C) 2017  Andreas Lund
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class WeaponSkills {

    private int potency;
    private String type;

    private int doTTime;


    public WeaponSkills() {

    }

    public void trueThrust() {

        potency = 150;
        type = "Physical";

        doTTime = 0;

    }

    public void vorpalThrust() {

        potency = 200;
        type = "Physical";

        doTTime = 0;

    }

    public void fullThrust() {

        potency = 360;
        type = "Physical";

        doTTime = 0;

    }

    public void impulseDrive() {

        potency = 180;
        type = "Physical";

        doTTime = 0;

    }

    public void disembowel() {

        potency = 220;
        type = "Physical";

        doTTime = 0;

    }

    public void chaosThrust() {

        potency = 250;
        type = "Physical";

        doTTime = 30;


    }

    public void phlebotomize() {

        potency = 170;
        type = "Physical";

        doTTime = 24;

    }

    public void wheelingThrust() {
        potency = 290;
        type = "Physical";
        doTTime = 0;
    }

    public void heavyThrust() {
        potency = 170;
        type = "Physical";
        doTTime = 0;
    }


    public int getPotency() {
        return potency;
    }


    public int getDoTTime() {
        return doTTime;
    }


    public String getType() {
        return type;
    }


}
