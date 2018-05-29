package com.galaxia.dragonagecharactersheet.controller.sheet.bean;

import com.google.common.collect.Lists;

import java.util.List;

public class PlayerAttributeBean {

    private String name;
    private String value;
    private List<String> focuss;

    public PlayerAttributeBean(String name, String value) {
        this.name = name;
        this.value = value;
        this.focuss = Lists.newArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<String> getFocuss() {
        return focuss;
    }

    public void addFocuss(String focus) {
        this.focuss.add(focus);
    }


}
