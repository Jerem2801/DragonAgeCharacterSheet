package com.galaxia.dragonagecharactersheet.data;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.Background;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.language.Language;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;

import java.util.Map;

public class DataPool {

    private static DataPool dataPool;

    private Map<String,Language> languages;
    private Map<String,Attribute> attributes;
    private Map<String,Focus> focus;
    private Map<String,WeaponGroup> weaponGroups;
    private Map<String,Classe> classes;
    private Map<String,Race> races;
    private Map<String,Background> backgrounds;

    public Map<String, Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, Language> languages) {
        this.languages = languages;
    }

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
    }

    public Map<String, WeaponGroup> getWeaponGroups() {
        return weaponGroups;
    }

    public void setWeaponGroups(Map<String, WeaponGroup> weaponGroups) {
        this.weaponGroups = weaponGroups;
    }

    public Map<String, Classe> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, Classe> classes) {
        this.classes = classes;
    }

    public Map<String, Race> getRaces() {
        return races;
    }

    public void setRaces(Map<String, Race> races) {
        this.races = races;
    }

    public Map<String, Focus> getFocus() {
        return focus;
    }

    public void setFocus(Map<String, Focus> focus) {
        this.focus = focus;
    }

    public Map<String, Background> getBackgrounds() {
        return backgrounds;
    }

    public void setBackgrounds(Map<String, Background> backgrounds) {
        this.backgrounds = backgrounds;
    }

    public synchronized static DataPool initialize(Context context){
        if(dataPool == null){
            dataPool = DataPoolManager.getDataLoader(context);
        }
        return dataPool;
    }

    public synchronized static DataPool getInstance(){
        return dataPool;
    }

}
