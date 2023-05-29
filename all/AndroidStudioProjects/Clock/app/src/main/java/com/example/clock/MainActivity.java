package com.example.clock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlarmManager;
import android.os.Bundle;

import com.example.clock.adapter.ViewPagerMassengerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout_id);
        viewPager = findViewById(R.id.viewPager_id);

        ViewPagerMassengerAdapter adapter = new ViewPagerMassengerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if(position == 0){
                tab.setText("Watch");
            }
            else if(position == 1){
                tab.setText("Stopwatch");
            }
            else {
                tab.setText("Alarm");
            }
        }).attach();
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


    }
}