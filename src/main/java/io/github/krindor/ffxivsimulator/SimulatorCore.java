package io.github.krindor.ffxivsimulator;

import io.github.krindor.ffxivsimulator.Ninja.Simulatorpart;
import io.github.krindor.ffxivsimulator.model.StatModel;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-02-23.
 */
public class SimulatorCore {
    private static StatModel stats;
    private static ArrayList<String> custom;
    private static boolean warThere;
    private static int time;
    private static int hutonTime;
    private static String openerType;
    private static ArrayList<String> Opener;
    private static ArrayList<ImageView> CurrentOpener;
    private static boolean openerCalled;

    public void setTime(int times) {
        time = times;
    }

    public void setMainStat(StatModel stats) {
        this.stats = stats;
    }

    public void setWarThere(boolean war) {
        warThere = war;
    }

    public void setHutonTime(int huton) {
        hutonTime = huton;
    }

    public void setOpenerType(String opener) {
        openerType = opener;
    }

    public void setOpener(ArrayList<String> opener) {
        Opener = opener;


    }

    public void setCurentOpener(ArrayList<ImageView> imageViews){
        openerCalled = true;
        CurrentOpener = imageViews;
    }

    public ArrayList<ImageView> loadCurrentOpener(){
        if(openerCalled) {
            return CurrentOpener;
        }else {
            CurrentOpener = new ArrayList<>(30);
            return CurrentOpener;
        }
    }

    public ArrayList<String> runSim() {
        Simulatorpart Sim = new Simulatorpart(stats, time, false, false, warThere, openerType, hutonTime, Opener);
        return Sim.runSim();
    }
}
