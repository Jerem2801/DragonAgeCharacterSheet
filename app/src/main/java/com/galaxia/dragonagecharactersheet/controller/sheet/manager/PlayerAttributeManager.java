package com.galaxia.dragonagecharactersheet.controller.sheet.manager;

import android.content.Context;

import com.galaxia.dragonagecharactersheet.controller.sheet.bean.PlayerAttributeBean;
import com.galaxia.dragonagecharactersheet.element.attribute.AttributeManager;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.focus.FocusManager;
import com.galaxia.dragonagecharactersheet.ressource.RessourceConstant;
import com.galaxia.dragonagecharactersheet.ressource.RessourceUtils;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class PlayerAttributeManager {

    private static final int ATTRIBUTE = 1;
    private static final int FOCUS = 2;

    private static final int ID = 0;
    private static final int VALUE = 1;


    private PlayerAttributeManager(){

    }

    public static Map<String,PlayerAttributeBean> getPlayerAttribute(Context context){
        Map<Integer,String> datas = RessourceUtils.getData(context, "character/lukadarkard_attribute.csv");

        Map<String,PlayerAttributeBean> beans = Maps.newHashMap();

        String data = datas.get(ATTRIBUTE);
        for(String line : StringUtils.split(data, RessourceConstant.SEPARATOR)){
            String[] split = StringUtils.split(line, RessourceConstant.AND);

            String id = split[ID];
            String value = split[VALUE];

            PlayerAttributeBean bean = new PlayerAttributeBean(id,value);
            beans.put(id,bean);
        }

        data = datas.get(FOCUS);
        for(String line : StringUtils.split(data, RessourceConstant.SEPARATOR)){
            Focus focus = FocusManager.getFocus(line);
            PlayerAttributeBean playerAttributeBean = beans.get(focus.getAttributeId());
            playerAttributeBean.addFocuss(line);

        }


        return beans;
    }

    public static void sort(List<PlayerAttributeBean> playerAttributes) {
        Collections.sort(playerAttributes, new Comparator<PlayerAttributeBean>() {
            @Override
            public int compare(PlayerAttributeBean o1, PlayerAttributeBean o2) {
                String name1 = AttributeManager.getAttribute(o1.getName()).getName();
                String name2 = AttributeManager.getAttribute(o2.getName()).getName();
                return name1.compareTo(name2);
            }
        });
    }

}
