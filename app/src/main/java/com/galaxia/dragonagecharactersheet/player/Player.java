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
    private List<String> focusIds;
    private Map<String,Integer> attribute;
    private List<String> weaponGroupIds;
    private List<String> spokenLanguages;

    public Player(){
       this.focusIds = Lists.newArrayList();
       this.weaponGroupIds = Lists.newArrayList();
       this.spokenLanguages = Lists.newArrayList();
       this.attribute = PlayerManager.initializeAttribute();
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

    public List<String> getFocusIds() {
        return focusIds;
    }

    public void setFocusIds(List<String> focusIds) {
        this.focusIds = focusIds;
    }


    public Map<String, Integer> getAttribute() {
        return attribute;
    }

    public void setAttribute(Map<String, Integer> attribute) {
        this.attribute = attribute;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.raceId);
        dest.writeString(this.classeId);
        dest.writeString(this.backgroundId);
        dest.writeStringList(this.focusIds);
        dest.writeInt(this.attribute.size());
        for (Map.Entry<String, Integer> entry : this.attribute.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeValue(entry.getValue());
        }
        dest.writeStringList(this.weaponGroupIds);
        dest.writeStringList(this.spokenLanguages);
    }

    protected Player(Parcel in) {
        this.raceId = in.readString();
        this.classeId = in.readString();
        this.backgroundId = in.readString();
        this.focusIds = in.createStringArrayList();
        int attributeSize = in.readInt();
        this.attribute = new HashMap<String, Integer>(attributeSize);
        for (int i = 0; i < attributeSize; i++) {
            String key = in.readString();
            Integer value = (Integer) in.readValue(Integer.class.getClassLoader());
            this.attribute.put(key, value);
        }
        this.weaponGroupIds = in.createStringArrayList();
        this.spokenLanguages = in.createStringArrayList();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
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
