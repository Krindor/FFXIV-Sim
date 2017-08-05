package io.github.krindor.ffxivsimulator.JobClasses;

import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;

/**
 * Created by andre on 2017-07-10.
 */
public class JobJSON {
    private static Job[] jobs;


    public Job[] getJobs() {
        return jobs;
    }

    public void setJobs(Job[] jobs) {
        JobJSON.jobs = jobs;
    }
}
