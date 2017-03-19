package io.github.krindor.ffxivsimulator.JavaFX;


import io.github.krindor.ffxivsimulator.CrossClassSkills.Dragoon;
import io.github.krindor.ffxivsimulator.CrossClassSkills.General;
import io.github.krindor.ffxivsimulator.CrossClassSkills.Monk;
import io.github.krindor.ffxivsimulator.Ninja.Skills.Ability;
import io.github.krindor.ffxivsimulator.Ninja.Skills.WeaponSkills;
import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

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

public class NinjaCustomRotationController {

    @FXML
    private Pane barForGrab;
    @FXML
    private GridPane gridOpener;
    private int columnIndex;
    private int rowIndex;
    private ArrayList<String> opener;
    private ArrayList<ImageView> saveCurrent;

    private double timer;
    private double bFBTime;
    private double iRTime;
    private double kassatsuTime;
    private double dualityTime;
    private double dWaDTime;
    private double mudraTime;
    private double tATime;
    private double mugTime;
    private double jugTime;
    private double dancingTime;
    private double hutonTime;
    private double machinistTime;
    private double dragoonTime;
    private double potionTime;
    private boolean dualityUsed;
    private boolean kassatsuUsed;
    private boolean suitonUsed;


    private boolean machinistHC;
    private boolean dragoonBL;
    private boolean warrior;

    private double nextAA;
    private double nextDoT;
    private double nextGCD;
    private double nextoGCD;

    private double skillSpeed;

    private double recast;
    private double aaRecast;
    private ArrayList<Double> timers;

    private String type;
    private String type2;
    private String mudraType;
    private String prevattack;

    private double mutdoTTime;
    private double sfdoTTime;


    public void mainSceneChanger(ActionEvent event) throws Exception{
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("MainFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Main Menu");
        customize.show();
    }

