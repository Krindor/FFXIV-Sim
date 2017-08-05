package io.github.krindor.ffxivsimulator.RotationOpenerClasses;

import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-08-02.
 */
public class JobInfo {
    private String job;

    private StatModel stats;


    private int time;
    private String openerType;
    private ArrayList<String> opener;
    private ArrayList<ImageView> CurrentOpener;
    private boolean openerCalled;
    private boolean machinist = false;
    private boolean dragoon = false;
    private int jobmod;


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

}
