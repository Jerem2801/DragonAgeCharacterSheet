package com.galaxia.dragonagecharactersheet.element.weapongroup;

import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonus;

public class WeaponGroup extends BackgroundBonus implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.attributeForAttack, flags);
    }

    protected WeaponGroup(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.attributeForAttack = in.readParcelable(Attribute.class.getClassLoader());
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
