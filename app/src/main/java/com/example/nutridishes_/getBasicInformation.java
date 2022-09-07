package com.example.nutridishes_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nutridishes_.dao.UserDao;
import com.example.nutridishes_.dao.User_infoDao;
import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.entity.User_info;

import java.sql.Date;

public class getBasicInformation extends AppCompatActivity {

    private Button finish;
//    Spinner spinner = findViewById(R.id.gender_spinner);
    Spinner gender;
    Spinner goal;
    EditText height = null;
    EditText weight = null;
    EditText age = null;
    EditText bodyfat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_basic_information);

        gender = findViewById(R.id.gender_spinner);
        goal = findViewById(R.id.target_spinner);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);
        age = findViewById(R.id.age);
        bodyfat = findViewById(R.id.body_fat);


        //切換頁面_登入帳號
        finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setClass(getBasicInformation.this,BottomNavigationBar.class);
//                startActivity(intent);
                basicData(view);
            }
        });
    }
    public void basicData(View view){

        int cGender = gender.getSelectedItemPosition();//0 or 1
        String sGender = gender.getTransitionName();
        int cGoal = goal.getSelectedItemPosition();
        String sHeight = height.getText().toString();
        Double cHeight ;
        String sWeight = weight.getText().toString();
        Double cWeight ;
        String sAge = age.getText().toString();
        int cAge ;//沒有值無法轉值
        String sBodyfat = bodyfat.getText().toString();
        Double cBodyfat ;

        System.out.println("cGender:"+cGender);
        System.out.println("cGoal:"+cGoal);
        System.out.println("sHeight:"+sHeight);
        System.out.println("sWeight:"+sWeight);
        if(sWeight.isEmpty()){
            System.out.println("sWeight:\"\"");
        }
        if(sWeight == null){
            System.out.println("sWeight:null");
        }

        if(sHeight.isEmpty() ||  sWeight.isEmpty()  ) { //google getText().toString(); 無值為何
            Toast.makeText(getApplicationContext(), "請輸入身高體重資料", Toast.LENGTH_LONG).show();

            return;
        }else{
            System.out.println(sHeight);
            cHeight = Double.valueOf(sHeight);
            System.out.println(sWeight);
            cWeight = Double.valueOf(sWeight);
        }
        User_info user_info = new User_info();
        if(!sAge.isEmpty()){
            cAge = Integer.valueOf(sAge);
            user_info.setAge(cAge);
        }
        if(!sBodyfat.isEmpty()){
            cBodyfat = Double.valueOf(sBodyfat);
            user_info.setBodyFat(cBodyfat);
        }

        new Thread(){
            @Override
            public void run() {

                int msg = 0;
                boolean flag = false;

                User_infoDao user_infoDao = new User_infoDao();
//                User_info user_info = new User_info();


                if(user_infoDao.setBasicData(cGender,cGoal,cHeight,cWeight)){//,cAge,cBodyfat
                    msg = 1;

                    user_info.setGender(cGender);
                    user_info.setGoal(cGoal);
                    user_info.setHeight(cHeight);
                    user_info.setWeight(cWeight);
//                    user_info.setAge(cAge);
//                    user_info.setBodyFat(cBodyfat);

                }else {
                    msg = 2;
                }
                if(!sAge.isEmpty()){
                    user_infoDao.setBasicData_age(user_info.getAge());
//                    user_info.setAge(cAge);
                }
                if(!sBodyfat.isEmpty()){
                    user_infoDao.setBasicData_bodyfat(user_info.getBodyFat());
//                    user_info.setBodyFat(cBodyfat);
                }
//                else if(flag = userDao.register(cemail, cpassword_new, cnickname, date)){
//                    msg = 2;
//
//                    User user = new User();
//
//                    user.setEmail(cemail);
//                    user.setPassword(cpassword_new);
//                    user.setUsername(cnickname);
//                    user.setCreatedate(date);
//                }

                hand.sendEmptyMessage(msg);

            }
        }.start();



    }

    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            Intent intent = new Intent();

            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"輸入失敗",Toast.LENGTH_LONG).show();
                intent.setClass(getBasicInformation.this, getBasicInformation.class);

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"建檔成功!",Toast.LENGTH_LONG).show();

                intent.setClass(getBasicInformation.this, BottomNavigationBar.class);

            }
            if(msg.what == 2)
            {
                Toast.makeText(getApplicationContext(),"請重新輸入",Toast.LENGTH_LONG).show();
                intent.setClass(getBasicInformation.this, getBasicInformation.class);
            }

            startActivity(intent);

        }
    };
}