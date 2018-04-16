package com.galaxia.dragonagecharactersheet.element.focus;

import android.text.TextUtils;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;

import java.util.List;

public class FocusUiManager {

    private FocusUiManager(){
        
    }

    public static String getChooseFocus(List<Focus> focuss) {
        StringBuilder builder = new StringBuilder();
        for(Focus focus : focuss){
            String focusName = focus.getName();
            String attributeId = focus.getAttributeId();
            Attribute attribute = AttributeManager.getAttribute(attributeId);
            if(TextUtils.isEmpty(builder.toString())){
                builder.append(attribute.getName() + "(" + focusName + ")");
            }else{
                builder.append(" ou " + attribute.getName() + "(" + focusName + ")");
            }


        }
        return builder.toString();
    }

    public static String getFocusWithAttribute(Focus focus) {
        String attributeId = focus.getAttributeId();
        Attribute attribute = AttributeManager.getAttribute(attributeId);
        return attribute.getName() + "(" + focus.getName() + ")";
    }
}
