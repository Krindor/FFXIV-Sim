package io.github.krindor.ffxivsimulator.JavaFX;


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
 * FFXIV Simulator
 * Copyright (C) 2017  Andreas Lund
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

public class CustomController implements GUIinterface {

    @FXML
    private Pane barForGrab;
    GUIclass guIclass = new GUIclass();

    public void mainSceneChanger(ActionEvent event) throws Exception {
        guIclass.mainSceneChanger(event);
    }

    public void ninjaCustomOpenerChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("NinjaCustomOpenerFX.fxml"));


        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Ninja Custom Opener");
        customize.show();
        NinjaCustomOpenerController ninjaCustomOpenerController = new NinjaCustomOpenerController();
        ninjaCustomOpenerController.initializeController();
    }

    public void monkCustomOpenerChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("MonkCustomOpenerFX.fxml"));


        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Monk Custom Opener");
        customize.show();
        MonkCustomOpenerController monkCustomOpenerController = new MonkCustomOpenerController();
        monkCustomOpenerController.initializeController();
    }

    public void closeProgram() {
        guIclass.closeProgram();
    }

    public void minimize(MouseEvent event) {
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

}
