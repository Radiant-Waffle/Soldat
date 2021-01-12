package com.example.soldat.objects.Aspects;

public class Skill extends Aspects {
    int cost;

    public Skill(String name, int cost, String description) {
        super(name, description);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
