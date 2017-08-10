package io.github.krindor.ffxivsimulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by andre on 2017-05-18.
 */
public class TextFileLoader {
    public ArrayList<String> loadText(File file) {
        ArrayList<String> stringArrayList = new ArrayList<>(10);
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
