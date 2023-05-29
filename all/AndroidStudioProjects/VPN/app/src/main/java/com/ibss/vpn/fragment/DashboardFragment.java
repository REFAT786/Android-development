package com.ibss.vpn.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.ibss.vpn.CountryActivity;
import com.ibss.vpn.R;

public class DashboardFragment extends Fragment {
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ConstraintLayout vpnBtn;
    TextView connectionText;
    CardView cardView;
    public DashboardFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        drawerLayout = view.findViewById(R.id.drawerLayout_id);
        navigationView = view.findViewById(R.id.navigation_id);
        toolbar = view.findViewById(R.id.toolbar_id);

        vpnBtn = view.findViewById(R.id.vpnBtn_id);
        connectionText = view.findViewById(R.id.connectionText_id);
        cardView = view.findViewById(R.id.cardView_id);

        ((AppCompatActivity) requireActivity()).setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle((Activity) getContext(),drawerLayout,toolbar,R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.home_id){
                Toast.makeText(getContext(), "Home Fragment", Toast.LENGTH_SHORT).show();
                loadFragment(new HomeFragment());
            }
            return true;
        });

        vpnBtn.setOnClickListener(v -> {

            if(connectionText.getText() == "Connect"){
                connectionText.setText("Disconnect");

            }
            else {
                connectionText.setText("Connect");
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CountryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack("reverse");
        fragmentTransaction.replace(R.id.contentLayout_id, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_items, menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings_id:
                Toast.makeText(getContext(), "Setting", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout_id:
                Toast.makeText(getContext(), "Log out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}