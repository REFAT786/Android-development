package com.example.contactapplication.contact;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ContactItem.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao ContactDao();

    public static ContactDatabase INSTANCE = null;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    ContactDatabase.class, "contact_database")
                    .fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
