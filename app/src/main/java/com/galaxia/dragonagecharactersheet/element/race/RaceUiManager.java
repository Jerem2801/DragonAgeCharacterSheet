package com.galaxia.dragonagecharactersheet.element.race;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeEnum;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class RaceUiManager {



    private RaceUiManager(){

    }


    public static String setInitialSpeed(Race race) {
        Attribute dexterity = AttributeManager.getAttribute(AttributeEnum.DEXTERITY);
        return String.valueOf(race.getInitialSpeed());
    }

    public static String setClasseAvailable(Race race) {
        List<Classe> classeAvailable = race.getClasseAvailable();
        StringBuilder builder = new StringBuilder();
        for(Classe classe : classeAvailable){
            if(builder.length() == 0){
                builder.append(classe.getName());
            }else{
                builder.append(", "+classe.getName());
            }
            //builder.append(ViewFormaterString.LINE_SEPARATOR);
        }
        return builder.toString().trim();
    }
}
