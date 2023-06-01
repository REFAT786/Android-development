package com.ibss.ibss.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.ibss.DashboardActivity;
import com.ibss.ibss.MainActivity;
import com.ibss.ibss.R;
import com.ibss.ibss.adapter.UserListAdapter;
import com.ibss.ibss.dao.UserListSelectListener;
import com.ibss.ibss.domain.User;

import java.util.Objects;

public class ShowUserInfoFragment extends Fragment {

    private TextView userName, position, email;
    User user;
    String uId;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView status;
    Switch active,inactive;

    public ShowUserInfoFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        uId = firebaseUser.getUid();

        userName = view.findViewById(R.id.name_text_profile_show_id);
        position = view.findViewById(R.id.position_show_id);
        status = view.findViewById(R.id.status_id);
        email = view.findViewById(R.id.mail_text_id);

        userName.setText(user.getName());
        position.setText(user.getDepartment());
        email.setText(user.getEmail());

        String userId = user.getUserId();
        String em = user.getEmail();
        String nm = user.getName();
        String blood = user.getBlood();
        String dep = user.getDepartment();
        String gen = user.getGender();
        String p_pic = user.getImageUrl();

        status.setText(user.getStatus());

        reference.child(uId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user1 = snapshot.getValue(User.class);
                //String user1Id = user1.getUserId();

                if(user1.getUserType() == 1){
                    status.setVisibility(View.VISIBLE);

                    status.setOnClickListener(v -> {

                        Dialog dialog = new Dialog(getContext());
                        dialog.setContentView(R.layout.status_layout);

                        active = dialog.findViewById(R.id.active_switch_id);
                        inactive = dialog.findViewById(R.id.inactive_switch_id);

                        active.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                            @Override
                            public void onClick(View v) {
                                if(active.isChecked()){
                                    user  = new User(userId, em, nm, blood, dep, gen, 0, p_pic, "Active");
                                    reference.child(userId).setValue(user);
                                    Toast.makeText(getContext(), "Active", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "else Active", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                        inactive.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                            @Override
                            public void onClick(View v) {
                                if(inactive.isChecked()){
                                    user = new User(userId, em, nm, blood, dep, gen, 2, p_pic, "Inactive");
                                    reference.child(userId).setValue(user);
                                    Toast.makeText(getContext(), "Inactive", Toast.LENGTH_SHORT).show();
                                } else {

                                    Toast.makeText(getContext(), "else Inactive", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                        dialog.show();

                    });
                } else if (user1.getUserType() == 0) {
                    status.setVisibility(View.INVISIBLE);
                }
                else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }




}