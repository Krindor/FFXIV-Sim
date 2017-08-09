package io.github.krindor.ffxivsimulator.OverallClassesForSim;


import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;
import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
import io.github.krindor.ffxivsimulator.RotationOpenerClasses.JobInfo;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.util.ArrayList;

/**
 * Created by andre on 2017-05-09.
 */
public class SimSelector {
    private JobInfo jobInfo;
    private StatModel stats;
    private int time;
    private String openerType;
    private ArrayList<String> opener;
    private int iterations;
    private ActionObject actionObject;


    public SimSelector(JobInfo jobInfo, int iterations){
        this.jobInfo = jobInfo;
        this.iterations = iterations;
    }

    public ArrayList<String> runSim(){
        Simulatorpart simulatorpart = new Simulatorpart(jobInfo);

        return simulatorpart.runSim();
    }

    public int getIterations() {
        return iterations;
    }
}
