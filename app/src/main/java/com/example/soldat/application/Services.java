package com.example.soldat.application;

import com.example.soldat.persistence.DatabaseStub;

public class Services {
    private static DatabaseStub soldatDatabase = null;

    public static DatabaseStub createDatabase(String dbName) {
        if(soldatDatabase ==null) {
            soldatDatabase = new DatabaseStub(dbName);
            soldatDatabase.openDatabase();
        }

        return soldatDatabase;
    }

    public static DatabaseStub getDatabase()        {
        if (soldatDatabase == null)      {
            System.out.println("No connection to database");
            System.exit(1);
        }

        return soldatDatabase;
    }

    public static void closeDatabase()      {
        if (soldatDatabase != null)      {
            soldatDatabase.closeDatabase();
        }

        soldatDatabase = null;
    }
}
