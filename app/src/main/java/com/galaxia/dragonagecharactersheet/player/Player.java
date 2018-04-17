package com.galaxia.dragonagecharactersheet.player;

import android.os.Parcel;
import android.os.Parcelable;

import com.galaxia.dragonagecharactersheet.element.classe.Classe;
import com.galaxia.dragonagecharactersheet.element.race.Race;
import com.google.common.collect.Lists;

import java.util.List;

public class Player implements Parcelable {

    private String raceId;
    private String classeId;
    private String backgroundId;
    private List<String> focusIds;

    public Player(){
       this.focusIds = Lists.newArrayList();
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

    public void addFocus(String focusId) {
        this.focusIds.add(focusId);
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
    }

    protected Player(Parcel in) {
        this.raceId = in.readString();
        this.classeId = in.readString();
        this.backgroundId = in.readString();
        this.focusIds = in.createStringArrayList();
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
