package com.galaxia.dragonagecharactersheet.data;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;

import java.util.Map;

public class DataPool {

    private static DataPool dataPool;

    private Map<String,Attribute> attributes;

    public Map<String, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Attribute> attributes) {
        this.attributes = attributes;
    }

    public synchronized static DataPool getInstance(Context context){
        if(dataPool == null){
            dataPool = DataPoolManager.getDataLoader(context);
        }
        return dataPool;
    }

}
