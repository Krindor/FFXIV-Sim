package io.github.krindor.ffxivsimulator.JavaFX;


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

public class NinjaCustomOpenerController{

    @FXML
    private Pane barForGrab;
    @FXML
    private GridPane gridOpener;
    private int columnIndex;
    private int rowIndex;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> saveCurrent;
    GUIclass guIclass = new GUIclass();




    public void mainSceneChanger(ActionEvent event) throws Exception{
        guIclass.mainSceneChanger(event);
    }

    public void CharSceneChanger(ActionEvent event) throws Exception {
        guIclass.CharSceneChanger(event);
    }

    public void closeProgram(){
        guIclass.closeProgram();
    }

    public void minimize(MouseEvent event){
        guIclass.minimize(event);
    }



    public void pressedMove(MouseEvent me) {
        guIclass.pressedMove(me);
    }


    public void draggedMove(MouseEvent mouseEvent) {
        draggedMove(mouseEvent);
    }

    public void removeSkill(ImageView imageView){
        int forLoop = gridOpener.getChildren().indexOf(imageView);

        saveCurrent.remove(forLoop);

        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;

        opener = new ArrayList<>(30);

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

        gridOpener.add(newImage, columnIndex, rowIndex);
        if(columnIndex < 10) {
            columnIndex++;
        }else if(columnIndex == 10) {
            rowIndex++;
            columnIndex = 0;
        }
    }


}
