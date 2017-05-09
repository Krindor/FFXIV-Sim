package io.github.krindor.ffxivsimulator.Ninja;

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
public class SimulatorCore extends io.github.krindor.ffxivsimulator.OverallClassesForSim.SimulatorCore{

   public SimulatorCore(){
       setJob("Ninja");
       setJobmod(110);
   }
    private static boolean warThere;

    private static int hutonTime;


    public void setWarThere(boolean war) {
        warThere = war;
    }

    public void setHutonTime(int huton) {
        hutonTime = huton;
    }

    public int getHutonTime(){
        return hutonTime;
    }

    public boolean isWarThere(){
        return warThere;
    }


}
