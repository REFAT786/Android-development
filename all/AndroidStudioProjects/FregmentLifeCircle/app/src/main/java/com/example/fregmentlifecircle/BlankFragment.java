package com.example.fregmentlifecircle;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class BlankFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toast.makeText(getActivity(), "Create", Toast.LENGTH_SHORT).show();
        Log.d("create","create");
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("create_view", "Create View");
    }

    @Override
    public void onStart() {
        super.onStart();
        //Toast.makeText(getActivity(), "Start", Toast.LENGTH_SHORT).show();
        Log.d("start","start");
    }

    @Override
    public void onResume() {
        super.onResume();
        //Toast.makeText(getActivity(), "Resume", Toast.LENGTH_SHORT).show();
        Log.d("resume","resume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(getActivity(), "Pause", Toast.LENGTH_SHORT).show();
        Log.d("pause","pause");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("destroy_view","Destroy View");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("detach","Detach");
    }

    @Override
    public void onStop() {
        super.onStop();
        //Toast.makeText(getActivity(), "Stop", Toast.LENGTH_SHORT).show();
        Log.d("stop","stop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(getActivity(), "Destroy", Toast.LENGTH_SHORT).show();
        Log.d("destroy","destroy");
    }
}