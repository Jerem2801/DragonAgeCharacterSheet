package com.galaxia.dragonagecharactersheet.element.background.backgroundtable;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.R;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonusConstant;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.focus.FocusUiManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.language.LanguageManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;
import com.galaxia.dragonagecharactersheet.player.Player;
import com.galaxia.dragonagecharactersheet.player.PlayerManager;
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

    public static Map<String,List<BackgroundTable>> getBackgroundTableData(Context context){
        Map<String,List<BackgroundTable>> backgroundTablesById = new HashMap<>();

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

            BackgroundTable backgroundTable = new BackgroundTable(order,rolls,type,bonus);

            List<BackgroundTable> backgroundTableList = backgroundTablesById.get(id);
            if(backgroundTableList == null){
                backgroundTableList = new ArrayList<>();
                backgroundTablesById.put(id,backgroundTableList);
            }
            backgroundTableList.add(backgroundTable);


        }

        return backgroundTablesById;
    }


    private static List<Integer> getRolls(String rollsString) {
        List<Integer> rolls = new ArrayList<>();
        String[] rollsList = rollsString.split(RessourceConstant.AND);
        for(String rollString : rollsList){
            Integer roll = Integer.valueOf(rollString);
            rolls.add(roll);
        }
        return rolls;
    }

    public static BackgroundTable getBackgroundTableWithRoll(List<BackgroundTable> backgroundTables,int roll){
        BackgroundTable result = null;
        for(BackgroundTable backgroundTable : backgroundTables){
            if(backgroundTable.getRoll().contains(roll)){
                result = backgroundTable;
            }
        }
        return result;
    }

    public static void setBonusToPlayer(Player player, BackgroundTable backgroundTable) {
        String type = backgroundTable.getType();
        String bonusId = backgroundTable.getBonus();
        switch(type){
            case BackgroundBonusConstant.ATTRIBUTE:
                PlayerManager.addAttribute(player,bonusId,1);
                break;
            case BackgroundBonusConstant.FOCUS:
                PlayerManager.addFocus(player,bonusId);
                break;
            case BackgroundBonusConstant.LANGUAGE:
                PlayerManager.addSpokenLanguage(player,bonusId);
                break;
            case BackgroundBonusConstant.WEAPON_GROUP:
                PlayerManager.addWeaponGroup(player,bonusId);
                break;
        }
    }



}
