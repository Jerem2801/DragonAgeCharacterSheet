package com.galaxia.dragonagecharactersheet.element.talent;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
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
    private static final int DESCRIPTION = 2;
    private static final int NOVICE = 3;
    private static final int TYPE_CHOICE = 4;
    private static final int CHOICES = 5;

    private TalentManager(){

    }

    public static Map<String,Talent> getTalentData(Context context){
        Map<String,Talent> talents = Maps.newHashMap();

        List<String> data = RessourceUtils.getData(context,TALENT_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];
            String novice = splitData[NOVICE];

            Talent talent;

            if(splitData.length > 4){
                String type_choice= splitData[TYPE_CHOICE];
                String choicesOneString = splitData[CHOICES];
                List<String> choices = Arrays.asList(StringUtils.split(choicesOneString, RessourceConstant.AND));
                talent = new Talent(id,name,description,novice,type_choice,choices);
            }else{
                talent = new Talent(id,name,description,novice);
            }

            talents.put(id,talent);
        }

        return talents;
    }

    public static Talent getTalent(String talentId){
        DataPool dataPool = DataPool.getInstance();
        Map<String, Talent> talents = dataPool.getTalents();
        return talents.get(talentId);
    }




}
