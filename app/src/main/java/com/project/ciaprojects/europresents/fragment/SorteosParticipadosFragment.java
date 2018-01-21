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
import com.project.ciaprojects.europresents.R;

public class SorteosParticipadosFragment extends Fragment {
    private static final String TAG = "SorteosActivosFragment";

    private Button btnNavFrag1;
    private Button btnNavFrag2;
    private Button btnNavFrag3;
    private Button btnNavFrag4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sorteos_participando, container, false);
        btnNavFrag1 = (Button) view.findViewById(R.id.btnNavFrag1);
        btnNavFrag2 = (Button) view.findViewById(R.id.btnNavFrag2);
        btnNavFrag3 = (Button) view.findViewById(R.id.btnNavFrag3);
        btnNavFrag4 = (Button) view.findViewById(R.id.btnNavFrag4);
        Log.d(TAG, "onCreateView: started");

        btnNavFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Sorteos activos", Toast.LENGTH_SHORT).show();

                ((Main2Activity)getActivity()).setViewPager(0);
            }
        });

        btnNavFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Sorteos proximos", Toast.LENGTH_SHORT).show();

                ((Main2Activity)getActivity()).setViewPager(1);
            }
        });

        btnNavFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Sorteos participando", Toast.LENGTH_SHORT).show();

                ((Main2Activity)getActivity()).setViewPager(2);
            }
        });

        btnNavFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Toast.makeText(getActivity(), "Sorteos acabados", Toast.LENGTH_SHORT).show();

                ((Main2Activity)getActivity()).setViewPager(3);
            }
        });

        return view;
    }
}
