package com.example.nutridishes_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nutridishes_.dao.UserDao;
import com.example.nutridishes_.dao.User_infoDao;
import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.entity.User_info;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.sql.Date;

public class MainActivity extends AppCompatActivity  {

    private TextView account;
    private TextView password;
    private Button create_account_page;
    private Button sign_button;
    private LoginButton login;
    private TextView e_mail;

    private boolean exist;
//    final GlobalVariable gv = (GlobalVariable) getApplication();


    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    static final int RC_SIGN_IN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        account = findViewById(R.id.info);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        signInButton = (SignInButton)findViewById(R.id.sign_in_button);
        e_mail = findViewById(R.id.e_mail);




        // google登入1
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });

//        facebook
        CallbackManager callbackManager = CallbackManager.Factory.create();

        login.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                account.setText(loginResult.getAccessToken().getUserId());
//                UserDao userDao = new UserDao();
//                long miliseconds = System.currentTimeMillis();
//                Date date = new Date(miliseconds);
//                userDao.register(loginResult.getAccessToken().getUserId(),loginResult.getAccessToken().getToken(),"",date);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(@NonNull FacebookException e) {

            }
        });

        //切換頁面_創建帳號
        create_account_page = (Button) findViewById(R.id.create_account_page);
        create_account_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CreateAccount.class);
                startActivity(intent);
            }
        });

        //切換頁面_登入
        sign_button = (Button) findViewById(R.id.sign_button);
        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login(view);
            }

        });


    }
//連結資料庫
    public void login(View view){

        EditText EditTextname = (EditText)findViewById(R.id.info);
        EditText EditTextpassword = (EditText)findViewById(R.id.password);

        new Thread(){
            @Override
            public void run() {

                UserDao userDao = new UserDao();
                int userid = 0;

                boolean aa = userDao.login(EditTextname.getText().toString(),EditTextpassword.getText().toString());

                User_infoDao user_infoDao  = new User_infoDao();
                String cemail = account.getText().toString();

                userid = user_infoDao.findUserID(cemail);

                ((GlobalVariable)getApplication()).setUserid(userid);

                exist = user_infoDao.basicdata_check(cemail);
                System.out.println("exist?"+exist);
                int msg = 0;
                if(aa){
                    msg = 1;
                    ((GlobalVariable)getApplication()).setUser(userDao.getUserData(cemail));
                    System.out.println("gv_password:"+((GlobalVariable)getApplication()).getUser().getPassword());
                }

                if(exist){
//                    user_info.setHeight();
                    ((GlobalVariable)getApplication()).setUser_info(user_infoDao.getBasicData(cemail));
                    ((GlobalVariable)getApplication()).getUser_info().getHeight();
                    System.out.println("gv_height:"+((GlobalVariable)getApplication()).getUser_info().getHeight());
                }

                hand1.sendEmptyMessage(msg);


            }
        }.start();


    }
    final Handler hand1 = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            Intent intent = new Intent();

            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"登入成功",Toast.LENGTH_LONG).show();
                //account password correct--> oldAccount

                if(exist){//user_info.basicdata_check(cemail)
//                    System.out.println("123");
                    intent.setClass(MainActivity.this, BottomNavigationBar.class);

                }else{
                    intent.setClass(MainActivity.this, getBasicInformation.class);
                }


            }
            else
            {
                Toast.makeText(getApplicationContext(),"登入失敗",Toast.LENGTH_LONG).show();
                //switch case account_password_false
                intent.setClass(MainActivity.this,MainActivity.class);
            }
            startActivity(intent);
        }
    };

//google登入2
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            e_mail.setText(acct.getEmail());
            account.setText(acct.getId());
            // Signed in successfully, show authenticated UI.
            Toast.makeText(this,"Sign-in Successful",Toast.LENGTH_SHORT).show();
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}