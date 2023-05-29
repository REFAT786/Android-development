package com.ibss.ibss.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibss.ibss.R;
import com.ibss.ibss.dao.UserListSelectListener;
import com.ibss.ibss.domain.User;

public class ShowUserInfoFragment extends Fragment {

    private TextView userName, position;
    User user;

    public ShowUserInfoFragment(User user) {
        // Required empty public constructor
        this.user = user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_user_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        userName = view.findViewById(R.id.name_text_profile_show_id);
        position = view.findViewById(R.id.position_show_id);

        userName.setText(user.getName());
        position.setText(user.getDepartment());
    }


}