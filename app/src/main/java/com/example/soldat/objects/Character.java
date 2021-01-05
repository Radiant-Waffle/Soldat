package com.example.soldat.objects;

import java.io.Serializable;
import java.util.List;

public class Character implements Serializable {

    private final int CreationPoints = 25;
    private int RemainingCreationPoints;
    private int userId;
    private String characterName;
    private BodyType body;
    private List<Skill> SkillList;


    public Character(String userName) {
        this.characterName = userName;
        this.RemainingCreationPoints = CreationPoints;
    }

    public int getCreationPoints() {
        return CreationPoints;
    }

    public int getRemainingCreationPoints() {
        return RemainingCreationPoints;
    }

    public void changeRemainingCreationPoints(int remainingCreationPoints) {
        RemainingCreationPoints += remainingCreationPoints;
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
