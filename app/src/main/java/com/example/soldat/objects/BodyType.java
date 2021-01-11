package com.example.soldat.objects;

import java.util.ArrayList;

public class BodyType {
    String name;
    int cost;
    String description;
    boolean primary;
    boolean multiple = false;
    ArrayList<String> subOptions;

    public BodyType(String name, int cost, String description, boolean primary) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.primary = primary;
    }

    public BodyType(String name, int cost, String description, ArrayList<String> subOptions, boolean primary) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.subOptions = subOptions;
        this.primary = primary;
    }

    public BodyType(String name, int cost, String description, boolean multiple, boolean primary) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.multiple = multiple;
        this.primary = primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getSubOptions() {
        return subOptions;
    }

    public void setSubOptions(ArrayList<String> subOptions) {
        this.subOptions = subOptions;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
