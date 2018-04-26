package com.galaxia.dragonagecharactersheet.element.talent;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Talent implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String novice;
    private String typeChoice;
    private List<String> choice;

    public Talent(String id, String name, String description, String novice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.novice = novice;
    }

    public Talent(String id, String name, String description, String novice, String typeChoice, List<String> choice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.novice = novice;
        this.typeChoice = typeChoice;
        this.choice = choice;
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

    public String getNovice() {
        return novice;
    }

    public void setNovice(String novice) {
        this.novice = novice;
    }

    public String getTypeChoice() {
        return typeChoice;
    }

    public void setTypeChoice(String typeChoice) {
        this.typeChoice = typeChoice;
    }

    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
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
        dest.writeString(this.novice);
        dest.writeString(this.typeChoice);
        dest.writeStringList(this.choice);
    }

    protected Talent(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.novice = in.readString();
        this.typeChoice = in.readString();
        this.choice = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Talent> CREATOR = new Parcelable.Creator<Talent>() {
        @Override
        public Talent createFromParcel(Parcel source) {
            return new Talent(source);
        }

        @Override
        public Talent[] newArray(int size) {
            return new Talent[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
