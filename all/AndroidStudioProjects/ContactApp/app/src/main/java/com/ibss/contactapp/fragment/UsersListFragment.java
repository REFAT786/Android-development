package com.ibss.contactapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.contactapp.R;
import com.ibss.contactapp.adapter.UserListAdapter;
import com.ibss.contactapp.dao.UserListSelectListener;
import com.ibss.contactapp.domain.User;

import java.util.ArrayList;
public class UsersListFragment extends Fragment implements UserListSelectListener {

    ArrayList<User> userList = new ArrayList<>();
    RecyclerView recyclerView;
    UserListAdapter adapter;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    String uId;

    public UsersListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //databaseReference = FirebaseDatabase.getInstance().getReference("user");

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        uId = firebaseUser.getUid();

        recyclerView = view.findViewById(R.id.user_list_recyclerView_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //userList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    userList.add(user);
                }
                Toast.makeText(getContext(), "data list", Toast.LENGTH_SHORT).show();

                adapter = new UserListAdapter(userList, getContext(), UsersListFragment.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void onItemClick(User user){
        Toast.makeText(getContext(), user.getName(), Toast.LENGTH_SHORT).show();

        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contain_id, new UserProfileFragment());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}