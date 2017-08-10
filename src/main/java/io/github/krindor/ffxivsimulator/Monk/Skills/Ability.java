package io.github.krindor.ffxivsimulator.Monk.Skills;

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
public class Ability {
    String skillname;
    int potency;
    String type;

    int cooldown;
    private boolean used;

    public Ability() {
        used = false;
    }

    public void shoulderTackle() {
        skillname = "Shoulder_Tackle";
        potency = 100;
        type = "Physical";

        cooldown = 30;
        used = true;

    }

    public void elixirField() {
        skillname = "Elixir_Field";
        potency = 220;
        type = "Physical";

        cooldown = 30;
        used = true;
    }

    public void forbiddenChakra() {
        skillname = "Forbidden_Chakra";
        potency = 320;
        type = "Physical";

        cooldown = 5;
        used = true;
    }

    public void internalRelease() {
        skillname = "Internal_Release";
        potency = -1;
        type = "Cast";

        cooldown = 60;
        used = true;
    }

    public void steelPeak() {
        skillname = "Steel_Peek";
        potency = 150;
        type = "Physical";
        cooldown = 60;
        used = true;
    }

    public void howlingFist() {
        skillname = "Howling_Fist";
        potency = 210;
        type = "Physical";

        cooldown = 60;
        used = true;
    }

    public void perfectBalance() {
        skillname = "Perfect Balance";
        potency = -1;
        type = "Cast";

        cooldown = 180;
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


    public int getCooldown() {
        return cooldown;
    }

    public boolean getUsed() {
        return used;
    }
}
