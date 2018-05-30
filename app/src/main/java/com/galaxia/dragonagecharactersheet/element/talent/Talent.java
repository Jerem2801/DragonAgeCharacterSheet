package com.galaxia.dragonagecharactersheet.element.talent;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Talent implements Parcelable {

    private String id;
    private String name;
    private List<String> classes;
    private String requirement;
    private String description;
    private String novice;
    private String journeyman;
    private String master;

    public Talent(String id, String name, List<String> classes, String requirement, String description, String novice, String journeyman, String master) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.requirement = requirement;
        this.description = description;
        this.novice = novice;
        this.journeyman = journeyman;
        this.master = master;
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

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
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

    public String getJourneyman() {
        return journeyman;
    }

    public void setJourneyman(String journeyman) {
        this.journeyman = journeyman;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
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
        dest.writeStringList(this.classes);
        dest.writeString(this.requirement);
        dest.writeString(this.description);
        dest.writeString(this.novice);
        dest.writeString(this.journeyman);
        dest.writeString(this.master);
    }

    protected Talent(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.classes = in.createStringArrayList();
        this.requirement = in.readString();
        this.description = in.readString();
        this.novice = in.readString();
        this.journeyman = in.readString();
        this.master = in.readString();
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
}
