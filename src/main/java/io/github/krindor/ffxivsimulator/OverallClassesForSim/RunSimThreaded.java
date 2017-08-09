package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.RotationOpenerClasses.JobInfo;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by andre on 2017-04-21.
 */
public class RunSimThreaded implements Callable {
    private int iterations;

    private String activeClass;
    private static ArrayList<Double> numberSim;

    private ArrayList<String> damageArray;
    private SimSelector simSelector;
    private JobInfo jobInfo;

    public RunSimThreaded(JobInfo jobInfo) {
        this.jobInfo = jobInfo;
        activeClass = jobInfo.getJobName();
        damageArray = new ArrayList<>(1000);
        simSelector = new SimSelector(jobInfo, jobInfo.getIterations());
    }


    @Override
    public ArrayList<String> call() {
        run();
        return damageArray;
    }

    public ArrayList<String> runSim() {
        iterations = simSelector.getIterations();


        ArrayList<String> damage = new ArrayList<>(1000);
        numberSim = new ArrayList<>(iterations);
        long startTime = System.nanoTime();
        if (iterations > 1001) {

            ExecutorService pool = Executors.newFixedThreadPool(4);


            Callable<ArrayList<String>> t1 = new RunSimThreaded(jobInfo);
            Future<ArrayList<String>> dps1 = pool.submit(t1);
            Callable<ArrayList<String>> t2 = new RunSimThreaded(jobInfo);
            Future<ArrayList<String>> dps2 = pool.submit(t2);
            Callable<ArrayList<String>> t3 = new RunSimThreaded(jobInfo);
            Future<ArrayList<String>> dps3 = pool.submit(t3);
            Callable<ArrayList<String>> t4 = new RunSimThreaded(jobInfo);
            Future<ArrayList<String>> dps4 = pool.submit(t4);
            try {
                damage = dps1.get();

                damage.addAll(dps2.get());
                damage.addAll(dps3.get());
                damage.addAll(dps4.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

        } else damage = simSelector.runSim();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000000;

        damage.set(damage.size() - 1, "Damage Per Second: " + damage.get(damage.size() - 1));
        damage.add("Simulation Time: " + String.valueOf(duration) + " seconds");

        return damage;

    }

    private void run() {
        for (int i = 0; i < iterations / 4; i++) {
            damageArray = simSelector.runSim();

        }

    }


}
