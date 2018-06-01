package com.galaxia.dragonagecharactersheet.element.weapongroup;

import android.os.Parcel;
import android.os.Parcelable;

public class WeaponGroup implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String attributeIdForAttack;
    private String attributeIdForDamage;

    public WeaponGroup(String id, String name, String description, String attributeIdForAttack, String attributeIdForDamage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attributeIdForAttack = attributeIdForAttack;
        this.attributeIdForDamage = attributeIdForDamage;
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

    public String getAttributeIdForAttack() {
        return attributeIdForAttack;
    }

    public void setAttributeIdForAttack(String attributeIdForAttack) {
        this.attributeIdForAttack = attributeIdForAttack;
    }

    public String getAttributeIdForDamage() {
        return attributeIdForDamage;
    }

    public void setAttributeIdForDamage(String attributeIdForDamage) {
        this.attributeIdForDamage = attributeIdForDamage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.attributeIdForAttack);
        dest.writeString(this.attributeIdForDamage);
    }

    protected WeaponGroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.attributeIdForAttack = in.readString();
        this.attributeIdForDamage = in.readString();
    }

    public static final Creator<WeaponGroup> CREATOR = new Creator<WeaponGroup>() {
        @Override
        public WeaponGroup createFromParcel(Parcel source) {
            return new WeaponGroup(source);
        }

        @Override
        public WeaponGroup[] newArray(int size) {
            return new WeaponGroup[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
