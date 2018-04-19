package com.galaxia.dragonagecharactersheet.player;

import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.galaxia.dragonagecharactersheet.element.weapongroup.WeaponGroup;
import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Parcelable {

    private String raceId;
    private String classeId;
    private String backgroundId;
    private String focusIdChooseFromBackground;
    private List<String> focusIdsRollFromBackgroundTable;
    private List<String> attributeIdsRollFromBackgroundTable;
    private List<String> weaponGroupIds;
    private List<String> spokenLanguages;
    private Map<String,Integer> attributeIdsRoll;

    public Player(){
       this.focusIdsRollFromBackgroundTable = Lists.newArrayList();
       this.weaponGroupIds = Lists.newArrayList();
       this.spokenLanguages = Lists.newArrayList();
       this.attributeIdsRollFromBackgroundTable = Lists.newArrayList();
       this.attributeIdsRoll = PlayerManager.initializeAttribute();
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getClasseId() {
        return classeId;
    }

    public void setClasseId(String classeId) {
        this.classeId = classeId;
    }

    public String getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(String backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getFocusIdChooseFromBackground() {
        return focusIdChooseFromBackground;
    }

    public void setFocusIdChooseFromBackground(String focusIdChooseFromBackground) {
        this.focusIdChooseFromBackground = focusIdChooseFromBackground;
    }

    public List<String> getFocusIdsRollFromBackgroundTable() {
        return focusIdsRollFromBackgroundTable;
    }

    public void setFocusIdsRollFromBackgroundTable(List<String> focusIdsRollFromBackgroundTable) {
        this.focusIdsRollFromBackgroundTable = focusIdsRollFromBackgroundTable;
    }

    public List<String> getAttributeIdsRollFromBackgroundTable() {
        return attributeIdsRollFromBackgroundTable;
    }

    public void setAttributeIdsRollFromBackgroundTable(List<String> attributeIdsRollFromBackgroundTable) {
        this.attributeIdsRollFromBackgroundTable = attributeIdsRollFromBackgroundTable;
    }

    public List<String> getWeaponGroupIds() {
        return weaponGroupIds;
    }

    public void setWeaponGroupIds(List<String> weaponGroupIds) {
        this.weaponGroupIds = weaponGroupIds;
    }

    public List<String> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<String> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Map<String, Integer> getAttributeIdsRoll() {
        return attributeIdsRoll;
    }

    public void setAttributeIdsRoll(Map<String, Integer> attributeIdsRoll) {
        this.attributeIdsRoll = attributeIdsRoll;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.raceId);
        dest.writeString(this.classeId);
        dest.writeString(this.backgroundId);
        dest.writeString(this.focusIdChooseFromBackground);
        dest.writeStringList(this.focusIdsRollFromBackgroundTable);
        dest.writeStringList(this.attributeIdsRollFromBackgroundTable);
        dest.writeStringList(this.weaponGroupIds);
        dest.writeStringList(this.spokenLanguages);
        dest.writeInt(this.attributeIdsRoll.size());
        for (Map.Entry<String, Integer> entry : this.attributeIdsRoll.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
    }

    protected Player(Parcel in) {
        this.raceId = in.readString();
        this.classeId = in.readString();
        this.backgroundId = in.readString();
        this.focusIdChooseFromBackground = in.readString();
        this.focusIdsRollFromBackgroundTable = in.createStringArrayList();
        this.attributeIdsRollFromBackgroundTable = in.createStringArrayList();
        this.weaponGroupIds = in.createStringArrayList();
        this.spokenLanguages = in.createStringArrayList();
        int attributeIdsRollSize = in.readInt();
        this.attributeIdsRoll = new HashMap<String, Integer>(attributeIdsRollSize);
        for (int i = 0; i < attributeIdsRollSize; i++) {
            String key = in.readString();
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.attributeIdsRoll.put(key, value);
        }
    }

    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel source) {
            return new Player(source);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
}
