package com.galaxia.dragonagecharactersheet.element.classe;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;

import java.util.List;

public class Classe {

    private String id;
    private String name;
    private String description;
    private int initialHealth;
    private List<Attribute> primaryAttributes;
    private List<Attribute> secondaryAttributes;
    private List<WeaponGroup> weaponGroupStarting;
    private String imagePath;

    public Classe(String id, String name, String description, int initialHealth, List<Attribute> primaryAttributes, List<Attribute> secondaryAttributes, List<WeaponGroup> weaponGroupStarting, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialHealth = initialHealth;
        this.primaryAttributes = primaryAttributes;
        this.secondaryAttributes = secondaryAttributes;
        this.weaponGroupStarting = weaponGroupStarting;
        this.imagePath = imagePath;
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

    public int getInitialHealth() {
        return initialHealth;
    }

    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    public List<Attribute> getPrimaryAttributes() {
        return primaryAttributes;
    }

    public void setPrimaryAttributes(List<Attribute> primaryAttributes) {
        this.primaryAttributes = primaryAttributes;
    }

    public List<Attribute> getSecondaryAttributes() {
        return secondaryAttributes;
    }

    public void setSecondaryAttributes(List<Attribute> secondaryAttributes) {
        this.secondaryAttributes = secondaryAttributes;
    }

    public List<WeaponGroup> getWeaponGroupStarting() {
        return weaponGroupStarting;
    }

    public void setWeaponGroupStarting(List<WeaponGroup> weaponGroupStarting) {
        this.weaponGroupStarting = weaponGroupStarting;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return name;
    }
}
