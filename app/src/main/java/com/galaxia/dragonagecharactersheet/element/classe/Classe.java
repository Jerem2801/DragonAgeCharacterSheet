package com.galaxia.dragonagecharactersheet.element.classe;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Classe implements Parcelable {

    private String id;
    private String name;
    private String description;
    private int initialHealth;
    private List<String> primaryAttributesId;
    private List<String> secondaryAttributesId;
    private List<String> weaponGroupStartingId;
    private String imagePath;
    private List<String> choiceTalents;
    private int numberOfTalent;
    private String stardTalent;

    public Classe(String id, String name, String description, int initialHealth, List<String> primaryAttributesId, List<String> secondaryAttributesId, List<String> weaponGroupStartingId, String imagePath, List<String> choiceTalents, int numberOfTalent, String stardTalent) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialHealth = initialHealth;
        this.primaryAttributesId = primaryAttributesId;
        this.secondaryAttributesId = secondaryAttributesId;
        this.weaponGroupStartingId = weaponGroupStartingId;
        this.imagePath = imagePath;
        this.choiceTalents = choiceTalents;
        this.numberOfTalent = numberOfTalent;
        this.stardTalent = stardTalent;
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

    public List<String> getPrimaryAttributesId() {
        return primaryAttributesId;
    }

    public void setPrimaryAttributesId(List<String> primaryAttributesId) {
        this.primaryAttributesId = primaryAttributesId;
    }

    public List<String> getSecondaryAttributesId() {
        return secondaryAttributesId;
    }

    public void setSecondaryAttributesId(List<String> secondaryAttributesId) {
        this.secondaryAttributesId = secondaryAttributesId;
    }

    public List<String> getWeaponGroupStartingId() {
        return weaponGroupStartingId;
    }

    public void setWeaponGroupStartingId(List<String> weaponGroupStartingId) {
        this.weaponGroupStartingId = weaponGroupStartingId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getChoiceTalents() {
        return choiceTalents;
    }

    public void setChoiceTalents(List<String> choiceTalents) {
        this.choiceTalents = choiceTalents;
    }

    public int getNumberOfTalent() {
        return numberOfTalent;
    }

    public void setNumberOfTalent(int numberOfTalent) {
        this.numberOfTalent = numberOfTalent;
    }

    public String getStardTalent() {
        return stardTalent;
    }

    public void setStardTalent(String stardTalent) {
        this.stardTalent = stardTalent;
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
        dest.writeInt(this.initialHealth);
        dest.writeStringList(this.primaryAttributesId);
        dest.writeStringList(this.secondaryAttributesId);
        dest.writeStringList(this.weaponGroupStartingId);
        dest.writeString(this.imagePath);
        dest.writeStringList(this.choiceTalents);
        dest.writeInt(this.numberOfTalent);
        dest.writeString(this.stardTalent);
    }

    protected Classe(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.initialHealth = in.readInt();
        this.primaryAttributesId = in.createStringArrayList();
        this.secondaryAttributesId = in.createStringArrayList();
        this.weaponGroupStartingId = in.createStringArrayList();
        this.imagePath = in.readString();
        this.choiceTalents = in.createStringArrayList();
        this.numberOfTalent = in.readInt();
        this.stardTalent = in.readString();
    }

    public static final Parcelable.Creator<Classe> CREATOR = new Parcelable.Creator<Classe>() {
        @Override
        public Classe createFromParcel(Parcel source) {
            return new Classe(source);
        }

        @Override
        public Classe[] newArray(int size) {
            return new Classe[size];
        }
    };
}
