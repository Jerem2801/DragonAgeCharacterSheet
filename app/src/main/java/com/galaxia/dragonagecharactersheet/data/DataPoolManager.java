package com.galaxia.dragonagecharactersheet.data;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;

import java.util.Map;

public class DataPoolManager {

    private DataPoolManager(){

    }

    public static DataPool getDataLoader(Context context){
        Map<String,Attribute> attributes = AttributeManager.getAttribute(context);

        DataPool dataPool = new DataPool();
        dataPool.setAttributes(attributes);

        return dataPool;
    }
}
