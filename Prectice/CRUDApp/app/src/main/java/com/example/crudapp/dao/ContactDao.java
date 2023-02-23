package com.example.crudapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.crudapp.domain.ContactItems;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void Insert(ContactItems contactItems);

    @Update //(onConflict = OnConflictStrategy.REPLACE)
    void Update(ContactItems contactItems);

    @Delete
    void Delete(ContactItems contactItems);

    @Query("DELETE FROM contacts")
    void DeleteAllContacts();

    @Query("SELECT * FROM contacts")
    LiveData<List<ContactItems>> getAllContacts();  //updates and returns
}

