package io.github.krindor.ffxivsimulator.Monk;

import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

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
public class SimulatorCore {
    private static StatModel stats;
    private static ArrayList<String> custom;

    private static int time;

    private static String openerType;
    private static ArrayList<String> Opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;

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
        Opener = opener;


    }

    public void setCurentOpener(ArrayList<ImageView> imageViews){
        openerCalled = true;
        CurrentOpener = imageViews;
    }

    public ArrayList<ImageView> loadCurrentOpener(){
        if(openerCalled) {
            return CurrentOpener;
        }else {
            CurrentOpener = new ArrayList<>(30);
            return CurrentOpener;
        }
    }

    public ArrayList<String> runSim() {
        Simulatorpart Sim = new Simulatorpart(stats, time, false, false, openerType, Opener);
        return Sim.runSim();
    }
}
