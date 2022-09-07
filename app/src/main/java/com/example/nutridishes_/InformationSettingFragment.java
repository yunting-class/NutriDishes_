package com.example.nutridishes_;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;


public class InformationSettingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information_setting, container, false);

        EditText height_info = view.findViewById(R.id.height_info);
        EditText weight_info = view.findViewById(R.id.weight_info);
        EditText body_fat_info = view.findViewById(R.id.body_fat_info);
        EditText age_info = view.findViewById(R.id.age_info);

        height_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getHeight()));
        weight_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getWeight()));
        body_fat_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getAge()));
        age_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getBodyFat()));

        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.back);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new InformationFragment());
                fr.commit();
            }
        });

        ImageButton imgbtn_basicdown = (ImageButton) view.findViewById(R.id.basic_down);
        ImageButton imgbtn_basicup = (ImageButton) view.findViewById(R.id.basic_up);
        FrameLayout frameLayout_basic = (FrameLayout) view.findViewById((R.id.basic_frame));

        imgbtn_basicdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_basic.setVisibility(View.VISIBLE);
                imgbtn_basicdown.setVisibility(View.GONE);
                imgbtn_basicup.setVisibility(View.VISIBLE);
            }
        });
        imgbtn_basicup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_basic.setVisibility(View.GONE);
                imgbtn_basicdown.setVisibility(View.VISIBLE);
                imgbtn_basicup.setVisibility(View.GONE);
            }
        });

        ImageButton imgbtn_perferdown = (ImageButton) view.findViewById(R.id.perfer_down);
        ImageButton imgbtn_perferup = (ImageButton) view.findViewById(R.id.perfer_up);
        FrameLayout frameLayout_perfer = (FrameLayout) view.findViewById((R.id.perfer_frame));

        imgbtn_perferdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_perfer.setVisibility(View.VISIBLE);
                imgbtn_perferdown.setVisibility(View.GONE);
                imgbtn_perferup.setVisibility(View.VISIBLE);
            }
        });
        imgbtn_perferup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_perfer.setVisibility(View.GONE);
                imgbtn_perferdown.setVisibility(View.VISIBLE);
                imgbtn_perferup.setVisibility(View.GONE);
            }
        });

        ImageButton imgbtn_targetdown = (ImageButton) view.findViewById(R.id.target_down);
        ImageButton imgbtn_targetup = (ImageButton) view.findViewById(R.id.target_up);
        FrameLayout frameLayout_target = (FrameLayout) view.findViewById((R.id.target_frame));

        imgbtn_targetdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_target.setVisibility(View.VISIBLE);
                imgbtn_targetdown.setVisibility(View.GONE);
                imgbtn_targetup.setVisibility(View.VISIBLE);
            }
        });
        imgbtn_targetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frameLayout_target.setVisibility(View.GONE);
                imgbtn_targetdown.setVisibility(View.VISIBLE);
                imgbtn_targetup.setVisibility(View.GONE);
            }
        });

        return view;
    }
}