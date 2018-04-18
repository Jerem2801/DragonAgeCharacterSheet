package com.galaxia.dragonagecharactersheet.element.weapongroup;

import java.util.List;

public class WeaponGroupUiManager {

    private WeaponGroupUiManager(){

    }

    public static String getWeaponGroupName(List<String> weaponGroupsIds){
        StringBuilder builder = new StringBuilder();

        for(String weaponGroupId : weaponGroupsIds){
            WeaponGroup weaponGroup = WeaponGroupManager.getWeaponGroup(weaponGroupId);
            if(builder.toString().length() == 0){
                builder.append(weaponGroup.getName());
            }else{
                builder.append(", " + weaponGroup.getName());
            }
        }

        return builder.toString().trim();
    }
}
