package com.ibss.vpn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ibss.vpn.fragment.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    private static final int LOGINFLAG = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new DashboardFragment(), LOGINFLAG);
    }

    public void loadFragment(Fragment fragment, int flag){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("reverse");

        if(flag == 0){
            fragmentTransaction.add(R.id.mainActivityBody_id, fragment);
        }
        else{
            fragmentTransaction.replace(R.id.mainActivityBody_id, fragment);
        }
        fragmentTransaction.commit();
    }
}