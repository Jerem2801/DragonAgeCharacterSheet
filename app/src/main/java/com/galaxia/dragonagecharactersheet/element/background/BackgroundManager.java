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

    public static Map<String,Background> getBackgroundData(Context context, Map<String,Attribute> attributes, Map<String,Focus> focus, Map<String,Language> languages, Map<String,List<BackgroundTable>> backgroundTables){
        Map<String,Background> backgrounds = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,BACKGROUND_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];

            String increaseAttribute = splitData[INCREASE_ATTRIBUTES];
            Attribute attribute = attributes.get(increaseAttribute);
            String increaseAttributeDesc = splitData[INCREASE_ATTRIBUTES_DESC];

            String focusString = splitData[FOCUS];
            List<Focus> focuss = getFocus(focusString,focus);

            String spokenLanguagesString = splitData[SPOKEN_LANGUAGES];
            List<Language> spokenLanguages = getLanguages(spokenLanguagesString,languages);
            String writenLanguagesString = splitData[WRITEN_LANGUAGES];
            List<Language> writenLanguages = getLanguages(writenLanguagesString,languages);

            String racesString = splitData[RACES];
            List<String> racesAvailable = getAvailable(racesString);
            String classesString = splitData[CLASSES];
            List<String> classesAvailable = getAvailable(classesString);

            String imagePath = "";
            if(splitData.length > 10){
                String imageFileName = splitData[IMAGE_FILE_PATH];
                imagePath = BACKGROUND_IMAGE_DIR + imageFileName;
            }

            List<BackgroundTable> backgroundTable = backgroundTables.get(id);

            Background background = new Background(id,name,description,attribute,increaseAttributeDesc,focuss,spokenLanguages,writenLanguages,backgroundTable,imagePath,racesAvailable,classesAvailable);
            backgrounds.put(id,background);
        }

        return backgrounds;
    }

    private static List<String> getAvailable(String racesString) {
        String[] splitData = racesString.split(RessourceConstant.AND);
        return Arrays.asList(splitData);
    }

    private static List<Language> getLanguages(String languagesListString, Map<String, Language> languages) {
        List<Language> languageList = new ArrayList<>();
        String[] splitData = languagesListString.split(RessourceConstant.AND);
        for(String languageString : splitData){
            Language language = languages.get(languageString);
            languageList.add(language);
        }
        return languageList;
    }

    private static List<Focus> getFocus(String focusStringList, Map<String, Focus> focuss) {
        List<Focus> focusList = new ArrayList<>();
        String[] splitData = focusStringList.split(RessourceConstant.AND);
        for(String focusString : splitData){
            Focus focus = focuss.get(focusString);
            focusList.add(focus);
        }
        return focusList;
    }

    public static List<Background> getBackground(Map<String, Background> backgrounds, String race, String classe) {
        List<Background> backgroundsSelected = new ArrayList<>();

        for(Background background : backgrounds.values()){
            if(background.getRaceAvailable().contains(race) && background.getClasseAvailable().contains(classe)){
                backgroundsSelected.add(background);
            }
        }

        return backgroundsSelected;
    }
}
