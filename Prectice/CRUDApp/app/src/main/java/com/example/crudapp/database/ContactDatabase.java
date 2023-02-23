package com.example.crudapp.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.crudapp.dao.ContactDao;
import com.example.crudapp.domain.ContactItems;

@Database(entities = {ContactItems.class},version = 2) //add as array if multiple
public abstract class ContactDatabase extends RoomDatabase {

    public abstract ContactDao ContactDao();
    private static ContactDatabase instance; //only one interface

    public static synchronized ContactDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class , "contacts_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // new PopulateDb(instance).execute();
        }
    };
}
