package com.example.soldat.objects.Aspects;

import com.example.soldat.enums.modificationType;

import java.util.ArrayList;

public class Modification extends Aspects {
    modificationType type;
    ArrayList<Integer> cost;
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
}
