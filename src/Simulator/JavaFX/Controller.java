package Simulator.JavaFX;


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

public class Controller {

    @FXML
    private Pane barForGrab;


    public void customizeSceneChanger(ActionEvent event) throws Exception{
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("CustomizeFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("Customize");
        customize.show();
    }

    public void SimulatorClassChooserSceneChanger(ActionEvent event) throws Exception{
        Parent customizeSceneParent = FXMLLoader.load(getClass().getResource("ClassChooserFX.fxml"));
        Scene customizeScene = new Scene(customizeSceneParent);
        Stage customize = (Stage) ((Node) event.getSource()).getScene().getWindow();
        customize.setScene(customizeScene);
        customize.setTitle("ClassChooser");
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
}
