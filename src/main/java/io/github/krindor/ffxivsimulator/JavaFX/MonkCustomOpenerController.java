package io.github.krindor.ffxivsimulator.JavaFX;


import io.github.krindor.ffxivsimulator.Monk.SimulatorCore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

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


public class MonkCustomOpenerController extends GUIclass{


    @FXML
    private GridPane gridOpener;
    @FXML
    private ImageView Bootshine;
    @FXML
    private ImageView True_Strike;
    @FXML
    private ImageView Snap_Punch;
    @FXML
    private ImageView Dragon_Kick;
    @FXML
    private ImageView Twin_Snakes;
    @FXML
    private ImageView Demolish;
    @FXML
    private ImageView Touch_of_Death;
    @FXML
    private ImageView Shoulder_Tackle;
    @FXML
    private ImageView Forbidden_Chakra;
    @FXML
    private ImageView Elixir_Field;
    @FXML
    private ImageView Steel_Peak;
    @FXML
    private ImageView Howling_Fist;
    @FXML
    private ImageView Perfect_Balance;
    @FXML
    private ImageView Blood_for_Blood;
    @FXML
    private ImageView Internal_Release;
    @FXML
    private ImageView Potion;
    @FXML
    private ImageView Fracture;


    private int columnIndex;
    private int rowIndex;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> saveCurrent;




    public void closeProgram(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void saveOpener(ActionEvent event) {
        writeStringToFile(opener, event);
        setOpener();
    }


    public void loadSavedOpener(ActionEvent event) {
        ArrayList<ImageView> loadList = new ArrayList<>(30);
        ArrayList<String> stringArrayList = loadOpener(event);

        for (String string : stringArrayList) {
            if (string.equals(Bootshine.getId())) {
                loadList.add(setImage(Bootshine));

            } else if (string.equals(True_Strike.getId())) {
                loadList.add(setImage(True_Strike));

            } else if (string.equals(Snap_Punch.getId())) {
                loadList.add(setImage(Snap_Punch));

            } else if (string.equals(Dragon_Kick.getId())) {
                loadList.add(setImage(Dragon_Kick));

            } else if (string.equals(Twin_Snakes.getId())) {
                loadList.add(setImage(Twin_Snakes));

            } else if (string.equals(Demolish.getId())) {
                loadList.add(setImage(Touch_of_Death));

            } else if (string.equals(Shoulder_Tackle.getId())) {
                loadList.add(setImage(Shoulder_Tackle));

            } else if (string.equals(Forbidden_Chakra.getId())) {
                loadList.add(setImage(Forbidden_Chakra));

            } else if (string.equals(Elixir_Field.getId())) {
                loadList.add(setImage(Elixir_Field));

            } else if (string.equals(Steel_Peak.getId())) {
                loadList.add(setImage(Steel_Peak));

            } else if (string.equals(Howling_Fist.getId())) {
                loadList.add(setImage(Howling_Fist));

            } else if (string.equals(Perfect_Balance.getId())) {
                loadList.add(setImage(Perfect_Balance));

            } else if (string.equals(Fracture.getId())) {
                loadList.add(setImage(Fracture));

            } else if (string.equals(Blood_for_Blood.getId())) {
                loadList.add(setImage(Blood_for_Blood));

            } else if (string.equals(Internal_Release.getId())) {
                loadList.add(setImage(Internal_Release));

            } else if (string.equals(Potion.getId())) {
                loadList.add(setImage(Potion));

            }
        }

        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;


        for (ImageView imageView : loadList) {
            opener.add((imageView.getId()));
            gridOpener.add(imageView, columnIndex, rowIndex);
            if (columnIndex < 10) {
                columnIndex++;
            } else if (columnIndex == 10) {
                rowIndex++;
                columnIndex = 0;
            }
        }



    }

    private ImageView setImage(ImageView view) {
        ImageView newImage = new ImageView(view.getImage());
        newImage.setOnMousePressed(event1 -> removeSkill(newImage));
        newImage.setId(view.getId());
        saveCurrent.add(newImage);


        return newImage;
    }


    public void removeSkill(ImageView imageView) {
        int forLoop = gridOpener.getChildren().indexOf(imageView);

        saveCurrent.remove(forLoop);

        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;

        opener = new ArrayList<>(30);

        for (ImageView imageLoop : saveCurrent) {
            opener.add((imageLoop.getId()));
            gridOpener.add(imageLoop, columnIndex, rowIndex);
            if (columnIndex < 10) {
                columnIndex++;
            } else if (columnIndex == 10) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }

    public void setOpener() {
        SimulatorCore sim = new SimulatorCore();
        sim.setOpener(opener);
        sim.setCurentOpener(saveCurrent);
    }

    public void loadCurrent() {
        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;
        SimulatorCore simulatorCore = new SimulatorCore();
        saveCurrent = simulatorCore.loadCurrentOpener();
        opener = new ArrayList<>(30);

        for (ImageView imageView : saveCurrent) {
            opener.add((imageView.getId()));
            gridOpener.add(imageView, columnIndex, rowIndex);
            if (columnIndex < 10) {
                columnIndex++;
            } else if (columnIndex == 10) {
                rowIndex++;
                columnIndex = 0;
            }
        }
    }

    public void newOpener() {
        opener = new ArrayList<>(30);
        saveCurrent = new ArrayList<>(30);


        gridOpener.getChildren().clear();
        columnIndex = 0;
        rowIndex = 0;
    }

    public void initializeController() {
        opener = new ArrayList<>(30);
        saveCurrent = new ArrayList<>(30);
    }

    public void addAnotherSkill(MouseEvent event) {
        ImageView newImage = new ImageView(((ImageView) event.getSource()).getImage());
        newImage.setOnMousePressed(event1 -> removeSkill(newImage));
        newImage.setId(((ImageView) event.getSource()).getId());
        opener.add(((ImageView) event.getSource()).getId());
        saveCurrent.add(newImage);

        gridOpener.add(newImage, columnIndex, rowIndex);
        if (columnIndex < 10) {
            columnIndex++;
        } else if (columnIndex == 10) {
            rowIndex++;
            columnIndex = 0;
        }
    }


}
