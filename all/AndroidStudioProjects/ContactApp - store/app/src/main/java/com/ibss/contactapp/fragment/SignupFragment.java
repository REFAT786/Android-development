package com.ibss.contactapp.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
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
import com.ibss.contactapp.R;


public class SignupFragment extends Fragment {

    private FirebaseAuth mAuth;
    private TextInputEditText email_TIE, password_TIE;
    Button signupBtn;
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

        TextView loginLinkText = view.findViewById(R.id.login_link_text_id);
        loginLinkText.setOnClickListener(v -> {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.contentBody_id, new LoginFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        email_TIE = view.findViewById(R.id.email_TIE_id);
        password_TIE = view.findViewById(R.id.password_TIE_id);
        signupBtn = view.findViewById(R.id.SignupBtn_id);
        signupBtn.setOnClickListener(v -> {
            String email = String.valueOf(email_TIE.getText());
            String password = String.valueOf(password_TIE.getText());

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

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.contentBody_id, new LoginFragment());
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();

                        } else {
                            Toast.makeText(getContext(), "Registration failed",
                                    Toast.LENGTH_SHORT).show();

                        }
                    });
        });
    }
}