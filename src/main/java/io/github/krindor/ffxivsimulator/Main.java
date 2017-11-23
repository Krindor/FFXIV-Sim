package io.github.krindor.ffxivsimulator;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.JSON.JSONParse;
import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;
import io.github.krindor.ffxivsimulator.OverallClassesForSim.Simulatorpart;
import io.github.krindor.ffxivsimulator.JobClasses.JobInfo;
import io.github.krindor.ffxivsimulator.model.StatModel;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        StatModel statModel = new StatModel(80, 2000, 500, 500, 500, 500, 105, 500);
        String jobName = "Monk";
        int time = 240;
        int iterations = 1;
        String openerName = "test";
        JSONParse jsonParse = new JSONParse();
        JsonIterator iterator = jsonParse.parseJSON("./JSON/" + openerName + ".json");
        ActionObject actionObject;
        try {
            actionObject = iterator.read(ActionObject.class);



        } catch (IOException e) {
            e.printStackTrace();
            actionObject = null;
        }
        JobInfo jobInfo = new JobInfo(statModel, jobName, time, iterations, actionObject);
        Simulatorpart simulatorpart = new Simulatorpart(jobInfo);
        System.out.println(simulatorpart.runSim());
    }

}
