package com.galaxia.dragonagecharactersheet.element.attribute;

public enum AttributeEnum {

    CONSTITUTION("constitution"),
    COMMUNICATION("communication"),
    CUNNING("cunning"),
    DEXTERITY("dexterity"),
    MAGIC("magic"),
    PERCEPTION("perception"),
    STRENGTH("strength"),
    WILLPOWER("willpower");

    private String id;

    private AttributeEnum(String id){
        this.id = id;
    }

    public  String getId(){
        return this.id;
    }



}
