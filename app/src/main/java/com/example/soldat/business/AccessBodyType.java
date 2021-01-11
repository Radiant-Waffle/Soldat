package com.example.soldat.business;

import android.provider.ContactsContract;

import com.example.soldat.application.Services;
import com.example.soldat.objects.BodyType;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessBodyType {
    private DatabaseStub db;

    public AccessBodyType() { db = Services.getDatabase(); }
    public ArrayList<BodyType> getBodyOptions() {
        return db.getBodyOptions();
    }

    public void insertBodyType(BodyType currType) {
        db.insertBodyType(currType);
    }

    public void updateBodyType(BodyType currType) {
        db.updateBodyType(currType);
    }

    public void deleteBodyType(BodyType currType) {
        db.deleteBodyType(currType);
    }
}
