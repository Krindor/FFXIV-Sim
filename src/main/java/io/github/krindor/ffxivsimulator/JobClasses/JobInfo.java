package io.github.krindor.ffxivsimulator.JobClasses;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.JSON.JSONParse;
import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.ConfigObject;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

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
    private TreeMap<String, Buffs> treeMap;

    public JobInfo(StatModel stats, String jobName, int time, int iterations, ActionObject actionObjects) {

        job = readJSON();
        jobJsonToBuffMap(job);
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

    private Job readJSON(){
        JSONParse jsonParse = new JSONParse();
        JsonIterator iterator = jsonParse.parseJSON("./JSON/Buffs.json");
        ConfigObject configObject;
        try {
            configObject = iterator.read(ConfigObject.class);
            for (Job i : configObject.getJob()) {
                if (i.getName().equals(jobName)) {
                    return i;
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void jobJsonToBuffMap(Job job){
        treeMap = new TreeMap();
        job.addEnumBuffs();
        for (Buffs i: job.getBuffs()){
            treeMap.put(i.getName(), i);
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

    public TreeMap<String, Buffs> getTreeMap() {
        return treeMap;
    }
}
