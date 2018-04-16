package com.galaxia.dragonagecharactersheet.element.classe;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClasseManager {

    private static final String CLASSE_DIR = RessourcePath.DATA_PATH + "classe/";
    private static final String CLASSE_CSV_PATH = CLASSE_DIR + "classe.csv";
    private static final String CLASSE_IMAGE_DIR = CLASSE_DIR + "image/";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;
    private static final int PRIMARY_ATTRIBUTES = 3;
    private static final int SECONDARY_ATTRIBUTES = 4;
    private static final int INITIAL_HEALTH = 5;
    private static final int WEAPON_GROUP_STARTING = 6;
    private static final int IMAGE_FILE_NAME = 7;

    private ClasseManager(){

    }

    public static Map<String,Classe> getClasseData(Context context){
        Map<String,Classe> classes = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,CLASSE_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];

            String initialHealthString = splitData[INITIAL_HEALTH];
            int initialHealth = Integer.valueOf(initialHealthString);

            String primaryAttributesString = splitData[PRIMARY_ATTRIBUTES];
            List<String> primaryAttributesId = getListOfId(primaryAttributesString);

            String secondaryAttributesString= splitData[SECONDARY_ATTRIBUTES];
            List<String> secondaryAttributesId = getListOfId(secondaryAttributesString);

            String weaponGroupStartingString = splitData[WEAPON_GROUP_STARTING];
            List<String> weaponGroupStartingId = getListOfId(weaponGroupStartingString);

            String imageFileName = splitData[IMAGE_FILE_NAME];
            String imagePath = CLASSE_IMAGE_DIR + imageFileName;

            Classe classe = new Classe(id,name,description,initialHealth,primaryAttributesId,secondaryAttributesId,weaponGroupStartingId,imagePath);
            classes.put(id,classe);
        }

        return classes;
    }

    private static List<String> getListOfId(String attributesString) {
        String idList[] = attributesString.split(RessourceConstant.AND);
        return new ArrayList<>(Arrays.asList(idList));
    }


    public static List<Classe> getClasses(List<String> classeIds) {
        List<Classe> classes = Lists.newArrayList();

        for(String classeId : classeIds){
            classes.add(getClasse(classeId));
        }

        return classes;
    }

    public static Classe getClasse(String classeId) {
        DataPool dataPool = DataPool.getInstance();
        Map<String, Classe> classes = dataPool.getClasses();
        return classes.get(classeId);
    }
}
