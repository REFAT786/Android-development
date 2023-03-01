package com.example.appdesign.dao;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appdesign.domain.Users;

import java.util.List;

@Dao
public interface UsersDao {

    @Insert
    void Insert(Users users);

    @Update
    void Update(Users users);

    @Delete
    void Delete(Users users);

    @Query("SELECT * FROM users")
    LiveData<List<Users>> getAllContacts();

    @Query("DELETE FROM users")
    void DeleteAllUsers();

    @Query("SELECT * FROM users WHERE username = :uName")
    LiveData<Users> findByUsername(String uName);

    @Query("SELECT * FROM users WHERE id=:id")
    LiveData<Users> findByUserId(int id);

    @Query("SELECT * FROM users WHERE username = :uName AND password = :password")
    boolean checkUsers(String uName, String password);
}
