package com.example.appdesign.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.appdesign.domain.Users;
import com.example.appdesign.repository.UsersRepository;

import java.util.List;

public class UsersViewModel extends AndroidViewModel {
    private UsersRepository repository;
    private LiveData<List<Users>> allUsers;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        repository = new UsersRepository(application);
        allUsers = repository.getAllUsers();
    }

    public  void insert(Users users){repository.insert(users);}
    public void update(Users users){repository.update(users);}
    public void delete(Users users){repository.delete(users);}
    public void deleteAllUsers(){repository.deleteAllUsers();}
    public LiveData<List<Users>> getAllUsers(){return allUsers;}
    public LiveData<Users> findByUserId(int id){return repository.findByUserId(id);}
    public LiveData<Users> findByUsername(String uName){return repository.findByUsername(uName);}

    public boolean checkUsers(String uName, String password){return repository.checkUsers(uName,password);}

}
