package io.github.krindor.ffxivsimulator.Monk;

import io.github.krindor.ffxivsimulator.RunSimThreaded;
import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

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
public class SimulatorCore {
    private static StatModel stats;
    private static ArrayList<String> custom;
    private static int iterations;
    private static int time;

    private static String openerType;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;

    private static boolean machinist = false;
    private static boolean dragoon = false;

    public void setTime(int times) {
        time = times;
    }

    public void setMainStat(StatModel stats) {
        this.stats = stats;
    }


    public void setOpenerType(String opener) {
        openerType = opener;
    }

    public void setOpener(ArrayList<String> opener) {
        this.opener = opener;
    }

    public int getIterations(){
        return iterations;
    }

    public StatModel getStats(){return stats;}

    public int getTime(){
        return time;
    }

    public String getOpenerType(){
        return openerType;
    }

    public ArrayList<String> getOpener(){
        return opener;
    }

    public boolean isMachinist(){
        return machinist;
    }

    public boolean isDragoon(){
        return dragoon;
    }



    public void setCurentOpener(ArrayList<ImageView> imageViews) {
        openerCalled = true;
        CurrentOpener = imageViews;
    }

    public ArrayList<ImageView> loadCurrentOpener() {
        if (openerCalled) {
            return CurrentOpener;
        } else {
            CurrentOpener = new ArrayList<>(30);
            return CurrentOpener;
        }
    }

    public ArrayList<String> runSim() {
        iterations = 1;

        RunSimThreaded simThreaded = new RunSimThreaded("Monk");


        return simThreaded.runSim();
    }
}
