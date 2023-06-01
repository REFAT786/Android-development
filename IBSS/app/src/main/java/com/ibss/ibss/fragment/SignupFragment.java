package com.ibss.ibss.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibss.ibss.R;
import com.ibss.ibss.domain.User;

import java.util.Objects;


public class SignupFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;

    private TextInputEditText  email_tie, password_tie;
    private Button signupBtn;
    User user;
    private TextView loginClick;

    String id;
    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        email_tie = view.findViewById(R.id.email_edittext_signup_id);
        password_tie = view.findViewById(R.id.password_edittext_signup_id);
        signupBtn = view.findViewById(R.id.signup_btn_id);

        signupBtn.setOnClickListener(v -> {

            String email = Objects.requireNonNull(email_tie.getText()).toString();
            String password = Objects.requireNonNull(password_tie.getText()).toString();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {

                if(task.isSuccessful()){


                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String id = firebaseUser.getUid();

                    user = new User(id,email, "", "", "", "", 0, "", "Active");
                    reference.child(id).setValue(user).addOnCompleteListener(task1 -> {
                        if(task1.isSuccessful()){
                            Toast.makeText(getContext(), "Successful", Toast.LENGTH_SHORT).show();

                            loadFragment(new PersonalInfoFragment());
                        }
                        else {
                            Toast.makeText(getContext(), "Not successful", Toast.LENGTH_SHORT).show();
                        }
                    });


                } else {
                    Toast.makeText(getContext(), "Not successful", Toast.LENGTH_SHORT).show();

                }
            });

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