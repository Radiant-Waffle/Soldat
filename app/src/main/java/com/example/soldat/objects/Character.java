package com.example.soldat.objects;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {

    private final int creationPoints = 25;
    private int remainingCreationPoints;
    private int experiencePoints;
    private int userId;
    private String characterName;
    private BodyType body;
    private List<Skill> SkillList;


    public Character(String userName) {
        this.characterName = userName;
        this.remainingCreationPoints = creationPoints;
    }

    public int getCreationPoints() {
        return creationPoints;
    }

    public int getRemainingCreationPoints() {
        return remainingCreationPoints;
    }

    public void changeRemainingCreationPoints(int remainingCreationPoints) {
        this.remainingCreationPoints += remainingCreationPoints;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public BodyType getBody() {
        return body;
    }

    public void setBody(BodyType body) {
        this.body = body;
    }
}
