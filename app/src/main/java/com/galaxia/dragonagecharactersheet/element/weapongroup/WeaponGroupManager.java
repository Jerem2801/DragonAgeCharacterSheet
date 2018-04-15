package com.galaxia.dragonagecharactersheet.element.weapongroup;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponGroupManager {

    private static final String WEAPON_GROUP_DIR = RessourcePath.DATA_PATH + "weapon_group/";
    private static final String WEAPON_GROUP_CSV_PATH = WEAPON_GROUP_DIR + "weapon_group.csv";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;
    private static final int ATTRIBUTE_FOR_ATTACK = 3;

    private WeaponGroupManager(){

    }

    public static Map<String,WeaponGroup> getWeaponGroupData(Context context, Map<String,Attribute> attributes){
        Map<String,WeaponGroup> weaponGroups = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,WEAPON_GROUP_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String attributeId = splitData[ATTRIBUTE_FOR_ATTACK];
            Attribute attribute = attributes.get(attributeId);

            WeaponGroup weaponGroup = new WeaponGroup(id,name,description,attribute);
            weaponGroups.put(id,weaponGroup);
        }

        return weaponGroups;
    }
}
