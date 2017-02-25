package Simulator;

import Simulator.Ninja.Simulatorpart;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Created by andre on 2017-02-23.
 */
public class SimulatorCore {
    private static ArrayList<Integer> mainStat;
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

    public void setMainStat(int WD, int Main, int Crit, int Det, int SS) {
        mainStat = new ArrayList<>(5);
        mainStat.add(WD);
        mainStat.add(Main);
        mainStat.add(Crit);
        mainStat.add(Det);
        mainStat.add(SS);
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
        Simulatorpart Sim = new Simulatorpart(mainStat, time, false, false, warThere, openerType, hutonTime, Opener);
        return Sim.runSim();
    }
}
