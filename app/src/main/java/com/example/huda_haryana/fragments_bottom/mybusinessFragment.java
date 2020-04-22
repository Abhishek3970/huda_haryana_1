package com.example.huda_haryana.fragments_bottom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.huda_haryana.R;

import org.w3c.dom.Text;

public class mybusinessFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View view =  inflater.inflate(R.layout.fragment_mybusiness, container, false);
        ImageView search = view.findViewById(R.id.search);
        TextView notes=view.findViewById(R.id.notes);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "For search", Toast.LENGTH_SHORT).show();
            }
        });
//        notes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });

        return view;
    }


}
