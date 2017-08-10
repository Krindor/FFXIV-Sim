package io.github.krindor.ffxivsimulator.RotationOpenerClasses;

import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.JobClasses.JobJSON;
import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-08-02.
 */
public class JobInfo {
    private String jobName;

    private StatModel stats;

    private Job job;
    private int time;
    private ArrayList<ImageView> CurrentOpener;
    private boolean openerCalled;
    private boolean machinist = false;
    private boolean dragoon = false;
    private int jobmod;
    private int iterations;
    private ActionObject actionObjects;
    private String resistance;

    public JobInfo(StatModel stats, String jobName, int time, int iterations, ActionObject actionObjects) {
        JobJSON jobJSON = new JobJSON();
        job = jobJSON.getJobs(jobName);
        this.time = time;
        this.iterations = iterations;
        this.stats = stats;
        this.actionObjects = actionObjects;
    }

    public void setMainStat(StatModel stats) {
        this.stats = stats;
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

    public String getJobName() {
        return jobName;
    }

    protected void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public StatModel getStats() {
        return stats;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int times) {
        time = times;
    }

    public boolean isMachinist() {
        return machinist;
    }

    public boolean isDragoon() {
        return dragoon;
    }


    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public ActionObject getActionObjects() {
        return actionObjects;
    }

    public void setActionObjects(ActionObject actionObjects) {
        this.actionObjects = actionObjects;
    }

    public int getJobmod() {
        return jobmod;
    }

    public void setJobmod(int jobmod) {
        this.jobmod = jobmod;
    }

    public String getResistance() {
        return resistance;
    }

    public void setResistance(String resistance) {
        this.resistance = resistance;
    }

    public Job getJob() {
        return job;
    }
}
