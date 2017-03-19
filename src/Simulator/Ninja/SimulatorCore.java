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
public class SimulatorCore implements Callable{
    private static ArrayList<Integer> mainStat;
    private static ArrayList<String> custom;
    private static boolean warThere;
    private static int time;
    private static int hutonTime;
    private static String openerType;
    private static ArrayList<String> Opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;
    private static ArrayList<Double> numberSim;


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
    public ArrayList<String> call(){
        ArrayList<String> damageArray = new ArrayList<>(1000);
        for (int i = 0; i < 15000; i++) {

            Simulatorpart Sim = new Simulatorpart(mainStat, time, false, false, warThere, openerType, hutonTime, Opener);
            damageArray = Sim.runSim();
            numberSim.add(Double.parseDouble(damageArray.get(damageArray.size()-1)));

        }
        return damageArray;
    }



    public ArrayList<String> runSim() {
        numberSim = new ArrayList<>(50000);
        ExecutorService pool = Executors.newFixedThreadPool(4);
        Long startTime = System.nanoTime();
        ArrayList<String> damage = new ArrayList<>(1000);
        Callable<ArrayList<String>> t1 = new SimulatorCore();
        Future<ArrayList<String>> dps1 = pool.submit(t1);
        Callable<ArrayList<String>> t2 = new SimulatorCore();
        Future<ArrayList<String>> dps2 = pool.submit(t2);
        Callable<ArrayList<String>> t3 = new SimulatorCore();
        Future<ArrayList<String>> dps3 = pool.submit(t3);
        Callable<ArrayList<String>> t4 = new SimulatorCore();
        Future<ArrayList<String>> dps4 = pool.submit(t4);
        try {
            damage = dps1.get();

            damage.addAll(dps2.get());
            damage.addAll(dps3.get());
            damage.addAll(dps4.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception");
        }



        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;
        System.out.println(duration);

        System.out.println(numberSim.size());
        damage.set(damage.size() - 1, "Damage Per Second: " + damage.get(damage.size() -1));
        damage.add("Simulation Time: " + String.valueOf(duration) + " seconds");

        return damage;

    }
}
