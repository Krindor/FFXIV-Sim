package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
import io.github.krindor.ffxivsimulator.Ninja.Simulatorpart;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by andre on 2017-04-21.
 */
public class RunSimThreaded implements Callable {
    private int iterations;
    private StatModel stats;
    private int time;
    private boolean machinist;
    private boolean dragoon;
    private boolean warThere;
    private String openerType;
    private int hutonTime;
    private ArrayList<String> opener;
    private String activeClass;
    private static ArrayList<Double> numberSim;
    private io.github.krindor.ffxivsimulator.Ninja.SimulatorCore nincore;
    private io.github.krindor.ffxivsimulator.Monk.SimulatorCore mnkcore;
    private ArrayList<String> damageArray;


    public RunSimThreaded(String job) {
        activeClass = job;
        damageArray = new ArrayList<>(1000);
    }


    @Override
    public ArrayList<String> call() {


        if (activeClass.equals("Ninja")) {
            nincore = new SimulatorCore();
            setNinja();
            runNinja();
        }
        if (activeClass.equals("Monk")) {
            mnkcore = new io.github.krindor.ffxivsimulator.Monk.SimulatorCore();
            setMonk();
            runMonk();
        }

        return damageArray;
    }

    public ArrayList<String> runSim() {

        if (activeClass.equals("Ninja")) {
            nincore = new SimulatorCore();
            iterations = nincore.getIterations();
            setNinja();
        }

        if (activeClass.equals("Monk")) {
            mnkcore = new io.github.krindor.ffxivsimulator.Monk.SimulatorCore();
            iterations = mnkcore.getIterations();
            setMonk();
        }

        ArrayList<String> damage = new ArrayList<>(1000);
        numberSim = new ArrayList<>(iterations);
        long startTime = System.nanoTime();
        if (iterations > 1001) {

            ExecutorService pool = Executors.newFixedThreadPool(4);


            Callable<ArrayList<String>> t1 = new RunSimThreaded(activeClass);
            Future<ArrayList<String>> dps1 = pool.submit(t1);
            Callable<ArrayList<String>> t2 = new RunSimThreaded(activeClass);
            Future<ArrayList<String>> dps2 = pool.submit(t2);
            Callable<ArrayList<String>> t3 = new RunSimThreaded(activeClass);
            Future<ArrayList<String>> dps3 = pool.submit(t3);
            Callable<ArrayList<String>> t4 = new RunSimThreaded(activeClass);
            Future<ArrayList<String>> dps4 = pool.submit(t4);
            try {
                damage = dps1.get();

                damage.addAll(dps2.get());
                damage.addAll(dps3.get());
                damage.addAll(dps4.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        } else if (activeClass.equals("Ninja")) {
            for (int i = 0; i < iterations; i++) {

                io.github.krindor.ffxivsimulator.Ninja.Simulatorpart Sim = new Simulatorpart(stats, time, openerType, opener);


                damage = Sim.runSim();

            }
        } else if (activeClass.equals("Monk")) {
            for (int i = 0; i < iterations; i++) {
                io.github.krindor.ffxivsimulator.Monk.Simulatorpart Sim = new io.github.krindor.ffxivsimulator.Monk.Simulatorpart(stats, time, false, false, openerType, opener);
                damage = Sim.runSim();
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;

        damage.set(damage.size() - 1, "Damage Per Second: " + damage.get(damage.size() - 1));
        damage.add("Simulation Time: " + String.valueOf(duration) + " seconds");

        return damage;

    }

    private void setNinja() {

        iterations = nincore.getIterations();
        stats = nincore.getStats();
        time = nincore.getTime();
        machinist = nincore.isMachinist();
        dragoon = nincore.isDragoon();
        warThere = nincore.isWarThere();
        openerType = nincore.getOpenerType();
        opener = nincore.getOpener();
        hutonTime = nincore.getHutonTime();
    }

    private void setMonk() {
        iterations = mnkcore.getIterations();
        stats = mnkcore.getStats();
        time = mnkcore.getTime();
        openerType = mnkcore.getOpenerType();
        opener = mnkcore.getOpener();
        machinist = mnkcore.isMachinist();
        dragoon = mnkcore.isDragoon();

    }

    private void runNinja() {
        for (int i = 0; i < nincore.getIterations() / 4; i++) {


            Simulatorpart Sim = new Simulatorpart(stats, time, openerType, opener);

            damageArray = Sim.runSim();

            numberSim.add(Double.parseDouble(damageArray.get(damageArray.size() - 1)));


        }
    }

    private void runMonk() {
        for (int i = 0; i < mnkcore.getIterations() / 4; i++) {


            io.github.krindor.ffxivsimulator.Monk.Simulatorpart Sim = new io.github.krindor.ffxivsimulator.Monk.Simulatorpart(stats, time, false, false, openerType, opener);
            damageArray = Sim.runSim();
            numberSim.add(Double.parseDouble(damageArray.get(damageArray.size() - 1)));


        }

    }

}