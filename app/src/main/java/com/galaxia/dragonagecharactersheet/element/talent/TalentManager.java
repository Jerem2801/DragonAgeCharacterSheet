package com.galaxia.dragonagecharactersheet.element.talent;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.galaxia.dragonagecharactersheet.ui.ViewFormaterString;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TalentManager {

    private static final String TALENT_DIR = RessourcePath.DATA_PATH + "talent/";
    private static final String TALENT_CSV_PATH = TALENT_DIR + "talent.csv";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int CLASSES = 2;
    private static final int REQUIREMENT = 3;
    private static final int DESCRIPTION = 4;
    private static final int NOVICE = 5;
    private static final int JOURNEYMAN = 6;
    private static final int MASTER = 7;

    private TalentManager(){

    }

    public static Map<String,Talent> getTalentData(Context context){
        Map<String,Talent> talents = Maps.newHashMap();

        List<String> data = RessourceUtils.getData(context,TALENT_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String classesString = splitData[CLASSES];
            List<String> classes = Arrays.asList(StringUtils.split(classesString, ViewFormaterString.SEPARATOR));
            String requirement = splitData[REQUIREMENT];
            String description = splitData[DESCRIPTION];
            String novice = splitData[NOVICE];
            String journeyman = splitData[JOURNEYMAN];
            String master = splitData[MASTER];

            Talent talent  = new Talent(id,name,classes,requirement,description,novice,journeyman,master);

            talents.put(id,talent);
        }

        return talents;
    }



    public static Talent getTalent(String talentId){
        DataPool dataPool = DataPool.getInstance();
        Map<String, Talent> talents = dataPool.getTalents();
        return talents.get(talentId);
    }

    public static List<Talent> getTalents(List<String> talentIds) {
        List<Talent> talents = Lists.newArrayList();

        for(String talentId : talentIds){
            talents.add(getTalent(talentId));
        }

        return talents;
    }




}
