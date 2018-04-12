package com.galaxia.dragonagecharactersheet.element.race;

import com.galaxia.dragonagecharactersheet.element.classe.Classe;

import java.util.List;

public class Race {

    private String id;
    private String name;
    private String description;
    private int initialSpeed;
    private List<Classe> classeAvailable;
    private String imagePath;

    public Race(String id, String name, String description, int initialSpeed, List<Classe> classeAvailable, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.initialSpeed = initialSpeed;
        this.classeAvailable = classeAvailable;
        this.imagePath = imagePath;
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

    public int getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(int initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public List<Classe> getClasseAvailable() {
        return classeAvailable;
    }

    public void setClasseAvailable(List<Classe> classeAvailable) {
        this.classeAvailable = classeAvailable;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return name;
    }
}
