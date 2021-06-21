package com.dshliazhko.android.test22;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class App extends Application {

    public static App instance;

    private DataBase database;
    private static  final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Contact ADD COLUMN pathImage STRING ");
        }
    };


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, DataBase.class, "database").addMigrations(App.MIGRATION_1_2).build();
    }

    public static App getInstance() {
        return instance;
    }

    public DataBase getDatabase() {
        return database;
    }
}
