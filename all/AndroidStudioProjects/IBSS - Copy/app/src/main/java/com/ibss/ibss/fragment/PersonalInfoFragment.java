package com.ibss.ibss.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibss.ibss.R;
import com.ibss.ibss.domain.User;

import java.util.Objects;

public class PersonalInfoFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    private TextInputEditText name_tie, blood_tie, department_tie, gender_tie;
    private Button saveBtn;
    User user;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personal_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        name_tie = view.findViewById(R.id.name_edittext_signup_id);
        blood_tie = view.findViewById(R.id.blood_edittext_signup_id);
        department_tie = view.findViewById(R.id.department_edittext_signup_id);
        gender_tie = view.findViewById(R.id.gender_edittext_signup_id);

        saveBtn = view.findViewById(R.id.save_id);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(name_tie.getText()).toString();
                String blood = Objects.requireNonNull(blood_tie.getText().toString());
                String department = Objects.requireNonNull(department_tie.getText().toString());
                String gender = Objects.requireNonNull(gender_tie.getText().toString());

                FirebaseUser firebaseUser = mAuth.getCurrentUser();
                assert firebaseUser != null;
                String id = firebaseUser.getUid();
                String email = firebaseUser.getEmail();

                reference.child(id).setValue(user).addOnCompleteListener(task1 -> {
                    if(task1.isSuccessful()){
                        Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                        User user1 = new User(id, email, name, blood, department, gender, 0);
                        reference.child(id).setValue(user1);

                        loadFragment(new SigninFragment());
                    }
                    else {
                        Toast.makeText(getContext(), "Not successful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentBody_id, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}