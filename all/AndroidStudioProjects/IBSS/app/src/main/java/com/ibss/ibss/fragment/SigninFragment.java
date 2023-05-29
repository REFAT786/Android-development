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

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ibss.ibss.DashboardActivity;
import com.ibss.ibss.R;

public class SigninFragment extends Fragment {

    private TextView createAccountClick;
    private FirebaseAuth mAuth;
    private TextInputEditText email_login_tie, password_login_tie;
    public SigninFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getContext(), DashboardActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();


        createAccountClick = view.findViewById(R.id.create_text_id);

        createAccountClick.setOnClickListener(v -> loadFragment(new SignupFragment()));

        email_login_tie = view.findViewById(R.id.email_edittext_signin_id);
        password_login_tie = view.findViewById(R.id.password_edittext_signin_id);

        Button loginBtn = view.findViewById(R.id.login_btn_id);
        loginBtn.setOnClickListener(v -> {
            String email = String.valueOf(email_login_tie.getText());
            String password = String.valueOf(password_login_tie.getText());

            if(email.isEmpty()){
                Toast.makeText(getContext(), "Enter you email address", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                Toast.makeText(getContext(), "Email address is not a right pattern", Toast.LENGTH_SHORT).show();
                return;

            }
            if(password.isEmpty()){
                Toast.makeText(getContext(), "Enter you password", Toast.LENGTH_SHORT).show();
                return;
            }
            if(password.length()<6){
                Toast.makeText(getContext(), "Password is less than 6", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){

                            //loadFragment(new UsersListFragment());
                            Intent intent = new Intent(getContext(), DashboardActivity.class);
                            startActivity(intent);

                        }
                        else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
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