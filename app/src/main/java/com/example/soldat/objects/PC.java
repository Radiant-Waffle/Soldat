package com.example.soldat.objects;

import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PC implements Serializable {

    private static int NEXT_ID = 1;
    private final int creationPoints = 25;
    private int remainingCreationPoints;
    private int experiencePoints;
    private int userId;
    private String characterName;
    private BodyType body;
    private ArrayList<Skill> SkillList;


    public PC(String userName, BodyType body, ArrayList<Skill> skillList) {
        this.characterName = userName;
        this.remainingCreationPoints = creationPoints;
        userId = NEXT_ID++;
        this.body = body;
        this.SkillList = skillList;
    }
    public PC(String userName) {
        this.characterName = userName;
        this.remainingCreationPoints = creationPoints;
        userId = NEXT_ID++;
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

    public ArrayList<Skill> getSkillList() {
        return SkillList;
    }

    public void addSkill(Skill newSkill) {
        SkillList.add(newSkill);
    }
}
