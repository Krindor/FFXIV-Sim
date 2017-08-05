package io.github.krindor.ffxivsimulator.RotationOpenerClasses;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.JSON.JSONParse;
import io.github.krindor.ffxivsimulator.JSON.OpeningSequence.ActionObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andre on 2017-08-02.
 */
public class OpenerSequence {
    private ActionObject actionObject;
    public OpenerSequence(String path){
        JsonIterator jsonIterator;
        JSONParse jsonParse = new JSONParse();
        jsonIterator = jsonParse.parseJSON(path);

        try {
            actionObject = jsonIterator.read(ActionObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ActionObject getActionObject() {
        return actionObject;
    }
}
