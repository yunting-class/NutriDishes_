package com.example.nutridishes_;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;

public class PerferenceFragment extends Fragment {

    Spinner spinner_type;
    TableLayout tableLayout_visible;
    TableLayout tableLayout_hide;
    Button check_button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_perference,container,false);

//        spinner_type = (Spinner) view.findViewById(R.id.spinner_type);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
//                R.array.spinnerValueType, android.R.layout.simple_spinner_item);//this
////        ArrayAdapter<String>arrayAdapter = new ArrayAdapter<>(PerferenceFragment.this,R.layout.spinner_set,getResources().getStringArray(R.array.spinnerValueType));
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
//        spinner_type.setAdapter(adapter);

//        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                switch (i){
//                    case 0:
//                        tableLayout_visible = (TableLayout)  view.findViewById(R.id.store_type_table);
//                        tableLayout_hide = (TableLayout)  view.findViewById(R.id.eating_type_table);
//                    case 1:
//                        tableLayout_visible = (TableLayout)  view.findViewById(R.id.eating_type_table);
//                        tableLayout_hide = (TableLayout)  view.findViewById(R.id.store_type_table);
//                }
//                tableLayout_visible.setVisibility(View.VISIBLE);
//                tableLayout_hide.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        check_button = view.findViewById(R.id.check_store);
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fr = getFragmentManager().beginTransaction();
                fr.replace(R.id.frame_layout,new RecommemdFragment());
                fr.commit();
//                FragmentManager fm = getActivity().getSupportFragmentManager();
//                fm.beginTransaction().replace(R.id.frame_layout_perfer,new RecommemdFragment()).commit();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}