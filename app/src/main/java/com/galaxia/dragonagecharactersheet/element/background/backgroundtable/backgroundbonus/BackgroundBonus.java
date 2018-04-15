package com.galaxia.dragonagecharactersheet.element.background.backgroundtable.backgroundbonus;

import android.os.Parcel;
import android.os.Parcelable;

public class BackgroundBonus implements Parcelable {



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public BackgroundBonus() {
    }

    protected BackgroundBonus(Parcel in) {
    }

    public static final Parcelable.Creator<BackgroundBonus> CREATOR = new Parcelable.Creator<BackgroundBonus>() {
        @Override
        public BackgroundBonus createFromParcel(Parcel source) {
            return new BackgroundBonus(source);
        }

        @Override
        public BackgroundBonus[] newArray(int size) {
            return new BackgroundBonus[size];
        }
    };
}
