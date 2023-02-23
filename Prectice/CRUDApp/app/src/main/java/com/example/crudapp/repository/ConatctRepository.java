package com.example.crudapp.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.crudapp.dao.ContactDao;
import com.example.crudapp.database.ContactDatabase;
import com.example.crudapp.domain.ContactItems;

import java.util.List;

public class ConatctRepository {

    private ContactDao contactDao;
    private LiveData<List<ContactItems>> allContacts;

    public ConatctRepository(Application application) { //application is subclass of context
        ContactDatabase database = ContactDatabase.getInstance(application);
        contactDao = database.ContactDao();
        allContacts = contactDao.getAllContacts();
    }

    //methods for database operations :-


    public void insert(ContactItems contactItems) {
        new InsertContactAsyncTask(contactDao).execute(contactItems);
    }
    public void update(ContactItems contactItems) {
        new UpdateContactAsyncTask(contactDao).execute(contactItems);
    }
    public void delete(ContactItems contactItems) {
        new DeleteContactAsyncTask(contactDao).execute(contactItems);
    }
    public void deleteAllContacts() {
        new DeleteAllContactsAsyncTask(contactDao).execute();
    }
    public LiveData<List<ContactItems>> getAllContacts() {
        return allContacts;
    }


    private static class InsertContactAsyncTask extends AsyncTask<ContactItems, Void, Void> { //static : doesnt have reference to the
        // repo itself otherwise it could cause memory leak!
        private ContactDao contactDao;
        private InsertContactAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(ContactItems... contactItems) { // ...  is similar to array
            contactDao.Insert(contactItems[0]); //single note
            return null;
        }
    }
    private static class UpdateContactAsyncTask extends AsyncTask<ContactItems, Void, Void> {
        private ContactDao contactDao;
        private UpdateContactAsyncTask(ContactDao contactDao) { //constructor as the class is static
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(ContactItems... contactItems) {
            contactDao.Update(contactItems[0]);
            return null;
        }
    }
    private static class DeleteContactAsyncTask extends AsyncTask<ContactItems, Void, Void> {
        private ContactDao contactDao;
        private DeleteContactAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(ContactItems... contactItems) {
            contactDao.Delete(contactItems[0]);
            return null;
        }
    }
    private static class DeleteAllContactsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ContactDao contactDao;
        private DeleteAllContactsAsyncTask(ContactDao contactDao) {
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.DeleteAllContacts();
            return null;
        }
    }
}


