package com.galaxia.dragonagecharactersheet.element.classe;

import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;

import java.util.List;

public class ClasseUiManager {

    private ClasseUiManager(){

    }

    public static String setWeaponGroup(Classe classe) {
        List<WeaponGroup> weaponGroups = classe.getWeaponGroupStarting();
        StringBuilder builder = new StringBuilder();
        for(WeaponGroup weaponGroup : weaponGroups){
            builder.append(weaponGroup.getName());
            builder.append(ViewFormaterString.LINE_SEPARATOR);
        }
        return builder.toString().trim();
    }
}
