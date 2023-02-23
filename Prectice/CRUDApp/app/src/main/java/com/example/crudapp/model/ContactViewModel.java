package com.example.crudapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.crudapp.domain.ContactItems;
import com.example.crudapp.repository.ConatctRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private ConatctRepository repository ;
    private LiveData<List<ContactItems>> allContacts;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ConatctRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(ContactItems contactItems) {
        repository.insert(contactItems);
    }
    public void update(ContactItems contactItems) {
        repository.update(contactItems);
    }
    public void delete(ContactItems contactItems) {
        repository.delete(contactItems);
    }
    public void deleteAllContacts() {
        repository.deleteAllContacts();
    }
    public LiveData<List<ContactItems>> getAllContacts() {
        return allContacts;
    }

}
