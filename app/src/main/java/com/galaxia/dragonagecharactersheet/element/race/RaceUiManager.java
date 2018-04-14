package com.galaxia.dragonagecharactersheet.element.race;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeEnum;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class RaceUiManager {


    private RaceUiManager(){

    }

    public static String setSpeed(Race race,String phrase){
        String name = race.getName();
        String speed = String.valueOf(race.getInitialSpeed());
        Attribute attribute = AttributeManager.getAttribute(AttributeEnum.DEXTERITY);
        String dexterity = attribute.getName();
        return String.format(phrase, name,speed,dexterity);
    }

    public static String setClasseAvailable(Race race) {
        List<Classe> classeAvailable = race.getClasseAvailable();
        StringBuilder builder = new StringBuilder();
        for(Classe classe : classeAvailable){
            if(builder.toString().length() == 0){
                builder.append(classe.getName());
            }else{
                builder.append(ViewFormaterString.COMMA + classe.getName());
            }
        }
        return builder.toString().trim();
    }
}
