package com.ibss.ibss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ibss.ibss.fragment.SigninFragment;

public class MainActivity extends AppCompatActivity {

    private static final int LOGINFLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFrag(new SigninFragment(), LOGINFLAG);

    }

    public void loadFrag(Fragment fragment, int flag){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.addToBackStack("reverse");

        if(flag == 0){
            ft.add(R.id.contentBody_id, fragment);
        }
        else {
            ft.replace(R.id.contentBody_id, fragment);
        }
        ft.commit();
    }
}