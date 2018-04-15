package com.galaxia.dragonagecharactersheet.element.focus;


import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus.BackgroundBonus;

public class Focus extends BackgroundBonus implements Parcelable {

    private String id;
    private String name;
    private String description;
    private Attribute attribute;

    public Focus(String id, String name, String description, Attribute attribute) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attribute = attribute;
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

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
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
        dest.writeParcelable(this.attribute, flags);
    }

    protected Focus(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.attribute = in.readParcelable(Attribute.class.getClassLoader());
    }

    public static final Parcelable.Creator<Focus> CREATOR = new Parcelable.Creator<Focus>() {
        @Override
        public Focus createFromParcel(Parcel source) {
            return new Focus(source);
        }

        @Override
        public Focus[] newArray(int size) {
            return new Focus[size];
        }
    };
}
