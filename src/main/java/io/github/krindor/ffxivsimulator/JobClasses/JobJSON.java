package io.github.krindor.ffxivsimulator.JobClasses;

import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;

/**
 * Created by andre on 2017-07-10.
 */
public class JobJSON {
    private static Job[] jobs;


    public Job getJobs(String jobName) {

        for (Job i : jobs){
            if (i.getName().equals(jobName)){
                return i;
            }
        }
        return null;
    }

    public void setJobs(Job[] jobs) {
        JobJSON.jobs = jobs;
    }
}
