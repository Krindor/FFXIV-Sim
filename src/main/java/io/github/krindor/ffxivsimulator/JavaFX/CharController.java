package io.github.krindor.ffxivsimulator.JavaFX;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class CharController implements GUIinterface {

    @FXML
    private Pane barForGrab;
    GUIclass gui = new GUIclass();

    public void mainSceneChanger(ActionEvent event) throws Exception {
        gui.mainSceneChanger(event);
    }

    public void ninjaSimChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("Ninsimexample.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Ninja Simulator");
        customize.show();
    }

    public void monkSimChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("MonkSimFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Ninja Simulator");
        customize.show();
    }

    public void closeProgram() {
        Platform.exit();
    }

    public void minimize(MouseEvent event) {
        gui.minimize(event);
    }


    public void pressedMove(MouseEvent me) {
        gui.pressedMove(me);
    }


    public void draggedMove(MouseEvent mouseEvent) {
        gui.draggedMove(mouseEvent, barForGrab);
    }

}
