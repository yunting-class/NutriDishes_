package com.example.nutridishes_;

import android.app.Application;

import com.example.nutridishes_.entity.User;
import com.example.nutridishes_.entity.User_info;

public class GlobalVariable extends Application {

    private int userid = 0;
    private double height = 0.0;
    private User user;
    private User_info user_info;

    public void setUser(User user){
        this.user = user;
    }

    public User getUser(){
        if(this.user == null){
            this.user = new User();
        }
        return user;
    }

    public void setUser_info(User_info user_info) {
        this.user_info = user_info;
    }

    public User_info getUser_info() {
        if(this.user_info == null){
            this.user_info = new User_info();
        }
        return user_info;
    }

    public void setUserid(int userid){
        this.userid = userid;
    }

    public int getUserid(){
        return userid;
    }

    public void setHeight(double height){
        this.height = height;
    }

    public double getHeight(){
        return height;
    }
}
