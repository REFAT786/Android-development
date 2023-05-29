package com.ibss.contactapp.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.contactapp.R;
import com.ibss.contactapp.domain.User;

import java.util.Objects;


public class UserProfileFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    ImageButton editIconBtn,editOffIconBtn;
    Button editBtn,cancelBtn;
    TextInputEditText UID_profile_TIE,name_profile_TIE,blood_profile_TIE,personalEmail_profile_TIE,officiallEmail_profile_TIE;
    TextInputEditText phone1_profile_TIE, phone2_profile_TIE, whatsapp_profile_TIE,skype_profile_TIE,address_profile_TIE;
    TextInputEditText nationality_profile_TIE,employee_profile_TIE,department_profile_TIE,designation_profile_TIE;
    String  uId;
    User user;
    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @SuppressLint("CutPasteId")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UID_profile_TIE = view.findViewById(R.id.UID_profile_TIE_id);
        name_profile_TIE = view.findViewById(R.id.name_profile_TIE_id);
        blood_profile_TIE = view.findViewById(R.id.blood_profile_TIE_id);
        personalEmail_profile_TIE = view.findViewById(R.id.personalEmail_profile_TIE_id);
        officiallEmail_profile_TIE = view.findViewById(R.id.officiallEmail_profile_TIE_id);
        phone1_profile_TIE = view.findViewById(R.id.phone1_profile_TIE_id);
        phone2_profile_TIE = view.findViewById(R.id.phone2_profile_TIE_id);
        whatsapp_profile_TIE = view.findViewById(R.id.whatsapp_profile_TIE_id);
        skype_profile_TIE = view.findViewById(R.id.skype_profile_TIE_id);
        address_profile_TIE = view.findViewById(R.id.address_profile_TIE_id);
        nationality_profile_TIE = view.findViewById(R.id.nationality_profile_TIE_id);
        employee_profile_TIE = view.findViewById(R.id.employee_profile_TIE_id);
        department_profile_TIE = view.findViewById(R.id.department_profile_TIE_id);
        designation_profile_TIE = view.findViewById(R.id.designation_profile_TIE_id);

        editIconBtn = view.findViewById(R.id.editIconBtn_id);
        editOffIconBtn = view.findViewById(R.id.editOffIconBtn_id);
        editBtn = view.findViewById(R.id.editBtn_id);
        cancelBtn = view.findViewById(R.id.cancelBtn_id);

        Bundle bundle = getArguments();
        if(bundle != null){
            String id = bundle.getString("id");

        }


        //data retrieve

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        uId = firebaseUser.getUid();


        //finish retrieve

        editIconBtn.setOnClickListener(v -> editOn());
        editOffIconBtn.setOnClickListener(v -> editOff());

        editBtn.setOnClickListener(v -> {
            editOn();

            String nam = Objects.requireNonNull(name_profile_TIE.getText()).toString();
            String blood = Objects.requireNonNull(blood_profile_TIE.getText()).toString();
            String p_address = Objects.requireNonNull(personalEmail_profile_TIE.getText()).toString();
            String o_address = Objects.requireNonNull(officiallEmail_profile_TIE.getText()).toString();
            String phn1 = Objects.requireNonNull(phone1_profile_TIE.getText()).toString();
            String phn2 = Objects.requireNonNull(phone2_profile_TIE.getText()).toString();
            String whatsapp = Objects.requireNonNull(whatsapp_profile_TIE.getText()).toString();
            String sky = Objects.requireNonNull(skype_profile_TIE.getText()).toString();
            String addr = Objects.requireNonNull(address_profile_TIE.getText()).toString();
            String nation = Objects.requireNonNull(nationality_profile_TIE.getText()).toString();
            String emp_id = Objects.requireNonNull(employee_profile_TIE.getText()).toString();
            String dep = Objects.requireNonNull(department_profile_TIE.getText()).toString();
            String des = Objects.requireNonNull(designation_profile_TIE.getText()).toString();

            user = new User(uId,nam, blood, p_address, o_address, phn1, phn2, whatsapp, sky, addr, nation,emp_id, dep, des);
            reference.child(uId).setValue(user);
            Toast.makeText(getContext(), "data save", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User currentUser = dataSnapshot.getValue(User.class);
                    if (Objects.equals(dataSnapshot.getKey(), uId)) {

                        UID_profile_TIE.setText(uId);
                        assert currentUser != null;
                        name_profile_TIE.setText(currentUser.getName());
                        blood_profile_TIE.setText(currentUser.getBlood_group());
                        personalEmail_profile_TIE.setText(currentUser.getP_email());
                        officiallEmail_profile_TIE.setText(currentUser.getO_email());
                        phone1_profile_TIE.setText(currentUser.getPhn1());
                        phone2_profile_TIE.setText(currentUser.getPhn2());
                        whatsapp_profile_TIE.setText(currentUser.getWhatsaap());
                        skype_profile_TIE.setText(currentUser.getSkype());
                        address_profile_TIE.setText(currentUser.getAddress());
                        nationality_profile_TIE.setText(currentUser.getNationality());
                        employee_profile_TIE.setText(currentUser.getEmp_id());
                        department_profile_TIE.setText(currentUser.getDepartment());
                        designation_profile_TIE.setText(currentUser.getDesignation());
                    }
                }
/*
                if(user!= null){
                    UID_profile_TIE.setText(uId);
//                    name_profile_TIE.setText(snapshot.get);
                    blood_profile_TIE.setText(user.getBlood_group());
                    personalEmail_profile_TIE.setText(user.getP_email());
                    officiallEmail_profile_TIE.setText(user.getO_email());
                    phone1_profile_TIE.setText(user.getPhn1());
                    phone2_profile_TIE.setText(user.getPhn2());
                    whatsapp_profile_TIE.setText(user.getWhatsaap());
                    skype_profile_TIE.setText(user.getSkype());
                    address_profile_TIE.setText(user.getAddress());
                    nationality_profile_TIE.setText(user.getNationality());
                    employee_profile_TIE.setText(user.getEmp_id());
                    department_profile_TIE.setText(user.getDepartment());
                    designation_profile_TIE.setText(user.getDesignation());
                }else{
                    name_profile_TIE.setText("");
                    blood_profile_TIE.setText("");
                    personalEmail_profile_TIE.setText("");
                    officiallEmail_profile_TIE.setText("");
                    phone1_profile_TIE.setText("");
                    phone2_profile_TIE.setText("");
                    whatsapp_profile_TIE.setText("");
                    skype_profile_TIE.setText("");
                    address_profile_TIE.setText("");
                    nationality_profile_TIE.setText("");
                    employee_profile_TIE.setText("");
                    department_profile_TIE.setText("");
                    designation_profile_TIE.setText("");
                }

 */
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void editOff(){
        editOffIconBtn.setVisibility(View.INVISIBLE);
        editIconBtn.setVisibility(View.VISIBLE);
        editBtn.setVisibility(View.VISIBLE);
        cancelBtn.setVisibility(View.VISIBLE);
        //UID_profile_TIE.setFocusableInTouchMode(true);
        name_profile_TIE.setFocusableInTouchMode(true);
        blood_profile_TIE.setFocusableInTouchMode(true);
        personalEmail_profile_TIE.setFocusableInTouchMode(true);
        officiallEmail_profile_TIE.setFocusableInTouchMode(true);
        phone1_profile_TIE.setFocusableInTouchMode(true);
        phone2_profile_TIE.setFocusableInTouchMode(true);
        whatsapp_profile_TIE.setFocusableInTouchMode(true);
        skype_profile_TIE.setFocusableInTouchMode(true);
        address_profile_TIE.setFocusableInTouchMode(true);
        nationality_profile_TIE.setFocusableInTouchMode(true);
        employee_profile_TIE.setFocusableInTouchMode(true);
        department_profile_TIE.setFocusableInTouchMode(true);
        designation_profile_TIE.setFocusableInTouchMode(true);
    }
    public void editOn(){
        editOffIconBtn.setVisibility(View.VISIBLE);
        editIconBtn.setVisibility(View.INVISIBLE);
        editBtn.setVisibility(View.INVISIBLE);
        cancelBtn.setVisibility(View.INVISIBLE);
        //UID_profile_TIE.setFocusableInTouchMode(true);
        name_profile_TIE.setFocusableInTouchMode(false);
        blood_profile_TIE.setFocusableInTouchMode(false);
        personalEmail_profile_TIE.setFocusableInTouchMode(false);
        officiallEmail_profile_TIE.setFocusableInTouchMode(false);
        phone1_profile_TIE.setFocusableInTouchMode(false);
        phone2_profile_TIE.setFocusableInTouchMode(false);
        whatsapp_profile_TIE.setFocusableInTouchMode(false);
        skype_profile_TIE.setFocusableInTouchMode(false);
        address_profile_TIE.setFocusableInTouchMode(false);
        nationality_profile_TIE.setFocusableInTouchMode(false);
        employee_profile_TIE.setFocusableInTouchMode(false);
        department_profile_TIE.setFocusableInTouchMode(false);
        designation_profile_TIE.setFocusableInTouchMode(false);
    }
}