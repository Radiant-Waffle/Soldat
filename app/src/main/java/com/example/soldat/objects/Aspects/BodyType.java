package com.example.soldat.objects.Aspects;

import java.util.ArrayList;

public class BodyType extends Aspects {
    int cost;
    boolean primary;
    boolean multiple = false;
    ArrayList<String> subOptions;

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

    public BodyType(String name, int cost, String description, boolean multiple, boolean primary) {
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
