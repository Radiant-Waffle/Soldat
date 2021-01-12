package com.example.soldat.business;

import com.example.soldat.application.Services;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.Skill;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessSkill {
    private DatabaseStub db;

    public AccessSkill() { db = Services.getDatabase(); }
    public void getSkillOptions(ArrayList<Aspects> skills) {
        db.getSkillOptions(skills);
    }

    public void insertSkill(Skill currSkill) {
        db.insertSkill(currSkill);
    }

    public void updateSkill(Skill currSkill) {
        db.updateSkill(currSkill);
    }

    public void deleteSkill(Skill currSkill) {
        db.deleteSkill(currSkill);
    }
}
