package com.galaxia.dragonagecharactersheet.element.classe;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeEnum;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class ClasseUiManager {

    private ClasseUiManager(){

    }

    public static String setHeatlh(Classe classe,String phrase){
        String name = classe.getName();
        String health = String.valueOf(classe.getInitialHealth());
        Attribute attribute = AttributeManager.getAttribute(AttributeEnum.CONSTITUTION);
        String constitution = attribute.getName();
        return String.format(phrase, name,health,constitution);
    }

    public static String setWeaponGroup(Classe classe) {
        List<WeaponGroup> weaponGroups = classe.getWeaponGroupStarting();
        StringBuilder builder = new StringBuilder();
        for(WeaponGroup weaponGroup : weaponGroups){
            if(builder.length() == 0){
                builder.append(weaponGroup.getName());
            }else{
                builder.append(ViewFormaterString.COMMA + weaponGroup.getName());
            }
        }
        return builder.toString().trim();
    }

    public static String setAttributeList(List<Attribute> attributes){
        StringBuilder builder = new StringBuilder();
        for(Attribute attribute : attributes){
            if(builder.length() == 0){
                builder.append(attribute.getName());
            }else{
                builder.append(ViewFormaterString.COMMA + attribute.getName());
            }
        }
        return builder.toString().trim();
    }
}
