package com.example.soldat.business;

import com.example.soldat.application.Services;
import com.example.soldat.objects.BodyType;
import com.example.soldat.objects.Skill;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessSkill {
    private DatabaseStub db;

    public AccessSkill() { db = Services.getDatabase(); }
    public ArrayList<Skill> getSkillOptions() {
        return db.getSkillOptions();
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
