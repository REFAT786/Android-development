package com.example.contactapplication.contact;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.nio.channels.AsynchronousChannelGroup;
import java.util.List;

public class ContactRepository {
    private ContactDao dao;
    private LiveData<List<ContactItem>> allContacts;

    public ContactRepository(Application application) {
        ContactDatabase database = ContactDatabase.getInstance(application);
        dao = database.ContactDao();
        allContacts = dao.getAllContacts();
    }

    public void insert(ContactItem contactItem) {
        new InsertContactAsyncTask(dao).execute(contactItem);
    }

    public void delete(ContactItem contactItem) {
        new DeleteContactAsyncTask(dao).execute(contactItem);
    }

    public LiveData<List<ContactItem>> getAllContacts() {
        return this.allContacts;
    }

    public static class InsertContactAsyncTask extends AsyncTask<ContactItem, Void, Void> {
        private ContactDao dao;
        private InsertContactAsyncTask(ContactDao dao) {
           this.dao = dao;
        }

        @Override
        protected Void doInBackground(ContactItem... contactItems) {
            dao.insert(contactItems[0]);
            return null;
        }
    }

    public static class DeleteContactAsyncTask extends AsyncTask<ContactItem, Void, Void> {
        private ContactDao dao;
        private DeleteContactAsyncTask(ContactDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(ContactItem... contactItems) {
            dao.delete(contactItems[0]);
            return null;
        }
    }
}
