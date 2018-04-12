package com.galaxia.dragonagecharactersheet.element.weapongroup;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;

public class WeaponGroup {

    private String id;
    private String name;
    private String description;
    private Attribute attributeForAttack;

    public WeaponGroup(String id, String name, String description,Attribute attributeForAttack) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attributeForAttack = attributeForAttack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Attribute getAttributeForAttack() {
        return attributeForAttack;
    }

    public void setAttributeForAttack(Attribute attributeForAttack) {
        this.attributeForAttack = attributeForAttack;
    }

    @Override
    public String toString() {
        return name;
    }
}
