package com.galaxia.dragonagecharactersheet.element.weapongroup;

import android.os.Parcel;
import android.os.Parcelable;

public class WeaponGroup implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String attributeIdForAttack;

    public WeaponGroup(String id, String name, String description, String attributeIdForAttack) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attributeIdForAttack = attributeIdForAttack;
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
    }

    protected WeaponGroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.attributeIdForAttack = in.readString();
    }

    public static final Parcelable.Creator<WeaponGroup> CREATOR = new Parcelable.Creator<WeaponGroup>() {
        @Override
        public WeaponGroup createFromParcel(Parcel source) {
            return new WeaponGroup(source);
        }

        @Override
        public WeaponGroup[] newArray(int size) {
            return new WeaponGroup[size];
        }
    };
}
