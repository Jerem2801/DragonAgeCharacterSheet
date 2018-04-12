package com.galaxia.dragonagecharactersheet.data;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;

import java.util.Map;

public class DataPoolManager {

    private DataPoolManager(){

    }

    public static DataPool getDataLoader(Context context){
        Map<String,Attribute> attributes = AttributeManager.getAttribute(context);
        Map<String,WeaponGroup> weaponGroups = WeaponGroupManager.getWeaponGroup(context,attributes);
        Map<String,Classe> classes = ClasseManager.getClasse(context,attributes,weaponGroups);
        Map<String, Race> races = RaceManager.getRace(context, classes);

        DataPool dataPool = new DataPool();
        dataPool.setAttributes(attributes);
        dataPool.setWeaponGroups(weaponGroups);
        dataPool.setClasses(classes);
        dataPool.setRaces(races);

        return dataPool;
    }
}
