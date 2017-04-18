package io.github.krindor.ffxivsimulator.JavaFX;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;


/**
 * Created by andre on 2017-04-18.
 */
public interface GUIinterface {
    void mainSceneChanger(ActionEvent event) throws Exception;

    void closeProgram();

    void minimize(MouseEvent event);

    void pressedMove(MouseEvent me);

    void draggedMove(MouseEvent mouseEvent);

}
