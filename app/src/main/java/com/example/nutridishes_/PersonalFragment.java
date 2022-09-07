package com.example.nutridishes_;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PersonalFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personal,container,false);
        Button btn_joural = (Button) view.findViewById(R.id.joural);

        btn_joural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new JouralFragment());
                fr.commit();
            }
        });

        Button btn_info = (Button) view.findViewById(R.id.information);

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new InformationFragment());
                fr.commit();
            }
        });

        Button btn_nutri = (Button) view.findViewById(R.id.nutritional);

        btn_nutri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new NutritionalAnalysisFragment());
                fr.commit();
            }
        });

        return view;
    }
}