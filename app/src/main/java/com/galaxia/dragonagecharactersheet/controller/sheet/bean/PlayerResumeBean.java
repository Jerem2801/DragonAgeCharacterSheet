package com.galaxia.dragonagecharactersheet.controller.sheet.bean;

public class PlayerResumeBean {

    private String name;
    private String race;
    private String classe;
    private String background;
    private String level;

    public PlayerResumeBean(String name, String race, String classe, String background, String level) {
        this.name = name;
        this.race = race;
        this.classe = classe;
        this.background = background;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
