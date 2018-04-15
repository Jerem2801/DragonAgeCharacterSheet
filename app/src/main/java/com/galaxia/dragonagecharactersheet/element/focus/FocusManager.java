package com.galaxia.dragonagecharactersheet.element.focus;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FocusManager {

    private static final String FOCUS_DIR = RessourcePath.DATA_PATH + "focus/";
    private static final String FOCUS_CSV_PATH = FOCUS_DIR + "focus.csv";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;
    private static final int ATTRIBUTE_ID = 3;

    private FocusManager(){

    }

    public static Map<String,Focus> getFocusData(Context context, Map<String,Attribute> attributes){
        Map<String,Focus> focuss = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,FOCUS_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String attributeId = splitData[ATTRIBUTE_ID];
            Attribute attribute = attributes.get(attributeId);

            Focus focus = new Focus(id,name,description,attribute);
            focuss.put(id,focus);
        }

        return focuss;
    }


}
