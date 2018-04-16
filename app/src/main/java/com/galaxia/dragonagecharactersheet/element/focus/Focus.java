package com.galaxia.dragonagecharactersheet.element.focus;


import android.os.Parcel;
import android.os.Parcelable;

public class Focus implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String attributeId;

    public Focus(String id, String name, String description, String attributeId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attributeId = attributeId;
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

    public String getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(String attributeId) {
        this.attributeId = attributeId;
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
        dest.writeString(this.attributeId);
    }

    protected Focus(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.attributeId = in.readString();
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
