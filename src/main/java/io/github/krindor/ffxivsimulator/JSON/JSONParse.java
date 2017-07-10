package io.github.krindor.ffxivsimulator.JSON;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.TextFileLoader;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andre on 2017-06-21.
 */
public class JSONParse {
    Buffs buffs;
    public JSONParse() {
        ArrayList<String> stringArrayList;
        String job = "Ninja";
        String skill = "Poison";
        buffs = new Buffs();
        File file = new File("./JSON/Buffs.json");
        TextFileLoader textFileLoader = new TextFileLoader();
        stringArrayList = textFileLoader.loadText(file);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringArrayList) {
            stringBuilder.append(s);
            stringBuilder.append("\t");
        }


        JsonIterator iterator = JsonIterator.parse(stringBuilder.toString());
        ConfigObject configObject;
        try {
            configObject = iterator.read(ConfigObject.class);
            Job[] jobs = configObject.getJobs();
            for (int i = 0; i < jobs.length; i++) {
                if (jobs[i].getName().equals(job)){
                    for (int j = 0; j < jobs[i].getBuffs().length; j++){
                        if (jobs[i].getBuffs()[j].getName().equals(skill)){
                            buffs = jobs[i].getBuffs()[j];
                            break;
                        }
                    }
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public Buffs getBuffs() {
        return buffs;
    }
}
