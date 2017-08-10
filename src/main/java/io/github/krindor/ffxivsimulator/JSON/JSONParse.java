package io.github.krindor.ffxivsimulator.JSON;

import com.jsoniter.JsonIterator;
import io.github.krindor.ffxivsimulator.TextFileLoader;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by andre on 2017-06-21.
 */
public class JSONParse {


    public JsonIterator parseJSON(String path) {

        ArrayList<String> stringArrayList;

        File file = new File(path);
        TextFileLoader textFileLoader = new TextFileLoader();
        stringArrayList = textFileLoader.loadText(file);
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : stringArrayList) {
            stringBuilder.append(s);
            stringBuilder.append("\t");
        }


        return JsonIterator.parse(stringBuilder.toString());
    }
}
