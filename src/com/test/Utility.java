package com.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Utility {

    /*
    returns value of a config item from properties file match the passed key
    returns String
     */
    public String getConfigByKey(String key)
    {
        String line = null;
        String value = null;
        try {
            FileReader fr = new FileReader(".\\src\\config.properties");
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while(line != null)
            {
                String[] lineArray = line.split("=");

                if(lineArray[0].trim().equalsIgnoreCase(key.trim()))
                {
                    value = lineArray[1].trim();
                    break;
                }
                line = br.readLine();
            }
            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }


    /*
    returns the content of the input file in array list
    returns List<String>
     */
    public  List<String> readInputFile(String file)
    {
        String line = null;
        List<String> inputList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while(line != null)
            {
                inputList.add(line);
                line=br.readLine();
            }
            fr.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }
}