    public void CharSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("CustomizeFX.fxml"));
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




    public void removeSkill(ImageView imageView){
        int forLoop = gridOpener.getChildren().indexOf(imageView);

        saveCurrent.remove(forLoop);

        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;

        opener = new ArrayList<>(30);
        opener.add("Custom Opener");
        for(ImageView imageLoop: saveCurrent){
            opener.add((imageLoop.getId()));
            gridOpener.add(imageLoop, columnIndex, rowIndex);
            if(columnIndex < 10) {
                columnIndex++;
            }else if(columnIndex == 10) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }

    public void setOpener(){
        SimulatorCore sim = new SimulatorCore();
        sim.setOpener(opener);
        sim.setCurentOpener(saveCurrent);
    }

    public void loadCurrent(){
        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;
        SimulatorCore simulatorCore = new SimulatorCore();
        saveCurrent = simulatorCore.loadCurrentOpener();
        opener = new ArrayList<>(30);
        opener.add("Custom Opener");
        for(ImageView imageView: saveCurrent){
            opener.add((imageView.getId()));
            gridOpener.add(imageView, columnIndex, rowIndex);
            if(columnIndex < 10) {
                columnIndex++;
            }else if(columnIndex == 10) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }

    public void newOpener(){
        opener = new ArrayList<>(30);
        saveCurrent = new ArrayList<>(30);

        opener.add("Custom Opener");
        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;
    }

    public void initializeController(){
        opener = new ArrayList<>(30);
        saveCurrent = new ArrayList<>(30);
    }

    public void addAnotherSkill(MouseEvent event){
        ImageView newImage = new ImageView(((ImageView) event.getSource()).getImage());
        newImage.setOnMousePressed(event1 -> removeSkill(newImage));
        newImage.setId(((ImageView) event.getSource()).getId());
        opener.add(((ImageView) event.getSource()).getId());
        saveCurrent.add(newImage);
        String attack = newImage.getId();
        checkSkill(attack);

        gridOpener.add(newImage, columnIndex, rowIndex);
        if(columnIndex < 10) {
            columnIndex++;
        }else if(columnIndex == 10) {
            rowIndex++;
            columnIndex = 0;
        }
    }

    private void checkSkill(String attack){
        Ability ability = new Ability();
        Monk monkCrossClass = new Monk();
        WeaponSkills weaponSkills = new WeaponSkills();
        Dragoon dragoonCrossClass = new Dragoon();
        General potion = new General();
        type = null;
        mudraType = "NoN";



        if (attack.equals("Fuma_Shuriken")) {
            ability.fuma_Shuriken();
            type2 = "Ability";
            mudraTime = ability.getCooldown();

            mudraType = ability.getMudraType();
            type = ability.getType();

        } else if (attack.equals("Raiton")) {
            ability.raiton();
            mudraTime = ability.getCooldown();

            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Suiton")) {
            ability.suiton();
            mudraTime = ability.getCooldown();

            mudraType = ability.getMudraType();
            type = ability.getType();
            type2 = "Ability";
            suitonUsed = true;


        } else if (attack.equals("Kassatsu")) {
            ability.kassatsu();
            kassatsuTime = ability.getCooldown();

            kassatsuUsed = true;
            type = ability.getType();
            mudraTime = 0;
            type2 = "Ability";

        } else if (attack.equals("Duality")) {
            ability.duality();
            dualityTime = ability.getCooldown();

            dualityUsed = true;
            type = ability.getType();
            type2 = "Ability";

        } else if (attack.equals("Dream_Within_a_Dream")) {
            ability.dreamWithinADream();
            dWaDTime = ability.getCooldown();

            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Mug")) {
            ability.mug();
            mugTime = ability.getCooldown();

            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Jugulate")) {
            ability.jugulate();
            jugTime = ability.getCooldown();

            type2 = "Ability";
            type = ability.getType();
        } else if (attack.equals("Trick_Attack")) {
            ability.trickAttack();
            tATime = ability.getCooldown();

            type2 = "Ability";
            type = ability.getType();
            suitonUsed = false;
        } else if (attack.equals("Internal_Release")) {
            monkCrossClass.internalRelease();
            iRTime = monkCrossClass.getCooldown();

            type2 = "Ability";
            type = monkCrossClass.getType();
        } else if (attack.equals("Blood_for_Blood")) {
            dragoonCrossClass.bloodForBlood();
            bFBTime = dragoonCrossClass.getCooldown();

            type2 = "Ability";
            type = dragoonCrossClass.getType();
        } else if (attack.equals("Potion")) {
            potion.potion();
            potionTime = potion.getCooldown();

            type2 = "Ability";
            type = potion.getType();
        } else if (attack.equals("Spinning_Edge")) {
            weaponSkills.spinningEdge();
            type = weaponSkills.getType();

            type2 = "Weapon Skill";
        } else if (attack.equals("Gust_Slash")) {
            weaponSkills.gustSlash();
            type = weaponSkills.getType();

            type2 = "Weapon Skill";
        } else if (attack.equals("Aeolian_Edge")) {
            weaponSkills.aeolianEdge();
            type = weaponSkills.getType();

            type2 = "Weapon Skill";
        } else if (attack.equals("Dancing_Edge")) {
            weaponSkills.dancingEdge();
            type = weaponSkills.getType();

            dancingTime = 20;
            type2 = "Weapon Skill";
        } else if (attack.equals("Armor_Crush")) {
            weaponSkills.armorCrush();
            type = weaponSkills.getType();

            hutonTime = hutonTime + 30;
            if (hutonTime > 70) {
                hutonTime = 70;
            }
            type2 = "Weapon Skill";
        } else if (attack.equals("Shadow_Fang")) {
            weaponSkills.shadowFang();
            type = weaponSkills.getType();
            ;

            sfdoTTime = weaponSkills.getDoTTime();

            type2 = "Weapon Skill";

        } else if (attack.equals("Mutilate")) {
            weaponSkills.mutilate();
            type = weaponSkills.getType();


            mutdoTTime = weaponSkills.getDoTTime();

            type2 = "Weapon Skill";
        }
    }

    private void timeChange(double change) {
        nextAA = nextAA - change;
        nextoGCD = nextoGCD - change;
        nextGCD = nextGCD - change;
        nextDoT = nextDoT - change;
        bFBTime = bFBTime - change;
        iRTime = iRTime - change;
        kassatsuTime = kassatsuTime - change;
        dualityTime = dualityTime - change;
        dWaDTime = dWaDTime - change;
        mudraTime = mudraTime - change;
        tATime = tATime - change;
        mugTime = mugTime - change;
        jugTime = jugTime - change;
        dancingTime = dancingTime - change;
        hutonTime = hutonTime - change;
        machinistTime = machinistTime - change;
        dragoonTime = dragoonTime - change;
        potionTime = potionTime - change;



    }


}
