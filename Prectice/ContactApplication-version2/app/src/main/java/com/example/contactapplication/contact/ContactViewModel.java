package com.example.contactapplication.contact;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private LiveData<List<ContactItem>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(ContactItem contactItem) {
        repository.insert(contactItem);
    }

    public void delete(ContactItem contactItem) {
        repository.delete(contactItem);
    }
    public void update(ContactItem contactItem) {
        repository.update(contactItem);
    }
    public LiveData<List<ContactItem>> getAllContacts() {
        return allContacts;
    }
}
