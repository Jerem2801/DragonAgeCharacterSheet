package com.galaxia.dragonagecharactersheet.element.race;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RaceManager {

    private static final String RACE_DIR = RessourcePath.DATA_PATH + "race/";
    private static final String RACE_CSV_PATH = RACE_DIR + "race.csv";
    private static final String RACE_IMAGE_DIR = RACE_DIR + "image/";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;
    private static final int CLASSE_AVAILABLE = 3;
    private static final int INITIAL_SPEED = 4;
    private static final int IMAGE_FILE_NAME = 5;

    private RaceManager(){

    }

    public static Map<String,Race> getRace(Context context, Map<String,Classe> classes){
        Map<String,Race> races = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,RACE_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String initialSpeedString = splitData[INITIAL_SPEED];
            int initialSpeed = Integer.valueOf(initialSpeedString);
            String classeAvailableString = splitData[CLASSE_AVAILABLE];
            List<Classe> classeAvailable = getClasseFromString(classeAvailableString,classes);
            String imageFileName = splitData[IMAGE_FILE_NAME];
            String imagePath = RACE_IMAGE_DIR + imageFileName;

            Race race= new Race(id,name,description,initialSpeed,classeAvailable,imagePath);
            races.put(id,race);
        }

        return races;
    }

    private static List<Classe> getClasseFromString(String classeAvailableString,Map<String,Classe> classesWithId) {
        List<Classe> classes = new ArrayList<>();

        String splitData[] = classeAvailableString.split(RessourceConstant.AND);
        for(String data : splitData){
            Classe classe = classesWithId.get(data);
            classes.add(classe);
        }
        return classes;
    }

}
