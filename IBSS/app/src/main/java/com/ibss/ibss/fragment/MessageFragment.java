package com.ibss.ibss.fragment;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ibss.ibss.R;
import com.ibss.ibss.adapter.MessagingAdapter;
import com.ibss.ibss.domain.Chat;
import com.ibss.ibss.domain.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MessageFragment extends Fragment {

    ImageView backBtn, profilePic, sendBtn;
    TextView name;
    EditText msgField;
    User userPojo;
   MessagingAdapter adapter;
    ArrayList<Chat> chatList;
    RecyclerView recyclerView;
    DatabaseReference reference;

    public MessageFragment(User userPojo) {
        // Required empty public constructor
        this.userPojo = userPojo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backBtn = view.findViewById(R.id.backBtn_id);
        profilePic = view.findViewById(R.id.profilePic_msg_id);
        sendBtn = view.findViewById(R.id.sendBtn_id);
        name = view.findViewById(R.id.profileMsg_name_id);
        msgField = view.findViewById(R.id.msg_editText_id);

        recyclerView = view.findViewById(R.id.msg_recyclerView_id);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        name.setText(userPojo.getName());

        reference = FirebaseDatabase.getInstance().getReference("user");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                readMsg(currentUser.getUid(),userPojo.getUserId());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new ShowAllUsersMassageFragment());
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msgField.getText().toString();

                if(!msg.equals("")){
                    sendMsg(currentUser.getUid(), userPojo.getUserId(), msg);
                } else {
                    Toast.makeText(getContext(), "can't send empty msg", Toast.LENGTH_SHORT).show();
                }
                msgField.setText("");
            }
        });


    }

    private void sendMsg(String sender, String receiver, String msg){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("msg", msg);

        reference.child("chat").push().setValue(hashMap);


    }

    private void readMsg(String myId, String userId){
        chatList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("chat");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //chatList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Chat chat = dataSnapshot.getValue(Chat.class);

                    if(chat.getReceiver().equals(myId) && chat.getSender().equals(userId)||
                        chat.getReceiver().equals(userId) && chat.getSender().equals(myId)){
                        chatList.add(chat);
                    }

                }
                adapter = new MessagingAdapter(chatList, getContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

   public void loadFragment(Fragment fragment){
       FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
       fragmentTransaction.replace(R.id.contain_id, fragment);
       fragmentTransaction.addToBackStack(null);
       fragmentTransaction.commit();
   }




}