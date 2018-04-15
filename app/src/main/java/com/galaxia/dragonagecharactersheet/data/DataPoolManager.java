package com.galaxia.dragonagecharactersheet.data;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTableManager;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.language.LanguageManager;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroupManager;

import java.util.List;
import java.util.Map;

public class DataPoolManager {

    private DataPoolManager(){

    }

    public static DataPool getDataLoader(Context context){
        Map<String,Language> languages = LanguageManager.getLanguageData(context);
        Map<String,Attribute> attributes = AttributeManager.getAttributeData(context);
        Map<String,Focus> focus = FocusManager.getFocusData(context, attributes);
        Map<String,WeaponGroup> weaponGroups = WeaponGroupManager.getWeaponGroupData(context,attributes);
        Map<String,Classe> classes = ClasseManager.getClasseData(context,attributes,weaponGroups);
        Map<String,Race> races = RaceManager.getRaceData(context, classes);
        Map<String, List<BackgroundTable>> backgroundTable = BackgroundTableManager.getAttributeData(context, attributes, focus, weaponGroups, languages);
        Map<String,Background> backgrounds = BackgroundManager.getBackgroundData(context,attributes,focus,languages,backgroundTable);


        DataPool dataPool = new DataPool();
        dataPool.setLanguages(languages);
        dataPool.setAttributes(attributes);
        dataPool.setFocus(focus);
        dataPool.setWeaponGroups(weaponGroups);
        dataPool.setClasses(classes);
        dataPool.setRaces(races);
        dataPool.setBackgrounds(backgrounds);

        return dataPool;
    }
}
