package io.github.krindor.ffxivsimulator;

/**
 * Created by andre on 2017-04-24.
 */
public class CurrentJob {
    private static String job;

    public CurrentJob(){

    }

    public CurrentJob(String jobs){
        job=jobs;
    }
    public String getJob(){
        return job;
    }

}
