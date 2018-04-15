package com.galaxia.dragonagecharactersheet.element.attribute;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.data.DataPool;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourcePath;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttributeManager {

    private static final String ATTRIBUTE_DIR = RessourcePath.DATA_PATH + "attribute/";
    private static final String ATTRIBUTE_CSV_PATH = ATTRIBUTE_DIR + "attribute.csv";

    private static final int ID = 0;
    private static final int NAME = 1;
    private static final int DESCRIPTION = 2;

    private AttributeManager(){

    }

    public static Map<String,Attribute> getAttributeData(Context context){
        Map<String,Attribute> attributes = new HashMap<>();

        List<String> data = RessourceUtils.getData(context,ATTRIBUTE_CSV_PATH,true);

        for(String line : data){
            String[] splitData = line.split(RessourceConstant.SEPARATOR);
            String id = splitData[ID];
            String name = splitData[NAME];
            String description = splitData[DESCRIPTION];

            Attribute attribute = new Attribute(id,name,description);
            attributes.put(id,attribute);
        }

        return attributes;
    }

    public static Attribute getAttributeData(AttributeEnum attribute){
        DataPool instance = DataPool.getInstance();
        Map<String, Attribute> attributes = instance.getAttributes();
        return attributes.get(attribute.getId());
    }


}
