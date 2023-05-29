package com.example.appdesign.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.appdesign.dao.UsersDao;
import com.example.appdesign.domain.Users;

@Database(entities = {Users.class}, version=1)
public abstract class UsersDatabase extends RoomDatabase {

    //not use
    public abstract UsersDao UsersDao();
    private static UsersDatabase instance;

    public static synchronized UsersDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            UsersDatabase.class, "users_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
