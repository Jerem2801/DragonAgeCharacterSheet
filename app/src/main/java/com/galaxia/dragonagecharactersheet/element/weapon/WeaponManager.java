package com.galaxia.dragonagecharactersheet.element.weapon;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeaponManager {

    private static final String WEAPON_DIR = RessourcePath.DATA_PATH + "weapon/";
    private static final String WEAPON_PATH = WEAPON_DIR + "weapons.csv";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int TYPE = 2;
    private static final int WEAPON_GROUP_ID = 3;
    private static final int DAMAGE = 4;
    private static final int MINIMUN_STRENGHT = 5;
    private static final int COST = 6;
    private static final int DESCRIPTION = 7;
    private static final int SHORT_RANGE = 8;
    private static final int LONG_RANGE = 9;
    private static final int RELOAD_ACTION = 10;

    private static final int NUMBER_DICE_DAMAGE = 0;
    private static final int DICE_DAMAGE = 1;
    private static final int BONUS_DAMAGE = 2;

    public static final String RANGE = "range";

    private WeaponManager(){

    }

    public static Map<String,Weapon> getWeaponData(Context context){
        Map<String,Weapon> weapons = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,WEAPON_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String type = splitData[TYPE];
            String weaponGroupId = splitData[WEAPON_GROUP_ID];

            String damage = splitData[DAMAGE];
            String[] splitDamage = StringUtils.split(damage, RessourceConstant.AND);
            int numberDiceDamage = Integer.parseInt(splitDamage[NUMBER_DICE_DAMAGE]);
            int diceDamage = Integer.parseInt(splitDamage[DICE_DAMAGE]);
            int bonusDamage = Integer.parseInt(splitDamage[BONUS_DAMAGE]);

            String minimunStrenght= splitData[MINIMUN_STRENGHT];

            String cost = splitData[COST];

            Weapon weapon = new Weapon(id,name,description,type,weaponGroupId,numberDiceDamage,diceDamage,bonusDamage,minimunStrenght,cost);

            if(StringUtils.equals(type,RANGE)){
                String shortRangeString = splitData[SHORT_RANGE];
                int shortRange = Integer.parseInt(shortRangeString);
                weapon.setShortRange(shortRange);

                String longRangeString = splitData[LONG_RANGE];
                int longRange = Integer.parseInt(longRangeString);
                weapon.setLongRange(longRange);

                String reloadAction = splitData[RELOAD_ACTION];
                weapon.setReloadAction(reloadAction);
            }


            weapons.put(id,weapon);
        }

        return weapons;
    }

    public static Weapon getWeapon(String weaponId) {
        DataPool dataPool = DataPool.getInstance();
        Map<String, Weapon> weapons = dataPool.getWeapons();
        return weapons.get(weaponId);
    }
}
