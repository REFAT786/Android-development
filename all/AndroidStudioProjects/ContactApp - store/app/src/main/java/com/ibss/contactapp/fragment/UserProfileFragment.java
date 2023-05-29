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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.contactapp.R;
import com.ibss.contactapp.domain.UserInfo;

import java.util.List;
import java.util.Objects;

public class UserProfileFragment extends Fragment {

    DatabaseReference databaseReference;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ImageButton editIconBtn,editOffIconBtn;
    Button editBtn,cancelBtn;
    private List<UserInfo> userInfoList;
    TextInputEditText UID_profile_TIE,name_profile_TIE,blood_profile_TIE,personalEmail_profile_TIE,officiallEmail_profile_TIE;
    TextInputEditText phone1_profile_TIE, phone2_profile_TIE, whatsapp_profile_TIE,skype_profile_TIE,address_profile_TIE;
    TextInputEditText nationality_profile_TIE,employee_profile_TIE,department_profile_TIE,designation_profile_TIE;
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
        name_profile_TIE = view.findViewById(R.id.UID_profile_TIE_id);
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
        department_profile_TIE = view.findViewById(R.id.department_TIL_id);
        designation_profile_TIE = view.findViewById(R.id.designation_profile_TIE_id);


        editIconBtn = view.findViewById(R.id.editIconBtn_id);
        editOffIconBtn = view.findViewById(R.id.editOffIconBtn_id);
        editBtn = view.findViewById(R.id.editBtn_id);
        cancelBtn = view.findViewById(R.id.cancelBtn_id);

        //data retrieve

        databaseReference = FirebaseDatabase.getInstance().getReference("user"+ Objects.requireNonNull(mAuth.getCurrentUser()).getUid());

        //finish retrieve

        editOffIconBtn.setOnClickListener(v -> {
            editOffIconBtn.setVisibility(View.INVISIBLE);
            editIconBtn.setVisibility(View.VISIBLE);
            editBtn.setVisibility(View.VISIBLE);
            cancelBtn.setVisibility(View.VISIBLE);
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

        });
        editIconBtn.setOnClickListener(v -> {
            editOffIconBtn.setVisibility(View.VISIBLE);
            editIconBtn.setVisibility(View.INVISIBLE);
            editBtn.setVisibility(View.INVISIBLE);
            cancelBtn.setVisibility(View.INVISIBLE);
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
        });
        editBtn.setOnClickListener(v -> {
            String nam = Objects.requireNonNull(name_profile_TIE.getText()).toString();
            String blood = Objects.requireNonNull(blood_profile_TIE.getText()).toString();
            String p_address = Objects.requireNonNull(personalEmail_profile_TIE.getText()).toString();
            String o_address = Objects.requireNonNull(officiallEmail_profile_TIE.getText()).toString();
            String phn1 = Objects.requireNonNull(phone1_profile_TIE.getText()).toString();
            String phn2 = Objects.requireNonNull(phone2_profile_TIE.getText()).toString();
            String whatapp = Objects.requireNonNull(whatsapp_profile_TIE.getText()).toString();
            String sky = Objects.requireNonNull(skype_profile_TIE.getText()).toString();
            String addr = Objects.requireNonNull(address_profile_TIE.getText()).toString();
            String nation = Objects.requireNonNull(nationality_profile_TIE.getText()).toString();
            String dep = Objects.requireNonNull(department_profile_TIE.getText()).toString();
            String des = Objects.requireNonNull(designation_profile_TIE.getText()).toString();

            String key = databaseReference.push().getKey();
            UserInfo userInfo = new UserInfo(nam, blood, p_address, o_address, phn1, phn2, whatapp, sky, addr, nation, dep, des);

            databaseReference.setValue(userInfo);

            assert key != null;
            databaseReference.child(key).setValue(userInfo);
            Toast.makeText(getContext(), "data save", Toast.LENGTH_SHORT).show();

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserInfo userInfo = null;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userInfo = dataSnapshot.getValue(UserInfo.class);
                    userInfoList.add(userInfo);
                }
                //edittext show info
                assert userInfo != null;
                name_profile_TIE.setText(userInfo.getName());
                blood_profile_TIE.setText(userInfo.getBlood_group());
                personalEmail_profile_TIE.setText(userInfo.getP_email());
                officiallEmail_profile_TIE.setText(userInfo.getO_email());
                phone1_profile_TIE.setText(userInfo.getPhn1());
                phone2_profile_TIE.setText(userInfo.getPhn2());
                whatsapp_profile_TIE.setText(userInfo.getWhatsaap());
                skype_profile_TIE.setText(userInfo.getSkype());
                address_profile_TIE.setText(userInfo.getAddress());
                nationality_profile_TIE.setText(userInfo.getNationality());
                department_profile_TIE.setText(userInfo.getDepartment());
                designation_profile_TIE.setText(userInfo.getDesignation());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}