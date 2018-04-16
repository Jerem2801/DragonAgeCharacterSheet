package com.galaxia.dragonagecharactersheet.element.language;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanguageManager {

    private static final String LANGUAGE_DIR = RessourcePath.DATA_PATH + "language/";
    private static final String LANGUAGE_DIR_CSV_PATH = LANGUAGE_DIR + "language.csv";

    private static final int ID = 0;
    private static final int NAME = 1;

    private LanguageManager(){

    }

    public static Map<String,Language> getLanguageData(Context context){
        Map<String,Language> languages = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,LANGUAGE_DIR_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];

            Language language = new Language(id,name);
            languages.put(id,language);
        }

        return languages;
    }

    public static Language getLanguage(String languageId){
        DataPool dataPool = DataPool.getInstance();
        Map<String, Language> languages = dataPool.getLanguages();
        return languages.get(languageId);
    }

    public static List<Language> getLanguage(List<String> languageIds) {
        List<Language> languages = Lists.newArrayList();
        for(String languageId : languageIds){
            languages.add(getLanguage(languageId));
        }
        return languages;
    }
}
