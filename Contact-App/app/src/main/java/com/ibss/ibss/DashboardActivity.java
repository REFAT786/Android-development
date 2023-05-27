package com.ibss.ibss;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.ibss.domain.User;
import com.ibss.ibss.fragment.ProfileshowFragment;
import com.ibss.ibss.fragment.UsersListFragment;

public class DashboardActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton profileShow;

    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String uId;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        uId = firebaseUser.getUid();
        //User user = database.getReference().child("user").child(uId).get();

        reference.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);

                if (user1.getUserType() == 1) {
                    Toast.makeText(DashboardActivity.this, "Admin", Toast.LENGTH_SHORT).show();
                    loadFrag(new UsersListFragment());

                } else {
                    Toast.makeText(DashboardActivity.this, "User", Toast.LENGTH_SHORT).show();
                    loadFrag(new ProfileshowFragment());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        drawerLayout = findViewById(R.id.drawer_nav_id);
        navigationView = findViewById(R.id.navigationView_id);
        toolbar = findViewById(R.id.toolbarView_id);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.OpenDrawer, R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if(id == R.id.usersMenu_id){
                Toast.makeText(this, "User", Toast.LENGTH_SHORT).show();
                loadFrag(new UsersListFragment());
            }

            else if(id == R.id.groupMenu_id){
                Toast.makeText(this, "Group", Toast.LENGTH_SHORT).show();

            }
            else if(id == R.id.supportMenu_id){
                Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();

            }
            else if(id == R.id.notificationMenu_id){
                Toast.makeText(this, "Notification", Toast.LENGTH_SHORT).show();

            }
            else if(id == R.id.logout_id){
                Toast.makeText(this, "Log out", Toast.LENGTH_SHORT).show();

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            }
            else {
                Toast.makeText(this, "Choose the option", Toast.LENGTH_SHORT).show();

            }
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });
        View headerView = navigationView.getHeaderView(0);
        profileShow = headerView.findViewById(R.id.profileShow_id);
        profileShow.setOnClickListener(v -> {
            Toast.makeText(this, "Profile Show", Toast.LENGTH_SHORT).show();
            loadFrag(new ProfileshowFragment());
            drawerLayout.closeDrawer(GravityCompat.START);

        });


    }

    public void loadFrag(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contain_id,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}