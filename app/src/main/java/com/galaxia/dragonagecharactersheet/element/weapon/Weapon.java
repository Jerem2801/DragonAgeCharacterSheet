package com.galaxia.dragonagecharactersheet.element.weapon;

import android.os.Parcel;
import android.os.Parcelable;

public class Weapon implements Parcelable {

    private String id;
    private String name;
    private String description;
    private String type;
    private String weaponGroupId;
    private int numberDiceDamage;
    private int diceDamage;
    private int bonusDamage;
    private String minimunStrenght;
    private String cost;
    private int shortRange;
    private int longRange;
    private String reloadAction;

    public Weapon(String id, String name, String description, String type, String weaponGroupId, int numberDiceDamage, int diceDamage, int bonusDamage, String minimunStrenght, String cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.weaponGroupId = weaponGroupId;
        this.numberDiceDamage = numberDiceDamage;
        this.diceDamage = diceDamage;
        this.bonusDamage = bonusDamage;
        this.minimunStrenght = minimunStrenght;
        this.cost = cost;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeaponGroupId() {
        return weaponGroupId;
    }

    public void setWeaponGroupId(String weaponGroupId) {
        this.weaponGroupId = weaponGroupId;
    }

    public int getNumberDiceDamage() {
        return numberDiceDamage;
    }

    public void setNumberDiceDamage(int numberDiceDamage) {
        this.numberDiceDamage = numberDiceDamage;
    }

    public int getDiceDamage() {
        return diceDamage;
    }

    public void setDiceDamage(int diceDamage) {
        this.diceDamage = diceDamage;
    }

    public int getBonusDamage() {
        return bonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    public String getMinimunStrenght() {
        return minimunStrenght;
    }

    public void setMinimunStrenght(String minimunStrenght) {
        this.minimunStrenght = minimunStrenght;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getShortRange() {
        return shortRange;
    }

    public void setShortRange(int shortRange) {
        this.shortRange = shortRange;
    }

    public int getLongRange() {
        return longRange;
    }

    public void setLongRange(int longRange) {
        this.longRange = longRange;
    }

    public String getReloadAction() {
        return reloadAction;
    }

    public void setReloadAction(String reloadAction) {
        this.reloadAction = reloadAction;
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
        dest.writeString(this.type);
        dest.writeString(this.weaponGroupId);
        dest.writeInt(this.numberDiceDamage);
        dest.writeInt(this.diceDamage);
        dest.writeInt(this.bonusDamage);
        dest.writeString(this.minimunStrenght);
        dest.writeString(this.cost);
        dest.writeInt(this.shortRange);
        dest.writeInt(this.longRange);
        dest.writeString(this.reloadAction);
    }

    protected Weapon(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.type = in.readString();
        this.weaponGroupId = in.readString();
        this.numberDiceDamage = in.readInt();
        this.diceDamage = in.readInt();
        this.bonusDamage = in.readInt();
        this.minimunStrenght = in.readString();
        this.cost = in.readString();
        this.shortRange = in.readInt();
        this.longRange = in.readInt();
        this.reloadAction = in.readString();
    }

    public static final Parcelable.Creator<Weapon> CREATOR = new Parcelable.Creator<Weapon>() {
        @Override
        public Weapon createFromParcel(Parcel source) {
            return new Weapon(source);
        }

        @Override
        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
