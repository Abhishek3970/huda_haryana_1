package com.example.huda_haryana.fragments_bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huda_haryana.R;
import com.example.huda_haryana.SG.lead_adapter;
import com.example.huda_haryana.ask_details;
import com.example.huda_haryana.order_to_database;
import com.example.huda_haryana.ui.analysis.AnalysisViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeadsFragment extends Fragment {

    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> firsttwos = new ArrayList<>();
    ArrayList<String> nos = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leads, container, false);

        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("leads");
        final RecyclerView rcv = view.findViewById(R.id.lead_recycler);

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                firsttwos.clear();nos.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    order_to_database info = snapshot.getValue(order_to_database.class);
                    if(info!=null){
                        names.add(info.getName());
                        firsttwos.add(info.getDetails());
                        nos.add(info.getNumber());
                    }
                    lead_adapter adapter = new lead_adapter(names, nos, firsttwos, getContext());
                    rcv.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    rcv.setLayoutManager(new LinearLayoutManager(getContext()));

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Data load failed", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ask_details.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initializeRV(LayoutInflater inflater, ViewGroup container){
        View view = inflater.inflate(R.layout.fragment_leads, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.lead_recycler);
        lead_adapter adapter = new lead_adapter(names, nos, firsttwos, getContext());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));}

}
