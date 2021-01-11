package com.example.soldat.business;

import com.example.soldat.application.Services;
import com.example.soldat.objects.PC;
import com.example.soldat.objects.Skill;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessPC {
    private DatabaseStub db;

    public AccessPC() { db = Services.getDatabase(); }
    public ArrayList<PC> getPCList() {
        return db.getPCList();
    }

    public void insertPC(PC currPC) {
        db.insertPC(currPC);
    }

    public void updatePC(PC currPC) {
        db.updatePC(currPC);
    }

    public void deletePC(PC currPC) {
        db.deletePC(currPC);
    }
}
