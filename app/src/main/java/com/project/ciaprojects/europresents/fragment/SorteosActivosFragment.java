package com.project.ciaprojects.europresents.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.ciaprojects.europresents.R;
import com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans.FirebaseReferences;
import com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans.Sorteo;
import com.project.ciaprojects.europresents.com.project.ciaprojects.europresents.firebase.beans.SorteosAdapter;

import java.util.ArrayList;
import java.util.List;

public class SorteosActivosFragment extends Fragment {
    private static final String TAG = "SorteosActivosFragment";
    RecyclerView recyclerView;

    List<Sorteo> sorteos;

    SorteosAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sorteos_activos, container, false);
        Log.d(TAG, "onCreateView: started");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        sorteos = new ArrayList<>();

        adapter = new SorteosAdapter(sorteos);

        recyclerView.setAdapter(adapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference sorteadosRef = database.getReference(FirebaseReferences.SORTEOS_REFERENCE);

        sorteadosRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("HOLA", "DENTRO ONDATACHANGE");
                sorteos.removeAll(sorteos);
                for (DataSnapshot snapshot:
                        dataSnapshot.getChildren()) {
                    Sorteo sorteo = snapshot.getValue(Sorteo.class);
                    sorteos.add(sorteo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }
}
