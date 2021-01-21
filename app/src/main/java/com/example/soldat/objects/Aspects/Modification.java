package com.example.soldat.objects.Aspects;

import com.example.soldat.enums.modificationType;

import java.util.ArrayList;

public class Modification extends Aspects {
    modificationType type;
    ArrayList<Integer> cost;
    ArrayList<String> typeRestrictions;
    String opposite;
    public Modification(String name, ArrayList<Integer> cost, String description, modificationType type, ArrayList<String> typeRestrictions, String opposite) {
        super(name, description);
        this.type = type;
        this.cost = cost;
        this.typeRestrictions = typeRestrictions;
        this.opposite = opposite;
    }
    public Modification(String name, ArrayList<Integer> cost, String description, modificationType type, String opposite) {
        super(name, description);
        this.type = type;
        this.cost = cost;
        this.opposite = opposite;
    }
    public Modification(String name, ArrayList<Integer> cost, String description, modificationType type, ArrayList<String> typeRestrictions) {
        super(name, description);
        this.cost = cost;
        this.type = type;
        this.typeRestrictions = typeRestrictions;
    }
    public Modification(String name, ArrayList<Integer> cost, String description, modificationType type) {
        super(name, description);
        this.cost = cost;
        this.type = type;
    }
    public Modification(String name, ArrayList<Integer> cost, String description) {
        super(name, description);
        this.cost = cost;
    }

    public ArrayList<Integer> getCost() {
        return cost;
    }

    public void setCost(ArrayList<Integer> cost) {
        this.cost = cost;
    }

    public modificationType getType() {
        return type;
    }

    public void setType(modificationType type) {
        this.type = type;
    }

    public ArrayList<String> getTypeRestrictions() {
        return typeRestrictions;
    }

    public void setTypeRestrictions(ArrayList<String> typeRestrictions) {
        this.typeRestrictions = typeRestrictions;
    }

    public void addTypeRestrictions(String typeRestriction) {
        this.typeRestrictions.add(typeRestriction);
    }
    public void removeTypeRestrictions(String typeRestriction) {
        this.typeRestrictions.remove(typeRestriction);
    }

    public String getOpposite() {
        return opposite;
    }

    public void setOpposite(String opposite) {
        this.opposite = opposite;
    }
}
