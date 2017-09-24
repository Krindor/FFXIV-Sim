package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.Damage.DamageOverTime;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.Timers.BuffBar;

import java.util.ArrayList;

/**
 * Created by andre on 2017-07-13.
 */
public class JobSkillUsed {
    private String attack;
    private BuffBar buffBar;
    private JobJSON jobJSON;

    private String jobName;
    private Job job;
    private ArrayList<DamageOverTime> dotsArray;

    public JobSkillUsed(String jobName) {
        this.jobName = jobName;
        jobJSON = new JobJSON();
        for (int i = 0; i < jobJSON.getJobs().length; i++) {
            if (jobJSON.getJobs()[i].getName().equals(jobName)) {
                job = jobJSON.getJobs()[i];
            }
        }
    }

    public void skillUsed(String attack, ArrayList<DamageOverTime> dotsArray)

    {
        this.attack = attack;
        this.dotsArray = dotsArray;


    }
}
