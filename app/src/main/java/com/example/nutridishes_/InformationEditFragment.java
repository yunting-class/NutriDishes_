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

import com.example.nutridishes_.dao.User_infoDao;
import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.entity.User_info;

public class InformationEditFragment extends Fragment {

    TextView nickname_edit = null;
    TextView e_mail_edit = null;
    TextView phone_edit = null;
    TextView user_id = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_information_edit, container, false);

        nickname_edit = view.findViewById(R.id.nickname_edit);
        e_mail_edit = view.findViewById(R.id.e_mail_edit);
        phone_edit = view.findViewById(R.id.phone_edit);
        user_id = view.findViewById(R.id.user_id);


        User user = new User();
        User_info user_info = new User_info();
        User_infoDao user_infoDao = new User_infoDao();

//        String nickname = user.getUsername();
        nickname_edit.setText(user.getUsername());
        e_mail_edit.setText(user.getEmail());
        phone_edit.setText(user_info.getPhoneNum());
//        user_id.setText(user_info.getInfoId());//?

        ImageButton imgbtn = (ImageButton) view.findViewById(R.id.back);

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new InformationFragment());
                fr.commit();
            }
        });

        Button check = view.findViewById(R.id.check);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }
}