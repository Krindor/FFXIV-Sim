package io.github.krindor.ffxivsimulator.JavaFX;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

public class Controller implements GUIinterface{

    @FXML
    private Pane barForGrab;
    GUIclass guIclass = new GUIclass();

    @Override
    public void mainSceneChanger(ActionEvent event) throws Exception {
        guIclass.mainSceneChanger(event);
    }

    public void customizeSceneChanger(ActionEvent event) throws Exception{
        guIclass.customizeSceneChanger(event);
    }

    public void SimulatorClassChooserSceneChanger(ActionEvent event) throws Exception{
        guIclass.SimulatorClassChooserSceneChanger(event);
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
        guIclass.draggedMove(mouseEvent, barForGrab);
    }
}
