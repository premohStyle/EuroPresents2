package com.project.ciaprojects.europresents.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.project.ciaprojects.europresents.Main2Activity;
import com.project.ciaprojects.europresents.MainActivity;
import com.project.ciaprojects.europresents.R;

public class SorteosActivosFragment extends Fragment {
    private static final String TAG = "SorteosActivosFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sorteos_activos, container, false);
        Log.d(TAG, "onCreateView: started");

        return view;
    }
}
