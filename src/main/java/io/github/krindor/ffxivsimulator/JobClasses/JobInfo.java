package io.github.krindor.ffxivsimulator.JobClasses;


import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.Enums.TypeNames;
import io.github.krindor.ffxivsimulator.JSON.JSONParse;
import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;
import io.github.krindor.ffxivsimulator.JSON.Rotation.Rotation;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Buffs;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.ConfigObject;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.Job;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.io.IOException;
import java.util.TreeMap;

/**
 * Created by andre on 2017-08-02.
 */
public class JobInfo {
    private String jobName;

    private StatModel stats;

    private Job job;
    private int time;
    private boolean machinist = false;
    private boolean dragoon = false;
    private int jobmod;
    private int iterations;
    private ActionObject actionObjects;
    private TypeNames resistance;
    private TreeMap<String, Buffs> treeMap;
    private Rotation rotationGCD;
    private Rotation rotationOGCD;

    public JobInfo(StatModel stats, String jobName, int time, int iterations, ActionObject actionObjects) {

        job = getSkillInfo(jobName);
        rotationGCD = getRotationInfo("GCD");
        rotationOGCD = getRotationInfo("oGCD");
        jobJsonToBuffMap(job);
        this.time = time;
        this.iterations = iterations;
        this.stats = stats;
        this.actionObjects = actionObjects;
    }

    public void setMainStat(StatModel stats) {
        this.stats = stats;
    }

    private Job getSkillInfo(String jobName) {
        JSONParse jsonParse = new JSONParse();
        JsonIterator iterator = jsonParse.parseJSON("./JSON/" + jobName + ".json");
        ConfigObject configObject;
        try {
            configObject = iterator.read(ConfigObject.class);

            return configObject.getJob()[0];


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Rotation getRotationInfo(String rotationName) {
        JSONParse jsonParse = new JSONParse();
        JsonIterator iterator = jsonParse.parseJSON("./JSON/" + rotationName + ".json");
        Rotation rotation;
        try {
            rotation = iterator.read(Rotation.class);
            return rotation;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void jobJsonToBuffMap(Job job) {
        treeMap = new TreeMap();
        job.addEnum();
        for (Buffs i : job.getBuffs()) {
            treeMap.put(i.getName(), i);
        }

    }

    public String getJobName() {
        return jobName;
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

    public TypeNames getResistance() {
        return resistance;
    }

    public void setResistance(TypeNames resistance) {
        this.resistance = resistance;
    }

    public Job getJob() {
        return job;
    }

    public TreeMap<String, Buffs> getTreeMap() {
        return treeMap;
    }

    public Rotation getRotationGCD() {
        return rotationGCD;
    }

    public Rotation getRotationOGCD() {
        return rotationOGCD;
    }
}
