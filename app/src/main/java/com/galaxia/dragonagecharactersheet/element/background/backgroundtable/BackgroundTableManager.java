package com.galaxia.dragonagecharactersheet.element.background.backgroundtable;

import android.content.Context;
import android.text.TextUtils;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonus;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonusConstant;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BackgroundTableManager {

    private static final String BACKGROUND_DIR = RessourcePath.DATA_PATH + "background/";
    private static final String BACKGROUND_TABLE_CSV_PATH = BACKGROUND_DIR + "background_table.csv";

    private static final int ID = 0;
    private static final int ORDER = 1;
    private static final int ROLL = 2;
    private static final int TYPE = 3;
    private static final int BONUS = 4;

    private BackgroundTableManager(){

    }

    public static Map<String,List<BackgroundTable>> getAttributeData(Context context, Map<String,Attribute> attributes, Map<String,Focus> focus, Map<String,WeaponGroup> weaponGroups, Map<String,Language> languages){
        Map<String,List<BackgroundTable>> backgroundTables = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,BACKGROUND_TABLE_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String orderString = splitData[ORDER];
            int order = Integer.valueOf(orderString);
            String roll = splitData[ROLL];
            List<Integer> rolls = getRolls(roll);
            String type = splitData[TYPE];
            String bonus = splitData[BONUS];
            BackgroundBonus backgroundBonus = getBackgroundBonus(type,bonus,attributes,focus,weaponGroups,languages);

            BackgroundTable backgroundTable = new BackgroundTable(order,rolls,type,backgroundBonus);

            List<BackgroundTable> backgroundTableList = backgroundTables.get(id);
            if(backgroundTableList == null){
                backgroundTableList = new ArrayList<>();
                backgroundTables.put(id,backgroundTableList);
            }
            backgroundTableList.add(backgroundTable);


        }

        return backgroundTables;
    }

    private static BackgroundBonus getBackgroundBonus(String type, String bonus, Map<String, Attribute> attributes, Map<String, Focus> focus, Map<String, WeaponGroup> weaponGroups, Map<String, Language> languages) {
        BackgroundBonus backgroundBonus = null;

        if(TextUtils.equals(type, BackgroundBonusConstant.FOCUS)){
                backgroundBonus = focus.get(bonus);
        }else if(TextUtils.equals(type, BackgroundBonusConstant.ATTRIBUTE)){
            backgroundBonus = attributes.get(bonus);
        }else if(TextUtils.equals(type, BackgroundBonusConstant.LANGUAGE)){
            backgroundBonus = languages.get(bonus);
        }else if(TextUtils.equals(type, BackgroundBonusConstant.WEAPON_GROUP)){
            backgroundBonus = weaponGroups.get(bonus);
        }

        return backgroundBonus;
    }

    private static List<Integer> getRolls(String roll) {
        List<Integer> rolls = new ArrayList<>();
        String[] splitData = roll.split(RessourceConstant.AND);
        for(String numberString : splitData){
            Integer number = Integer.valueOf(numberString);
            rolls.add(number);
        }
        return rolls;
    }


}
