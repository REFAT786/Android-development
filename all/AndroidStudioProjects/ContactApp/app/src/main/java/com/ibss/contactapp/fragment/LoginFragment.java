package com.ibss.contactapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ibss.contactapp.MainActivity;
import com.ibss.contactapp.R;


public class LoginFragment extends Fragment {

    private FirebaseAuth mAuth;
    private TextInputEditText email_Login_TIE, password_Login_TIE;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentBody_id, new DashboardFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        Button crash = view.findViewById(R.id.crashButton);

        crash.setOnClickListener(v -> {
            throw new RuntimeException("Test Crash");
        });

        TextView signupLinkText = view.findViewById(R.id.signup_link_text_id);
        signupLinkText.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentBody_id, new SignupFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        email_Login_TIE = view.findViewById(R.id.email_Login_TIE_id);
        password_Login_TIE = view.findViewById(R.id.password_Login_TIE_id);

        Button loginBtn = view.findViewById(R.id.LoginBtn_id);
        loginBtn.setOnClickListener(v -> {
            String email = String.valueOf(email_Login_TIE.getText());
            String password = String.valueOf(password_Login_TIE.getText());

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

                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentBody_id, new DashboardFragment());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }
                        else {
                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}