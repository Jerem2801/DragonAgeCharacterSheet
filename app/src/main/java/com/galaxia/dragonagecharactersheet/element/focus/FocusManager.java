package com.galaxia.dragonagecharactersheet.element.focus;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Lists;

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

    public static Map<String,Focus> getFocusData(Context context){
        Map<String,Focus> focuss = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,FOCUS_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String attributeId = splitData[ATTRIBUTE_ID];

            Focus focus = new Focus(id,name,description,attributeId);
            focuss.put(id,focus);
        }

        return focuss;
    }

    public static Focus getFocus(String focusId){
        DataPool dataPool = DataPool.getInstance();
        Map<String, Focus> focuss = dataPool.getFocus();
        return focuss.get(focusId);
    }


    public static List<Focus> getFocus(List<String> chooseFocusId) {
        List<Focus> focuss = Lists.newArrayList();
        for(String focusId: chooseFocusId){
            focuss.add(getFocus(focusId));
        }
        return focuss;
    }

    public static String getFocusNames(List<String> focusIds) {
        StringBuilder builder = new StringBuilder();
        List<Focus> focuss = getFocus(focusIds);
        for(Focus focus : focuss){
            if(builder.toString().length() == 0){
                builder.append(focus.getName());
            }else{
                builder.append("," + focus.getName());
            }
        }
        return builder.toString().trim();
    }
}
