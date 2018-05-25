package com.galaxia.dragonagecharactersheet.element.race;

import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.classe.Classe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Race implements Parcelable,Comparator<Race> {

    private String id;
    private String name;
    private String description;
    private int initialSpeed;
    private List<String> classeAvailableIds;
    private String imagePath;

    public Race(String id, String name, String description, int initialSpeed, List<String> classeAvailableIds, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialSpeed = initialSpeed;
        this.classeAvailableIds = classeAvailableIds;
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

    public int getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public List<String> getClasseAvailableIds() {
        return classeAvailableIds;
    }

    public void setClasseAvailableIds(List<String> classeAvailableIds) {
        this.classeAvailableIds = classeAvailableIds;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
        dest.writeInt(this.initialSpeed);
        dest.writeStringList(this.classeAvailableIds);
        dest.writeString(this.imagePath);
    }

    protected Race(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.initialSpeed = in.readInt();
        this.classeAvailableIds = in.createStringArrayList();
        this.imagePath = in.readString();
    }

    public static final Parcelable.Creator<Race> CREATOR = new Parcelable.Creator<Race>() {
        @Override
        public Race createFromParcel(Parcel source) {
            return new Race(source);
        }

        @Override
        public Race[] newArray(int size) {
            return new Race[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compare(Race o1, Race o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
