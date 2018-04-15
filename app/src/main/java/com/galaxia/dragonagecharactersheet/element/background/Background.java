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
    private Attribute increaseAttribute;
    private String increaseAttributeDesc;
    private List<Focus> chooseFocus;
    private List<Language> spokenLanguage;
    private List<Language> writenLanguage;
    private List<BackgroundTable> bonusRoll;
    private String imagePath;
    private List<String> raceAvailable;
    private List<String> classeAvailable;

    public Background(String id, String name, String description, Attribute increaseAttribute, String increaseAttributeDesc, List<Focus> chooseFocus, List<Language> spokenLanguage, List<Language> writenLanguage, List<BackgroundTable> bonusRoll, String imagePath, List<String> raceAvailable, List<String> classeAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.increaseAttribute = increaseAttribute;
        this.increaseAttributeDesc = increaseAttributeDesc;
        this.chooseFocus = chooseFocus;
        this.spokenLanguage = spokenLanguage;
        this.writenLanguage = writenLanguage;
        this.bonusRoll = bonusRoll;
        this.imagePath = imagePath;
        this.raceAvailable = raceAvailable;
        this.classeAvailable = classeAvailable;
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

    public Attribute getIncreaseAttribute() {
        return increaseAttribute;
    }

    public void setIncreaseAttribute(Attribute increaseAttribute) {
        this.increaseAttribute = increaseAttribute;
    }

    public String getIncreaseAttributeDesc() {
        return increaseAttributeDesc;
    }

    public void setIncreaseAttributeDesc(String increaseAttributeDesc) {
        this.increaseAttributeDesc = increaseAttributeDesc;
    }

    public List<Focus> getChooseFocus() {
        return chooseFocus;
    }

    public void setChooseFocus(List<Focus> chooseFocus) {
        this.chooseFocus = chooseFocus;
    }

    public List<Language> getSpokenLanguage() {
        return spokenLanguage;
    }

    public void setSpokenLanguage(List<Language> spokenLanguage) {
        this.spokenLanguage = spokenLanguage;
    }

    public List<Language> getWritenLanguage() {
        return writenLanguage;
    }

    public void setWritenLanguage(List<Language> writenLanguage) {
        this.writenLanguage = writenLanguage;
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

    public List<String> getRaceAvailable() {
        return raceAvailable;
    }

    public void setRaceAvailable(List<String> raceAvailable) {
        this.raceAvailable = raceAvailable;
    }

    public List<String> getClasseAvailable() {
        return classeAvailable;
    }

    public void setClasseAvailable(List<String> classeAvailable) {
        this.classeAvailable = classeAvailable;
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
        dest.writeParcelable(this.increaseAttribute, flags);
        dest.writeString(this.increaseAttributeDesc);
        dest.writeTypedList(this.chooseFocus);
        dest.writeTypedList(this.spokenLanguage);
        dest.writeTypedList(this.writenLanguage);
        dest.writeTypedList(this.bonusRoll);
        dest.writeString(this.imagePath);
        dest.writeStringList(this.raceAvailable);
        dest.writeStringList(this.classeAvailable);
    }

    protected Background(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.increaseAttribute = in.readParcelable(Attribute.class.getClassLoader());
        this.increaseAttributeDesc = in.readString();
        this.chooseFocus = in.createTypedArrayList(Focus.CREATOR);
        this.spokenLanguage = in.createTypedArrayList(Language.CREATOR);
        this.writenLanguage = in.createTypedArrayList(Language.CREATOR);
        this.bonusRoll = in.createTypedArrayList(BackgroundTable.CREATOR);
        this.imagePath = in.readString();
        this.raceAvailable = in.createStringArrayList();
        this.classeAvailable = in.createStringArrayList();
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
        return  name;
    }
}
