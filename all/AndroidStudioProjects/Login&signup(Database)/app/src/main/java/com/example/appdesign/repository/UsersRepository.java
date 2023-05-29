package com.example.appdesign.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.appdesign.dao.UsersDao;
import com.example.appdesign.database.UsersDatabase;
import com.example.appdesign.domain.Users;

import java.util.List;

public class UsersRepository {

    private UsersDao usersDao;
    private LiveData<List<Users>> allUsers;

    public UsersRepository(Application application) {
        UsersDatabase database = UsersDatabase.getInstance(application);
        usersDao = database.UsersDao();
        allUsers = usersDao.getAllContacts();

    }
    public void insert(Users users){
        new InserUsersAsyncTask(usersDao).execute(users);
    }
    public void update(Users users){
        new UpdateUsersAsyncTask(usersDao).execute(users);
    }
    public void delete(Users users){
        new DeleteUsersAsyncTask(usersDao).execute(users);
    }
    public  void deleteAllUsers(){
        new DeleteAllUsersAsyncTask(usersDao).execute();
    }
    public LiveData<List<Users>> getAllUsers(){
        return allUsers;
    }
    public LiveData<Users> findByUserId(int id){
        return usersDao.findByUserId(id);
    }
    public LiveData<Users> findByUsername(String uName){
        return usersDao.findByUsername(uName);
    }

    public boolean checkUsers(String uName, String password){
        usersDao.checkUsers(uName,password);
        return true;
    }

    private static class InserUsersAsyncTask extends AsyncTask<Users, Void, Void>{
        private UsersDao usersDao;

        private InserUsersAsyncTask(UsersDao usersDao){
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.Insert(users[0]);
            return null;
        }
    }
    private static class UpdateUsersAsyncTask extends AsyncTask<Users, Void, Void>{
        private UsersDao usersDao;

        private UpdateUsersAsyncTask(UsersDao usersDao){
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.Update(users[0]);
            return null;
        }
    }
    private static class DeleteUsersAsyncTask extends AsyncTask<Users, Void, Void>{
        private UsersDao usersDao;

        private DeleteUsersAsyncTask(UsersDao usersDao){
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.Delete(users[0]);
            return null;
        }
    }
    private static class DeleteAllUsersAsyncTask extends AsyncTask<Users, Void, Void>{
        private UsersDao usersDao;

        private DeleteAllUsersAsyncTask(UsersDao usersDao){
            this.usersDao = usersDao;
        }

        @Override
        protected Void doInBackground(Users... users) {
            usersDao.DeleteAllUsers();
            return null;
        }
    }




}
