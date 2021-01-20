package com.example.soldat.objects;

import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.BodyType;
import com.example.soldat.objects.Aspects.Skill;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayerCharacter implements Serializable {

    private static int NEXT_ID = 1;
    private final int creationPoints = 25;
    private int remainingCreationPoints;
    private int experiencePoints;
    private int userId;
    private String characterName;
    private BodyType body;
    private ArrayList<Aspects> bodyAugments;
    private ArrayList<Aspects> skillList;


    public PlayerCharacter(String userName, BodyType body, ArrayList<Aspects> skillList) {
        this.characterName = userName;
        this.remainingCreationPoints = creationPoints;
        userId = NEXT_ID++;
        this.body = body;
        this.bodyAugments = new ArrayList<>();
        this.skillList = skillList;
    }
    public PlayerCharacter(String userName) {
        this.characterName = userName;
        this.remainingCreationPoints = creationPoints;
        this.bodyAugments = new ArrayList<>();
        this.skillList = new ArrayList<>();
        userId = NEXT_ID++;
    }
    public PlayerCharacter() {
        this.remainingCreationPoints = creationPoints;
        this.bodyAugments = new ArrayList<>();
        this.skillList = new ArrayList<>();
        userId = NEXT_ID++;
    }

    public int getCreationPoints() {
        return creationPoints;
    }

    public int getRemainingCreationPoints() {
        return remainingCreationPoints;
    }

    public void changeRemainingCreationPoints(int remainingCreationPoints) {
        this.remainingCreationPoints -= remainingCreationPoints;
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

    public ArrayList<Aspects> getSkillList() {
        return skillList;
    }

    public void addSkill(Skill newSkill) {
        skillList.add(newSkill);
    }

    public void removeSkill(Skill newSkill) {
        skillList.remove(newSkill);
    }

    public ArrayList<Aspects> getBodyAugments() {
        return bodyAugments;
    }

    public void addBodyAugments(BodyType bodyAugments) {
        this.bodyAugments.add(bodyAugments);
    }

    public void clearBodyAugments() { this.bodyAugments.clear(); }

    public void removeBodyAugments(BodyType bodyAugments) {
        this.bodyAugments.remove(bodyAugments);
    }
}
