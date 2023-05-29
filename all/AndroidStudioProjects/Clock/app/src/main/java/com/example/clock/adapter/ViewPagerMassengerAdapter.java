package com.example.clock.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.clock.fragment.AlarmFragment;
import com.example.clock.fragment.StopWatchFragment;
import com.example.clock.fragment.WatchFragment;

public class ViewPagerMassengerAdapter extends FragmentStateAdapter {

    public ViewPagerMassengerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPagerMassengerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ViewPagerMassengerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if(position == 0){
            fragment = new WatchFragment();
        }
        else if (position == 1) {
            fragment = new StopWatchFragment();
        }
        else {
            fragment =  new AlarmFragment();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
