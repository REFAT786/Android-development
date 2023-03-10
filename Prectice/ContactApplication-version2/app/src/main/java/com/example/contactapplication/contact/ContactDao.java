package com.example.contactapplication.contact;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Insert
    void insert(ContactItem contactItem);
    @Update
        //(onConflict = OnConflictStrategy.REPLACE)
    void Update(ContactItem contactItem);

    @Delete
    void delete(ContactItem contactItem);

    @Query("SELECT * FROM contacts")
    LiveData<List<ContactItem>> getAllContacts();
}
