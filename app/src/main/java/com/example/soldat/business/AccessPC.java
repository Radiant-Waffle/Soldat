package com.example.soldat.business;

import com.example.soldat.application.Services;
import com.example.soldat.objects.PlayerCharacter;
import com.example.soldat.persistence.DatabaseStub;

import java.util.ArrayList;

public class AccessPC {
    private DatabaseStub db;

    public AccessPC() { db = Services.getDatabase(); }
    public void getPCList(ArrayList<PlayerCharacter> playerCharacter) {
        db.getPCList(playerCharacter);
    }

    public void insertPC(PlayerCharacter currPlayerCharacter) {
        db.insertPC(currPlayerCharacter);
    }

    public void updatePC(PlayerCharacter currPlayerCharacter) {
        db.updatePC(currPlayerCharacter);
    }

    public void deletePC(PlayerCharacter currPlayerCharacter) {
        db.deletePC(currPlayerCharacter);
    }
}
