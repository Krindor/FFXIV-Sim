package io.github.krindor.ffxivsimulator.JavaFX;


import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;

import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;

import javafx.scene.control.MenuItem;

/**
 FFXIV Simulator
 Copyright (C) 2017  Andreas Lund

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class NinjaSimController {
    @FXML
    private TextField weaponText;
    @FXML
    private TextField dexText;
    @FXML
    private TextField critText;
    @FXML
    private TextField detText;
    @FXML
    private TextField ssText;
    @FXML
    private TextField simTimeText;
    @FXML
    private TextField hutonText;
    @FXML
    private TextArea damageLog;
    @FXML
    private CheckBox warrior;

    @FXML
    private MenuButton simulationType;

    @FXML
    private Pane barForGrab;

    private String opener;

    private ArrayList<String> log;

    public void mainSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("MainFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Main Menu");
        customize.show();
    }

    public void CharSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("ClassChooserFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Class Chooser");
        customize.show();
    }

    public void closeProgram(){
        Platform.exit();
    }

    public void minimize(MouseEvent event){
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setIconified(true);
    }

    public void setOpener(ActionEvent event){
        simulationType.setText(((MenuItem)event.getSource()).getText());
        opener = simulationType.getText();
        System.out.println(opener);

    }

   public void startSim() {
        boolean runneable = true;
        damageLog.clear();


        int weaponDamage;
        weaponDamage = 85;
        try {
            if (weaponText.getText() != null && !weaponText.getText().isEmpty()) {
                weaponDamage = Integer.parseInt(weaponText.getText());
            }
        }catch (RuntimeException e){
            runneable = false;
            damageLog.setText("Wrong Weapon Damage input");
        }

        int mainStat;
        mainStat = 1589;
        try {
            if (dexText.getText() != null && !dexText.getText().isEmpty()) {
                mainStat = Integer.parseInt(dexText.getText());
            }
        }catch (RuntimeException e){
            runneable = false;
            damageLog.setText("Wrong Dexterity input");
        }

        int crit;
        crit = 1366;
        try {
            if (critText.getText() != null && !critText.getText().isEmpty()) {
                crit = Integer.parseInt(critText.getText());
            }
        }catch (RuntimeException e){
            runneable = false;
            damageLog.setText("Wrong Crit input");
        }

        int det;
        det = 689;
        try {
            if (detText.getText() != null && !detText.getText().isEmpty()) {
                det = Integer.parseInt(detText.getText());
            }
        }catch (RuntimeException e){
            runneable = false;
            damageLog.setText("Wrong Determination input");
        }
        int skillSpeed;

        skillSpeed = 444;
        try {
            if (ssText.getText() != null && !ssText.getText().isEmpty()) {
                skillSpeed = Integer.parseInt(ssText.getText());
            }
        }catch (RuntimeException e){
            runneable = false;
            damageLog.setText("Wrong SkillSpeed Input");
        }

       int time;
       time = 180;
       try {
           if (simTimeText.getText() != null && !simTimeText.getText().isEmpty()) {
               time = Integer.parseInt(simTimeText.getText());
           }
       }catch (RuntimeException e){
        runneable = false;
        damageLog.setText("Wrong Time input");
       }

       int hutonTime;
       hutonTime = 23;
       try {
           if (hutonText.getText() != null && !hutonText.getText().isEmpty()) {
               hutonTime = Integer.parseInt(hutonText.getText());

           }
       }catch (RuntimeException e){
           runneable = false;
           damageLog.setText("Wrong Huton Pre-Pull input");
       }

       if (runneable) {
           SimulatorCore sim = new SimulatorCore();
           sim.setMainStat(weaponDamage, mainStat, crit, det, skillSpeed);
           sim.setTime(time);
           sim.setHutonTime(hutonTime);
           sim.setOpenerType(opener);
           sim.setWarThere(warrior.selectedProperty().get());
           ExecutorService pool = Executors.newFixedThreadPool(4);
           Task task = new Task() {
               @Override
               protected Object call() throws Exception {
                   log = sim.runSim();
                   String logtext = "Sim start";
                   for (String x : log) {
                       logtext = logtext + "\n" + x;
                   }
                   damageLog.setText(logtext);
                   return damageLog;
               }
           };
           pool.submit(task);



       }



    }

    double initialX;
    double initialY;

    public void pressedMove(MouseEvent me) {
        if (me.getButton() != MouseButton.MIDDLE) {
            initialX = me.getSceneX();
            initialY = me.getSceneY();

        }
    }


    public void draggedMove(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() != MouseButton.MIDDLE) {
            barForGrab.getScene().getWindow().setX(mouseEvent.getScreenX() - initialX);
            barForGrab.getScene().getWindow().setY(mouseEvent.getScreenY() - initialY);
        }
    }

    public void writeStringToFile(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
        Date date = new Date();
        File directory = new File(System.getProperty("user.home")+"/Documents/FFXIVSIM_Log/") ;
        directory.mkdirs();
        File file = new File(System.getProperty("user.home")+"/Documents/FFXIVSIM_Log/" + dateFormat.format(date) + ".txt");
        try(PrintWriter out = new PrintWriter(file)){

            for(String x: log){
                out.println("\n" + x);
            }
            out.close();

        }
        catch (IOException e){
            System.out.println("Exception");
        }
    }


}
