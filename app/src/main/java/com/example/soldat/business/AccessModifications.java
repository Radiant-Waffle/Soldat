package com.example.soldat.business;

import com.example.soldat.application.Services;
import com.example.soldat.enums.modificationType;
import com.example.soldat.objects.Aspects.Aspects;
import com.example.soldat.objects.Aspects.Modification;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessModifications {
    private DatabaseStub db;

    public AccessModifications() { db = Services.getDatabase(); }
    public void getModOptions(ArrayList<Aspects> mods) {
        db.getModOptions(mods);
    }
    public void getModOptions(ArrayList<Aspects> mods, modificationType type) {
        ArrayList<Aspects> retrieve = new ArrayList<>();
        db.getModOptions(retrieve);
        mods.clear();
        for(Aspects m : retrieve) {
            if(((Modification)m).getType() == type) {
                mods.add(m);
            }
        }
    }

    public void insertMod(Modification currMod) {
        db.insertMod(currMod);
    }

    public void updateMod(Modification currMod) {
        db.updateMod(currMod);
    }

    public void deleteMod(Modification currMod) {
        db.deleteMod(currMod);
    }
}
