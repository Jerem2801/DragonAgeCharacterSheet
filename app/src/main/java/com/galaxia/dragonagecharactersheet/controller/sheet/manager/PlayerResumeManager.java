package com.galaxia.dragonagecharactersheet.controller.sheet.manager;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerResumeBean;
import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.background.BackgroundManager;
import com.galaxia.dragonagecharactersheet.element.classe.ClasseManager;
import com.galaxia.dragonagecharactersheet.element.race.RaceManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class PlayerResumeManager {

    private static final int NAME = 0;
    private static final int RACE = 1;
    private static final int CLASSE = 2;
    private static final int BACKGROUND = 3;
    private static final int LEVEL = 4;

    private PlayerResumeManager(){

    }

    public static PlayerResumeBean getPlayerResume(Context context){
        List<String> datas = RessourceUtils.getData(context, "character/lukadarkard_resume.csv", true);

        PlayerResumeBean bean = null;

        for(String data : datas){
            String[] splitData = StringUtils.split(data, RessourceConstant.SEPARATOR);
            String name = splitData[NAME];

            String race = splitData[RACE];

            String classe = splitData[CLASSE];

            String background = splitData[BACKGROUND];

            String level = splitData[LEVEL];

            bean = new PlayerResumeBean(name,race,classe,background,level);
        }

        return bean;
    }
}
