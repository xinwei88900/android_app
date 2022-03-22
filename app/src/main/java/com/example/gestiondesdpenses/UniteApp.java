package com.example.gestiondesdpenses;



/*
Global object
All methods that need to be applied to the entire app are called here
 */


import android.app.Application;

import com.example.gestiondesdpenses.database.DBManager;

public class UniteApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // there we can call the initialize methods for the global App
        DBManager.initDB(getApplicationContext());
    }
}
