package com.galaxia.dragonagecharactersheet.player;

import com.galaxia.dragonagecharactersheet.element.attribute.AttributeEnum;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableManager;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class PlayerManager {

    private PlayerManager(){

    }

    public static Map<String, Integer> initializeAttribute() {
        Map<String, Integer> attributes = Maps.newHashMap();
        for(AttributeEnum attribute : AttributeEnum.values()){
            attributes.put(attribute.getId(),0);
        }
        return attributes;
    }

    public static void setBonusBackground(Player player, BackgroundTable... backgroundTables){
        for(BackgroundTable backgroundTable : backgroundTables){
            BackgroundTableManager.setBonusToPlayer(player,backgroundTable);
        }
    }

    public static void addFocus(Player player,String focusId){
        List<String> focusIds = player.getFocusIds();
        if(!focusIds.contains(focusId)){
            focusIds.add(focusId);
        }
    }

    public static void addAttribute(Player player,String attributeId,int add){
        Map<String, Integer> attributes = player.getAttribute();
        int value = attributes.get(attributeId);
        value += add;
        attributes.put(attributeId,value);
    }

    public static void addSpokenLanguage(Player player,String languageId){
        List<String> spokenLanguages = player.getSpokenLanguages();
        if(!spokenLanguages.contains(languageId)){
           spokenLanguages.add(languageId);
        }
    }

    public static void addWeaponGroup(Player player,String weaponGroupId){
        List<String> weaponGroupIds = player.getWeaponGroupIds();
        if(!weaponGroupIds.contains(weaponGroupId)){
            weaponGroupIds.add(weaponGroupId);
        }
    }



}
