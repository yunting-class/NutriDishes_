package com.example.nutridishes_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutridishes_.dao.UserDao;
import com.example.nutridishes_.entity.User;

import java.sql.Date;

public class CreateAccount extends AppCompatActivity {


    private Button login_text;
    private Button sign_button;

    EditText email = null;
    EditText nickname = null;
    EditText password_new = null;
    EditText password_check = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.email);
        nickname = findViewById(R.id.nickname);
        password_new = findViewById(R.id.password_new);
        password_check = findViewById(R.id.password_check);

        //切換頁面_登入帳號
        login_text = (Button) findViewById(R.id.login_page);
        login_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(CreateAccount.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //切換頁面_登入
        sign_button = (Button) findViewById(R.id.sign_button);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                register(view);

            }
        });

    }

    public void register(View view){

        String cemail = email.getText().toString();
        String cnickname = nickname.getText().toString();
        String cpassword_new = password_new.getText().toString();
        String cpassword_check = password_check.getText().toString();


        if(cemail.length() < 5 ||  cpassword_new.length() > 16 || cpassword_new.length() < 8 ) {
            Toast.makeText(getApplicationContext(), "輸入資訊不符合要求請重新輸入", Toast.LENGTH_LONG).show();

            return;
        }
        if(cpassword_new.length() != cpassword_check.length() ) {
            Toast.makeText(getApplicationContext(), "您輸入的兩個密碼並不相符", Toast.LENGTH_LONG).show();
            return;
        }

        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);


        new Thread(){
            @Override
            public void run() {

                int msg = 0;
                boolean flag = false;

                UserDao userDao = new UserDao();

                if(userDao.findUser(cemail)){
                    msg = 1;

                }else if(flag = userDao.register(cemail, cpassword_new, cnickname, date)){
                    msg = 2;

                    User user = new User();

                    user.setEmail(cemail);
                    user.setPassword(cpassword_new);
                    user.setUsername(cnickname);
                    user.setCreatedate(date);

                    ((GlobalVariable)getApplication()).setUser(user);
                    System.out.println("gv_email:"+((GlobalVariable)getApplication()).getUser().getEmail());
                }

                hand.sendEmptyMessage(msg);

            }
        }.start();



    }

    @SuppressLint("HandlerLeak")
    final Handler hand = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            Intent intent = new Intent();

            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"註冊失敗",Toast.LENGTH_LONG).show();
                intent.setClass(CreateAccount.this, CreateAccount.class);

            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"該賬號已經存在，請換一個賬號",Toast.LENGTH_LONG).show();

                intent.setClass(CreateAccount.this, CreateAccount.class);

            }
            if(msg.what == 2)
            {
                Toast.makeText(getApplicationContext(),"註冊成功!",Toast.LENGTH_LONG).show();
                intent.setClass(CreateAccount.this, getBasicInformation.class);
            }

            startActivity(intent);

        }
    };

}