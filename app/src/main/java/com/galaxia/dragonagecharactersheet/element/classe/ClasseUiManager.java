package com.galaxia.dragonagecharactersheet.element.classe;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeEnum;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class ClasseUiManager {

    private ClasseUiManager(){

    }

    public static String setHeatlh(Classe classe,String phrase){
        String health = String.valueOf(classe.getInitialHealth());
        Attribute attribute = AttributeManager.getAttribute(AttributeEnum.CONSTITUTION);
        String constitution = attribute.getName();
        return String.format(phrase, health,constitution);
    }

    public static String getWeaponGroupStarting(Classe classe) {
        List<String> weaponGroupsStartingId = classe.getWeaponGroupStartingId();

        StringBuilder builder = new StringBuilder();
        for(String weaponGroupStartingId : weaponGroupsStartingId){

            WeaponGroup weapongGroupStarting = WeaponGroupManager.getWeaponGroup(weaponGroupStartingId);
            if(builder.length() == 0){
                builder.append(weapongGroupStarting.getName());
            }else{
                builder.append(ViewFormaterString.COMMA + weapongGroupStarting.getName());
            }

        }
        return builder.toString().trim();
    }

    public static String getAttributes(List<String> attributesId){
        StringBuilder builder = new StringBuilder();
        for(String attributeId : attributesId){
            Attribute attribute = AttributeManager.getAttribute(attributeId);

            if(builder.length() == 0){
                builder.append(attribute.getName());
            }else{
                builder.append(ViewFormaterString.COMMA + attribute.getName());
            }

        }
        return builder.toString().trim();
    }
}
