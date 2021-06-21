package com.dshliazhko.android.test22;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class},version = 2)
public abstract class DataBase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
