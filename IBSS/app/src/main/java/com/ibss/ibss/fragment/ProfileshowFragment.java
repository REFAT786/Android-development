package com.ibss.ibss.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ibss.ibss.DashboardActivity;
import com.ibss.ibss.R;
import com.ibss.ibss.domain.User;

import java.util.Objects;

public class ProfileshowFragment extends Fragment {
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference reference;
    StorageReference storageReference;
    String uId;
    int u_type = 0;
    String p_pic;
    private Uri imageUri;

    private ImageView edit, infoEdit, profilePic, editPic;
    private TextView userName, position, email;

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch aSwitch;
    private TextInputLayout currentPass, newPass, confirmPass;
    private TextInputEditText currentPass_edittext, newPass_edittext, confirmPass_edittext;
    private TextInputEditText nameUpdate, bloodUpdate, departmentUpdate, genderUpdate;
    private Button cancel, reset, updateBtn;

    public ProfileshowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profileshow, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edit = view.findViewById(R.id.edit_id);
        infoEdit = view.findViewById(R.id.personalInfo_edit_id);
        userName = view.findViewById(R.id.name_text_profile_id);
        position = view.findViewById(R.id.position_id);
        email = view.findViewById(R.id.mail_text_profile_id);

        profilePic = view.findViewById(R.id.profile_pic_id);
        editPic = view.findViewById(R.id.editPic_id);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("user");
        storageReference = FirebaseStorage.getInstance().getReference();

        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        assert firebaseUser != null;
        uId = firebaseUser.getUid();

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            imageUri = data.getData();
                            profilePic.setImageURI(imageUri);
                        } else {
                            Toast.makeText(getContext(), "NO Image select", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent photoPicker = new Intent();
                photoPicker.setAction(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);

            }
        });

        editPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    uploadToFirebase(imageUri);
                } else {
                    Toast.makeText(getContext(), "Please select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });




        infoEdit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CutPasteId")
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Edit Personal Password", Toast.LENGTH_SHORT).show();

                Dialog dialog1 = new Dialog(getContext());
                dialog1.setContentView(R.layout.custom_dialog_personalinfo_layout);

                nameUpdate = dialog1.findViewById(R.id.name_edittext_update_id);
                bloodUpdate = dialog1.findViewById(R.id.blood_edittext_update_id);
                departmentUpdate = dialog1.findViewById(R.id.department_edittext_update_id);
                genderUpdate = dialog1.findViewById(R.id.gender_edittext_update_id);
                updateBtn = dialog1.findViewById(R.id.update_id);

                String em = firebaseUser.getEmail();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            User currentUser = dataSnapshot.getValue(User.class);
                            if (Objects.equals(dataSnapshot.getKey(), uId)) {

                                assert currentUser != null;
                                nameUpdate.setText(currentUser.getName());
                                bloodUpdate.setText(currentUser.getBlood());
                                departmentUpdate.setText(currentUser.getDepartment());
                                genderUpdate.setText(currentUser.getGender());
                                u_type = currentUser.getUserType();
                                p_pic = String.valueOf(currentUser.getImageUrl());

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nm = Objects.requireNonNull(nameUpdate.getText()).toString();
                        String blood = Objects.requireNonNull(bloodUpdate.getText()).toString();
                        String dep = Objects.requireNonNull(departmentUpdate.getText()).toString();
                        String gen = Objects.requireNonNull(genderUpdate.getText()).toString();

                        User user1 = new User(uId, em, nm, blood, dep, gen, u_type, p_pic, "Active");
                        reference.child(uId).setValue(user1);
                        Toast.makeText(getContext(), "data update", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getContext(), DashboardActivity.class);
                        startActivity(intent);
                    }
                });

                dialog1.show();
            }
        });

        edit.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Edit Password", Toast.LENGTH_SHORT).show();

            Dialog dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.custom_dialog_layout);

            Button reset = dialog.findViewById(R.id.reset_btn_id);
            Button cancel = dialog.findViewById(R.id.cancel_btn_id);

            currentPass = dialog.findViewById(R.id.current_password_layout_id);
            newPass = dialog.findViewById(R.id.new_password_layout_id);
            confirmPass = dialog.findViewById(R.id.confirm_password_layout_id);

            currentPass_edittext = dialog.findViewById(R.id.current_password_edittext_id);
            newPass_edittext = dialog.findViewById(R.id.new_password_edittext_id);
            confirmPass_edittext = dialog.findViewById(R.id.confirm_password_edittext_id);

            aSwitch = dialog.findViewById(R.id.switch_id);


            currentPass.setVisibility(View.INVISIBLE);
            newPass.setVisibility(View.INVISIBLE);
            confirmPass.setVisibility(View.INVISIBLE);
            cancel.setVisibility(View.INVISIBLE);
            reset.setVisibility(View.INVISIBLE);

            aSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("TAG", "onViewCreated: " + aSwitch.isChecked());

                    if (aSwitch.isChecked()) {
                        currentPass.setVisibility(View.VISIBLE);
                        newPass.setVisibility(View.VISIBLE);
                        confirmPass.setVisibility(View.VISIBLE);
                        cancel.setVisibility(View.VISIBLE);
                        reset.setVisibility(View.VISIBLE);

                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                String c_pass = Objects.requireNonNull(currentPass_edittext.getText()).toString();
                                String n_pass = Objects.requireNonNull(newPass_edittext.getText()).toString();
                                String con_pass = Objects.requireNonNull(confirmPass_edittext.getText()).toString();

                                FirebaseUser firebaseUser = mAuth.getCurrentUser();

                                AuthCredential credential = EmailAuthProvider.getCredential(Objects.requireNonNull(firebaseUser.getEmail()), c_pass);

                                if (!c_pass.equals(n_pass)) {
                                    if (n_pass.equals(con_pass)) {

                                        firebaseUser.reauthenticate(credential)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            firebaseUser.updatePassword(n_pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Log.d("TAG", "Password updated");
                                                                        Toast.makeText(getContext(), "Password updated", Toast.LENGTH_SHORT).show();
                                                                        FirebaseAuth.getInstance().signOut();
                                                                        loadFragment(new SigninFragment());

                                                                    } else {
                                                                        Log.d("TAG", "Error password not updated");
                                                                        Toast.makeText(getContext(), "Error password not updated", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            });
                                                        } else {
                                                            Log.d("TAG", "Error auth failed");
                                                            Toast.makeText(getContext(), "Error auth failed", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });

                                        // reference.child(uId).setValue(user);
                                        Toast.makeText(getContext(), "reset data", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getContext(), "New password and confirm password are not same", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(getContext(), "Current password and New password are same", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                    } else {

                        currentPass.setVisibility(View.INVISIBLE);
                        newPass.setVisibility(View.INVISIBLE);
                        confirmPass.setVisibility(View.INVISIBLE);
                        cancel.setVisibility(View.INVISIBLE);
                        reset.setVisibility(View.INVISIBLE);

                    }
                }
            });

            dialog.show();

        });

    }

    private void uploadToFirebase(Uri uri){

        StorageReference imageReference = storageReference.child(System.currentTimeMillis()+ "."+ getFileExtension(uri));

        imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                User userImage = new User(uri.toString());
                String key = reference.push().getKey();
                reference.child(key).setValue(userImage);
                Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private String getFileExtension(Uri fileUri){

        ContentResolver contentResolver = requireContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }




    /*
    ActivityResultLauncher<String> picker = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {

            imageUri = result;
            profilePic.setImageURI(imageUri);
        }
    });

     */



    @Override
    public void onStart() {
        super.onStart();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User currentUser = dataSnapshot.getValue(User.class);

                    if (Objects.equals(dataSnapshot.getKey(), uId)) {

                        assert currentUser != null;
                        userName.setText(currentUser.getName());
                        position.setText(currentUser.getDepartment());
                        email.setText(currentUser.getEmail());
                        profilePic.setImageURI(currentUser.getImageUrl());

                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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