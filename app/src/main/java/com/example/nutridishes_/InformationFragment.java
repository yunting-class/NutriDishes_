package com.example.nutridishes_;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class InformationFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_information, container, false);

        TextView nickname_info = view.findViewById(R.id.nickname_info);
        TextView e_mail_info = view.findViewById(R.id.e_mail_info);
        TextView phone_info = view.findViewById(R.id.phone_info);
        TextView height_info = view.findViewById(R.id.height_info);
        TextView weight_info = view.findViewById(R.id.weight_info_);
        TextView target_info = view.findViewById(R.id.target_info);

        nickname_info.setText(((GlobalVariable)getActivity().getApplication()).getUser().getUsername());
        e_mail_info.setText(((GlobalVariable)getActivity().getApplication()).getUser().getEmail());
        phone_info.setText(((GlobalVariable)getActivity().getApplication()).getUser_info().getPhoneNum());
        height_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getHeight()));
        weight_info.setText(String.valueOf(((GlobalVariable)getActivity().getApplication()).getUser_info().getWeight()));

        int goal = ((GlobalVariable)getActivity().getApplication()).getUser_info().getGoal();
        switch (goal){
            case 0:
                target_info.setText("減肥");
            case 1:
                target_info.setText("維持");
            case 2:
                target_info.setText("增重");
            case 3:
                target_info.setText("增肌");
        }



        Button btn = (Button) view.findViewById(R.id.edit);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new InformationEditFragment());
                fr.commit();
            }
        });

        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.back);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new PersonalFragment());
                fr.commit();
            }
        });

        ImageButton imgbtn_setting = (ImageButton) view.findViewById(R.id.setting);

        imgbtn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new InformationSettingFragment());
                fr.commit();
            }
        });

        return view;
    }
}