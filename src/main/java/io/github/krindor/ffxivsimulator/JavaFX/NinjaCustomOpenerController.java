package io.github.krindor.ffxivsimulator.JavaFX;


import io.github.krindor.ffxivsimulator.Ninja.SimulatorCore;
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

public class NinjaCustomOpenerController {

    @FXML
    private Pane barForGrab;
    @FXML
    private GridPane gridOpener;


    @FXML
    private ImageView Spinning_Edge;
    @FXML
    private ImageView Gust_Slash;
    @FXML
    private ImageView Aeolian_Edge;
    @FXML
    private ImageView Dancing_Edge;
    @FXML
    private ImageView Armor_Crush;
    @FXML
    private ImageView Mutilate;
    @FXML
    private ImageView Shadow_Fang;
    @FXML
    private ImageView Fuma_Shuriken;
    @FXML
    private ImageView Raiton;
    @FXML
    private ImageView Suiton;
    @FXML
    private ImageView Trick_Attack;
    @FXML
    private ImageView Dream_Within_a_Dream;
    @FXML
    private ImageView Duality;
    @FXML
    private ImageView Kassatsu;
    @FXML
    private ImageView Mug;
    @FXML
    private ImageView Jugulate;
    @FXML
    private ImageView Blood_for_Blood;
    @FXML
    private ImageView Internal_Release;
    @FXML
    private ImageView Potion;


    private int columnIndex;
    private int rowIndex;
    private static ArrayList<String> opener;
    private static ArrayList<ImageView> saveCurrent;
    GUIclass guIclass = new GUIclass();


    public void mainSceneChanger(ActionEvent event) throws Exception {
        guIclass.mainSceneChanger(event);
    }

    public void CharSceneChanger(ActionEvent event) throws Exception {
        guIclass.CharSceneChanger(event);
    }

    public void closeStage(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void minimize(MouseEvent event) {
        guIclass.minimize(event);
    }


    public void pressedMove(MouseEvent me) {
        guIclass.pressedMove(me);
    }


    public void draggedMove(MouseEvent mouseEvent) {
        guIclass.draggedMove(mouseEvent, barForGrab);
    }

    public void saveOpener(ActionEvent event) {
        guIclass.writeStringToFile(opener, event);
        setOpener();
    }

    public void loadOpener(ActionEvent event) {
        ArrayList<ImageView> loadList = new ArrayList<>(30);
        ArrayList<String> stringArrayList = guIclass.loadOpener(event);

        for (String string : stringArrayList) {
            if (string.equals(Spinning_Edge.getId())) {
                loadList.add(setImage(Spinning_Edge));

            } else if (string.equals(Gust_Slash.getId())) {
                loadList.add(setImage(Gust_Slash));

            } else if (string.equals(Aeolian_Edge.getId())) {
                loadList.add(setImage(Aeolian_Edge));

            } else if (string.equals(Dancing_Edge.getId())) {
                loadList.add(setImage(Dancing_Edge));

            } else if (string.equals(Armor_Crush.getId())) {
                loadList.add(setImage(Armor_Crush));

            } else if (string.equals(Mutilate.getId())) {
                loadList.add(setImage(Mutilate));

            } else if (string.equals(Shadow_Fang.getId())) {
                loadList.add(setImage(Shadow_Fang));

            } else if (string.equals(Fuma_Shuriken.getId())) {
                loadList.add(setImage(Fuma_Shuriken));

            } else if (string.equals(Raiton.getId())) {
                loadList.add(setImage(Raiton));

            } else if (string.equals(Suiton.getId())) {
                loadList.add(setImage(Suiton));

            } else if (string.equals(Trick_Attack.getId())) {
                loadList.add(setImage(Trick_Attack));

            } else if (string.equals(Dream_Within_a_Dream.getId())) {
                loadList.add(setImage(Dream_Within_a_Dream));

            } else if (string.equals(Duality.getId())) {
                loadList.add(setImage(Duality));

            } else if (string.equals(Kassatsu.getId())) {
                loadList.add(setImage(Kassatsu));

            } else if (string.equals(Mug.getId())) {
                loadList.add(setImage(Mug));

            } else if (string.equals(Jugulate.getId())) {
                loadList.add(setImage(Jugulate));

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
