package com.galaxia.dragonagecharactersheet.element.background;

import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.attribute.Attribute;
import com.galaxia.dragonagecharactersheet.element.background.backgroundtable.BackgroundTable;
import com.galaxia.dragonagecharactersheet.element.focus.Focus;
import com.galaxia.dragonagecharactersheet.element.language.Language;

import java.util.List;

public class Background implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String increaseAttributeId;
    private String increaseAttributeDesc;
    private List<String> chooseFocusId;
    private List<String> spokenLanguageId;
    private List<String> writenLanguageId;
    private List<BackgroundTable> bonusRoll;
    private String imagePath;
    private List<String> raceAvailableId;
    private List<String> classeAvailableId;

    public Background(String id, String name, String description, String increaseAttributeId, String increaseAttributeDesc, List<String> chooseFocusId, List<String> spokenLanguageId, List<String> writenLanguageId, List<BackgroundTable> bonusRoll, String imagePath, List<String> raceAvailableId, List<String> classeAvailableId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.increaseAttributeId = increaseAttributeId;
        this.increaseAttributeDesc = increaseAttributeDesc;
        this.chooseFocusId = chooseFocusId;
        this.spokenLanguageId = spokenLanguageId;
        this.writenLanguageId = writenLanguageId;
        this.bonusRoll = bonusRoll;
        this.imagePath = imagePath;
        this.raceAvailableId = raceAvailableId;
        this.classeAvailableId = classeAvailableId;
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

    public String getIncreaseAttributeId() {
        return increaseAttributeId;
    }

    public void setIncreaseAttributeId(String increaseAttributeId) {
        this.increaseAttributeId = increaseAttributeId;
    }

    public String getIncreaseAttributeDesc() {
        return increaseAttributeDesc;
    }

    public void setIncreaseAttributeDesc(String increaseAttributeDesc) {
        this.increaseAttributeDesc = increaseAttributeDesc;
    }

    public List<String> getChooseFocusId() {
        return chooseFocusId;
    }

    public void setChooseFocusId(List<String> chooseFocusId) {
        this.chooseFocusId = chooseFocusId;
    }

    public List<String> getSpokenLanguageId() {
        return spokenLanguageId;
    }

    public void setSpokenLanguageId(List<String> spokenLanguageId) {
        this.spokenLanguageId = spokenLanguageId;
    }

    public List<String> getWritenLanguageId() {
        return writenLanguageId;
    }

    public void setWritenLanguageId(List<String> writenLanguageId) {
        this.writenLanguageId = writenLanguageId;
    }

    public List<BackgroundTable> getBonusRoll() {
        return bonusRoll;
    }

    public void setBonusRoll(List<BackgroundTable> bonusRoll) {
        this.bonusRoll = bonusRoll;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<String> getRaceAvailableId() {
        return raceAvailableId;
    }

    public void setRaceAvailableId(List<String> raceAvailableId) {
        this.raceAvailableId = raceAvailableId;
    }

    public List<String> getClasseAvailableId() {
        return classeAvailableId;
    }

    public void setClasseAvailableId(List<String> classeAvailableId) {
        this.classeAvailableId = classeAvailableId;
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
        dest.writeString(this.increaseAttributeId);
        dest.writeString(this.increaseAttributeDesc);
        dest.writeStringList(this.chooseFocusId);
        dest.writeStringList(this.spokenLanguageId);
        dest.writeStringList(this.writenLanguageId);
        dest.writeTypedList(this.bonusRoll);
        dest.writeString(this.imagePath);
        dest.writeStringList(this.raceAvailableId);
        dest.writeStringList(this.classeAvailableId);
    }

    protected Background(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.increaseAttributeId = in.readString();
        this.increaseAttributeDesc = in.readString();
        this.chooseFocusId = in.createStringArrayList();
        this.spokenLanguageId = in.createStringArrayList();
        this.writenLanguageId = in.createStringArrayList();
        this.bonusRoll = in.createTypedArrayList(BackgroundTable.CREATOR);
        this.imagePath = in.readString();
        this.raceAvailableId = in.createStringArrayList();
        this.classeAvailableId = in.createStringArrayList();
    }

    public static final Parcelable.Creator<Background> CREATOR = new Parcelable.Creator<Background>() {
        @Override
        public Background createFromParcel(Parcel source) {
            return new Background(source);
        }

        @Override
        public Background[] newArray(int size) {
            return new Background[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
