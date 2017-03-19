package Simulator.Ninja;

import javafx.concurrent.Task;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.concurrent.*;

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
public class SimulatorCore implements Callable {
    private static ArrayList<Integer> mainStat;
    private static ArrayList<String> custom;
    private static boolean warThere;
    private static int time;
    private static int hutonTime;
    private static String openerType;
    private static ArrayList<String> Opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;
    private static int numberSim;

    public void setTime(int times) {
        time = times;
    }

    public void setMainStat(int WD, int Main, int Crit, int Det, int SS) {
        mainStat = new ArrayList<>(5);
        mainStat.add(WD);
        mainStat.add(Main);
        mainStat.add(Crit);
        mainStat.add(Det);
        mainStat.add(SS);
    }

    public void setWarThere(boolean war) {
        warThere = war;
    }

    public void setHutonTime(int huton) {
        hutonTime = huton;
    }

    public void setOpenerType(String opener) {
        openerType = opener;
    }

    public void setOpener(ArrayList<String> opener) {
        Opener = opener;


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

    @Override
    public ArrayList<String> call() throws Exception {
        ArrayList<String> damageArray = new ArrayList<>(1000);
        for (int i = 0; i < 15000; i++) {
            mainStat.set(1, mainStat.get(1) + 1);
            Simulatorpart Sim = new Simulatorpart(mainStat, time, false, false, warThere, openerType, hutonTime, Opener);
            damageArray = Sim.runSim();
            numberSim++;

        }
        return damageArray;
    }


    public ArrayList<String> runSim() {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        ArrayList<String> damage = new ArrayList<>(1000);
        Task<ArrayList<String>> task1 = new Task<ArrayList<String>>() {
            @Override
            protected ArrayList<String> call() throws Exception {
                long startTime = System.nanoTime();
                ArrayList<String> damageArray = new ArrayList<>(1000);
                for (int i = 0; i < 15000; i++) {
                    mainStat.set(1, mainStat.get(1) + 1);
                    Simulatorpart Sim = new Simulatorpart(mainStat, time, false, false, warThere, openerType, hutonTime, Opener);
                    damageArray = Sim.runSim();
                    numberSim++;

                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1000000;
                System.out.println(duration);
                return damageArray;
            }

        };
        pool.submit(task1);
        System.out.println(numberSim);
        return damage;

    }
}
