package com.galaxia.dragonagecharactersheet.dice;

public class Dice {

    private int result;
    private String imagePath;

    public Dice(int result, String imagePath) {
        this.result = result;
        this.imagePath = imagePath;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
