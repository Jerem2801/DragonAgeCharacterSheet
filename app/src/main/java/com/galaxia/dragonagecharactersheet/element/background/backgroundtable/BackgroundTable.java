package com.galaxia.dragonagecharactersheet.element.background.backgroundtable;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class BackgroundTable implements Comparable, Parcelable {

    private int order;
    private List<Integer> roll;
    private String type;
    private String bonus;

    public BackgroundTable(int order, List<Integer> roll, String type, String bonus) {
        this.order = order;
        this.roll = roll;
        this.type = type;
        this.bonus = bonus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<Integer> getRoll() {
        return roll;
    }

    public void setRoll(List<Integer> roll) {
        this.roll = roll;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        int orderObject = ((BackgroundTable) o).getOrder();
        return this.order - orderObject;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.order);
        dest.writeList(this.roll);
        dest.writeString(this.type);
        dest.writeString(this.bonus);
    }

    protected BackgroundTable(Parcel in) {
        this.order = in.readInt();
        this.roll = new ArrayList<Integer>();
        in.readList(this.roll, Integer.class.getClassLoader());
        this.type = in.readString();
        this.bonus = in.readString();
    }

    public static final Parcelable.Creator<BackgroundTable> CREATOR = new Parcelable.Creator<BackgroundTable>() {
        @Override
        public BackgroundTable createFromParcel(Parcel source) {
            return new BackgroundTable(source);
        }

        @Override
        public BackgroundTable[] newArray(int size) {
            return new BackgroundTable[size];
        }
    };
}
