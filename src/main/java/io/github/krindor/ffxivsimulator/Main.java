package io.github.krindor.ffxivsimulator;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.JSON.SkillDB.ConfigObject;
import io.github.krindor.ffxivsimulator.JSON.JSONParse;

import io.github.krindor.ffxivsimulator.JobClasses.JobJSON;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("JavaFX/MainFX.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(new Scene(root, 1200, 700));
        primaryStage.show();
        primaryStage.setResizable(false);
        JobJSON jobJSON = new JobJSON();
        JSONParse jsonParse = new JSONParse();
        JsonIterator iterator = jsonParse.parseJSON("./JSON/Buffs.json");
        ConfigObject configObject;
        try {
            configObject = iterator.read(ConfigObject.class);
            jobJSON.setJobs(configObject.getJob());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static void main(String[] args) {
        launch(args);
    }
}
