package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class SimSelector {
    private io.github.krindor.ffxivsimulator.Ninja.SimulatorCore ninSimulatorCore;
    private io.github.krindor.ffxivsimulator.Monk.SimulatorCore monkSimulatorCore;
    private String job;
    private StatModel stats;
    private int time;
    private String openerType;
    private ArrayList<String> opener;
    private int iterations;

    public SimSelector(String job){
        this.job = job;
        switch (job){
            case "Ninja":
                ninSimulatorCore = new SimulatorCore();
                stats = ninSimulatorCore.getStats();
                time = ninSimulatorCore.getTime();
                openerType = ninSimulatorCore.getOpenerType();
                opener = ninSimulatorCore.getOpener();
                iterations = ninSimulatorCore.getIterations();

                break;
            case "Monk":
                monkSimulatorCore = new io.github.krindor.ffxivsimulator.Monk.SimulatorCore();
                stats = monkSimulatorCore.getStats();
                time = monkSimulatorCore.getTime();
                openerType = monkSimulatorCore.getOpenerType();
                opener = monkSimulatorCore.getOpener();
                iterations = monkSimulatorCore.getIterations();

                break;
        }
    }

    public ArrayList<String> runSim(){
        Simulatorpart simulatorpart = new Simulatorpart(stats, time, openerType, opener, job);

        return simulatorpart.runSim();
    }

    public int getIterations() {
        return iterations;
    }
}
