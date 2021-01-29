package com.example.soldat.objects.Aspects;

import java.util.ArrayList;

public class BodyType extends Aspects {
    int cost;
    boolean primary;
    int multiple = 1;
    int multipleSelected = 1;
    ArrayList<String> subOptions = new ArrayList<>();

    public BodyType(String name, int cost, String description) {
        super(name, description);
        this.cost = cost;
    }
    public BodyType(String name, int cost, String description, boolean primary) {
        super(name, description);
        this.cost = cost;
        this.primary = primary;
    }

    public BodyType(String name, int cost, String description, ArrayList<String> subOptions, boolean primary) {
        super(name, description);
        this.cost = cost;
        this.subOptions = subOptions;
        this.primary = primary;
    }

    public BodyType(String name, int cost, String description, int multiple, boolean primary) {
        super(name, description);
        this.cost = cost;
        this.multiple = multiple;
        this.primary = primary;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<String> getSubOptions() {
        return subOptions;
    }

    public void setSubOptions(ArrayList<String> subOptions) {
        this.subOptions = subOptions;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }

    public int getMultipleSelected() {
        return multipleSelected;
    }

    public void setMultipleSelected(int multipleSelected) {
        this.multipleSelected = multipleSelected;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
