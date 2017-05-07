package io.github.krindor.ffxivsimulator.OverallClassesForSim;

import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-02-23.
 */
public class SimulatorCore {
    private static StatModel stats;


    private static int time;
    private static String job;
    private static String openerType;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;
    private static ArrayList<Double> numberSim;
    private static int iterations;
    private static boolean machinist = false;
    private static boolean dragoon = false;


    public void setTime(int times) {
        time = times;
    }

    public void setMainStat(StatModel stats) {
        this.stats = stats;
    }

    public void setOpenerType(String opener) {
        openerType = opener;
    }

    public void setOpener(ArrayList<String> opener) {
        this.opener = opener;


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

    protected void setJob(String job) {
        this.job = job;
    }

    public String getJob(){
        return job;
    }

    public StatModel getStats() {
        return stats;
    }

    public int getTime() {
        return time;
    }


    public int getIterations() {
        return iterations;
    }

    public boolean isMachinist() {
        return machinist;
    }

    public boolean isDragoon() {
        return dragoon;
    }


    public String getOpenerType() {
        return openerType;
    }

    public ArrayList<String> getOpener() {
        return opener;
    }

    public ArrayList<String> runSim() {
        iterations = 1;


        RunSimThreaded simThreaded = new RunSimThreaded(job);


        return simThreaded.runSim();
    }


}
