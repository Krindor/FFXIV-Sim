package Simulator.JavaFX;


import Simulator.SimulatorCore;
import javafx.application.Platform;
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

import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.CheckBox;

import javafx.scene.control.MenuItem;

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
        damageLog.clear();
       System.out.println("It's alive");
        int weaponDamage;
        if (weaponText.getText() != null && !weaponText.getText().isEmpty()) {
            weaponDamage = Integer.parseInt(weaponText.getText());
        }else weaponDamage = 85;

        int mainStat;
        if (dexText.getText() != null && !dexText.getText().isEmpty()) {
            mainStat = Integer.parseInt(dexText.getText());
        }else mainStat = 1589;
        int crit;
        if (critText.getText() != null && !critText.getText().isEmpty()) {
            crit = Integer.parseInt(critText.getText());
        }else crit = 1366;
        int det;
        if (detText.getText() != null && !detText.getText().isEmpty()) {
            det = Integer.parseInt(detText.getText());
        }else det = 689;
        int skillSpeed;
        if (ssText.getText() != null && !ssText.getText().isEmpty()) {
            skillSpeed = Integer.parseInt(ssText.getText());
        }else   skillSpeed = 444;

       int time;
       if (simTimeText.getText() != null && !simTimeText.getText().isEmpty()) {
           time = Integer.parseInt(simTimeText.getText());
       }else time = 180;

       int hutonTime;
       if (hutonText.getText() != null && !hutonText.getText().isEmpty()) {
           hutonTime = Integer.parseInt(hutonText.getText());
       }else hutonTime = 23;



        SimulatorCore sim = new SimulatorCore();
        sim.setMainStat(weaponDamage, mainStat, crit, det, skillSpeed);
        sim.setTime(time);
        sim.setHutonTime(hutonTime);
        sim.setOpenerType(opener);
        sim.setWarThere(warrior.selectedProperty().get());

       log = sim.runSim();
       String logtext = "Sim start";
       for(String x: log){
           logtext = logtext + "\n" + x;
       }
           damageLog.setText(logtext);



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
