package com.ibss.contactapp.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibss.contactapp.R;

public class FirebaseMsgFragment extends Fragment {

    TextView msgText;
    public FirebaseMsgFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_firebase_msg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msgText = view.findViewById(R.id.msgText_id);

        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("remoteMsg", Context.MODE_PRIVATE);
        String msg1 = sharedPreferences.getString("msg"," ");
        msgText = view.findViewById(R.id.msgText_id);
        msgText.setText(msg1);
    }
}