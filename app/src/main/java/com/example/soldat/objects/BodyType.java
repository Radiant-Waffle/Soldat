package com.example.soldat.objects;

import java.util.List;

public class BodyType {
    String name;
    int cost;
    String description;
    List<BodyType> subOptions;

    public BodyType(String name, int cost, String description) {
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public BodyType(String name, int cost, String description, List<BodyType> subOptions) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.subOptions = subOptions;
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

    public List<BodyType> getSubOptions() {
        return subOptions;
    }

    public void setSubOptions(List<BodyType> subOptions) {
        this.subOptions = subOptions;
    }
}
