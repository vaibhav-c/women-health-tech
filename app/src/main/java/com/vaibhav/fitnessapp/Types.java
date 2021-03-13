package com.vaibhav.fitnessapp;

public class Types {
    private String type;
    private String description;

    public Types() {
        type = null;
        description = null;
    }

    public Types(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Types{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
