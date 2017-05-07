package io.github.krindor.ffxivsimulator.JavaFX;

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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * Created by andre on 2017-04-18.
 */
public class GUIclass {
    @FXML
    private Pane barForGrab;


    public void mainSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("MainFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Main Menu");
        customize.show();
    }

    public void customizeSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("CustomizeFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Customize");
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

    public void SimulatorClassChooserSceneChanger(ActionEvent event) throws Exception {
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("ClassChooserFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("ClassChooser");
        customize.show();
    }

    public void closeProgram() {
        System.exit(0);
    }

    public void minimize(MouseEvent event) {
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setIconified(true);
    }

    private double initialX;
    private double initialY;

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



    public void writeStringToFile(ArrayList<String> log, ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Opener");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());

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

    public ArrayList<String> loadOpener(ActionEvent event) {

        ArrayList<String> stringArrayList = new ArrayList<>(30);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Opener");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        FileReader fileReader;
        BufferedReader reader;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                stringArrayList.add(currentLine);
            }

            try {
                if (reader != null) {
                    reader.close();
                }

                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringArrayList;
    }




}
