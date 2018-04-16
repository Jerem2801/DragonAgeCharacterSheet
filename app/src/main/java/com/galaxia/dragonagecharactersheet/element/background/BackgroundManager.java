package com.galaxia.dragonagecharactersheet.element.background;

import android.content.Context;
import android.text.TextUtils;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackgroundManager {

    private static final String BACKGROUND_DIR = RessourcePath.DATA_PATH + "background/";
    private static final String BACKGROUND_CSV_PATH = BACKGROUND_DIR + "background.csv";
    private static final String BACKGROUND_IMAGE_DIR = BACKGROUND_DIR + "image/";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;
    private static final int INCREASE_ATTRIBUTES = 3;
    private static final int INCREASE_ATTRIBUTES_DESC = 4;
    private static final int FOCUS = 5;
    private static final int SPOKEN_LANGUAGES= 6;
    private static final int WRITEN_LANGUAGES= 7;
    private static final int RACES = 8;
    private static final int CLASSES = 9;
    private static final int IMAGE_FILE_PATH = 10;

    private BackgroundManager(){

    }

    public static Map<String,Background> getBackgroundData(Context context, Map<String,List<BackgroundTable>> backgroundTables){
        Map<String,Background> backgrounds = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,BACKGROUND_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];

            String increaseAttributeId = splitData[INCREASE_ATTRIBUTES];
            String increaseAttributeDesc = splitData[INCREASE_ATTRIBUTES_DESC];

            String focusString = splitData[FOCUS];
            List<String> focussId = getIds(focusString);

            String spokenLanguagesString = splitData[SPOKEN_LANGUAGES];
            List<String> spokenLanguagesId = getIds(spokenLanguagesString);
            String writenLanguagesString = splitData[WRITEN_LANGUAGES];
            List<String> writenLanguagesId = getIds(writenLanguagesString);

            String racesString = splitData[RACES];
            List<String> racesAvailable = getIds(racesString);
            String classesString = splitData[CLASSES];
            List<String> classesAvailable = getIds(classesString);

            String imagePath = "";
            if(splitData.length > 10){
                String imageFileName = splitData[IMAGE_FILE_PATH];
                imagePath = BACKGROUND_IMAGE_DIR + imageFileName;
            }

            List<BackgroundTable> backgroundTable = backgroundTables.get(id);

            Background background = new Background(id,name,description,increaseAttributeId,increaseAttributeDesc,focussId,spokenLanguagesId,writenLanguagesId,backgroundTable,imagePath,racesAvailable,classesAvailable);
            backgrounds.put(id,background);
        }

        return backgrounds;
    }

    private static List<String> getIds(String id) {
        String[] splitData = id.split(RessourceConstant.AND);
        return Arrays.asList(splitData);
    }


    public static List<Background> getBackground(Map<String, Background> backgrounds, String race, String classe) {
        List<Background> backgroundsSelected = new ArrayList<>();

        for(Background background : backgrounds.values()){
            if(background.getRaceAvailableId().contains(race) && background.getClasseAvailableId().contains(classe)){
                backgroundsSelected.add(background);
            }
        }

        return backgroundsSelected;
    }
}
