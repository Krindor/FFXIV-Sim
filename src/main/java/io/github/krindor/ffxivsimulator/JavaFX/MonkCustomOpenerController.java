package io.github.krindor.ffxivsimulator.JavaFX;


import io.github.krindor.ffxivsimulator.Monk.SimulatorCore;
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


public class MonkCustomOpenerController {

    @FXML
    private Pane barForGrab;
    @FXML
    private GridPane gridOpener;
    private int columnIndex;
    private int rowIndex;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> saveCurrent;




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
